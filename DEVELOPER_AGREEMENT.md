# Error handling & logging — Developer Agreement
> Last updated: 2026-05-03

---

## 1. Exception hierarchy

```
AppException (base, RuntimeException)
├── NotFoundException        → 404
├── ValidationException      → 400
├── ConflictException        → 409
├── AccessDeniedException    → 403
└── BusinessRuleException    → 422
```

---

## 2. Spring exceptions handled by GlobalExceptionHandler

| Exception | HTTP | errorCode |
|---|---|---|
| `NotFoundException` | 404 | από το exception |
| `ValidationException` | 400 | από το exception |
| `ConflictException` | 409 | από το exception |
| `BusinessRuleException` | 422 | από το exception |
| `AccessDeniedException` | 403 | από το exception |
| `MethodArgumentNotValidException` | 400 | `VALIDATION_FAILED` |
| `HttpMessageNotReadableException` | 400 | `MALFORMED_REQUEST` |
| `Exception` (catch-all) | 500 | `INTERNAL_SERVER_ERROR` |

---

## 3. Error response format

```json
{
  "errorCode": "LAW_FIRM_NOT_FOUND",
  "message": "Law firm not found [lawFirmId=3fa85f64]",
  "timestamp": "2026-04-26T10:30:00",
  "path": "/api/admin/law-firms/3fa85f64",
  "details": {}
}
```

---

## 4. Error codes

### Rules
- **MUST be defined as enum** in each module
- Enum name: `ErrorCode` in the module's exception package
- Naming: English, SCREAMING_SNAKE_CASE, with domain prefix
- Examples: `LAW_FIRM_NOT_FOUND`, `SUBSCRIPTION_NOT_FOUND`, `PAYMENT_INVALID_PERIOD`

### Structure
```kotlin
// exception/ErrorCode.kt
enum class ErrorCode {
    LAW_FIRM_NOT_FOUND,
    LAW_FIRM_ALREADY_EXISTS,
    SUBSCRIPTION_NOT_FOUND,
    PAYMENT_INVALID_PERIOD
}
```

### Usage in exceptions
```kotlin
class LawFirmNotFoundException(lawFirmId: UUID) : AppException(
    errorCode = ErrorCode.LAW_FIRM_NOT_FOUND.name,
    message = "Law firm not found [lawFirmId=$lawFirmId]"
)
```

---

## 5. Log event codes

Every log entry MUST include an `eventCode` that uniquely identifies the event.
Event codes apply to ALL log levels — INFO, WARN, ERROR.

### Log format
- All logs MUST be written in structured JSON format
- Language: English
- Every log entry MUST contain these fields:

```json
{
  "timestamp": "2026-04-26T10:30:00.123Z",
  "level": "ERROR",
  "service": "law-case-manager",
  "traceId": "abc123",
  "eventCode": "USR_004",
  "message": "User not found",
  "exception": "com.katsadourose.lawcasemanager.core.exception.NotFoundException",
  "attributes": {
    "userId": "abc123"
  }
}
```

### Mandatory fields
- `timestamp`, `level`, `service`, `traceId`, `eventCode`, `message`
- `attributes` — always present, empty object if no values
- `exception` — only on ERROR level

### Message format
- Messages MUST be clean and descriptive without contextual values embedded in text
- All contextual data MUST be in `attributes` only for machine querying
- Message should describe the event clearly but not include specific IDs or values
- Example: `"User not found"` (with userId in attributes, NOT `"User not found [userId=abc123]"`)

### Event code format
`{MODULE_PREFIX}_{NUMBER}`
- MODULE_PREFIX: 3 uppercase letters identifying the module
- NUMBER: 3-digit zero-padded integer
- Example: `USR_001`, `FRM_003`, `PAY_012`

### Implementation

Each module defines its own event codes as a Kotlin enum
located at `{module}/logging/EventCodes.kt`.

Each enum entry carries the code as a string property:
```kotlin
enum class PlatformUserEventCode(val code: String) {
    USER_CREATED("USR_001"),
    USER_DEACTIVATED("USR_002"),
    ...
}
```

### Rules
- All logs MUST be in JSON format — no plain text logging
- Every log call MUST reference an entry from the module's EventCode enum
- New events MUST be added to the enum before being used in code
- Event codes are immutable — never reuse or renumber an existing entry
- The `eventCode` field in the log entry uses the `code` string value, not the enum name
- Module name is derived from the event code prefix, so no need to add it as a separate attribute
- Log messages MUST NOT contain contextual values like IDs, names, or amounts — use attributes instead

## 6. Service layer architecture

- **Always use interface + implementation pattern**
- Service interfaces live directly in the `service` package
- Implementations live in `service/implementation` package
- Implementation class name = Interface name + `Impl` suffix

### Example structure
```
service/
├── LawFirmService.kt              // interface
├── LawFirmUserService.kt          // interface
└── implementation/
    ├── LawFirmServiceImpl.kt      // @Service implementation
    └── LawFirmUserServiceImpl.kt  // @Service implementation
```

### Example
```kotlin
// service/LawFirmService.kt
interface LawFirmService {
    fun createLawFirm(request: CreateLawFirmRequest): LawFirmResponse
}

// service/implementation/LawFirmServiceImpl.kt
@Service
class LawFirmServiceImpl(
    private val lawFirmRepository: LawFirmRepository
) : LawFirmService {
    override fun createLawFirm(request: CreateLawFirmRequest): LawFirmResponse {
        // implementation
    }
}
```

---

## 7. Mapping

- **NO mapping logic in service classes**
- Use dedicated `Mapper` objects (Kotlin `object` singleton)
- Mappers contain extension functions for transformations
- Pattern: `Request.toEntity()`, `Entity.toResponse()`

### Example
```kotlin
object LawFirmUserMapper {
    fun CreateLawFirmUserRequest.toEntity(lawFirm: LawFirm): LawFirmUser {
        return LawFirmUser(
            lawFirm = lawFirm,
            firstName = this.firstName,
            lastName = this.lastName,
            // ...
        )
    }
}
```

### Usage in service
```kotlin
import com.katsadourose.lawcasemanager.lawfirm.mapper.LawFirmUserMapper.toEntity

val user = request.toEntity(lawFirm)
```