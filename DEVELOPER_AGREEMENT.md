# Error handling & logging — Developer Agreement
> Last updated: 2026-04-26

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

## 4. Error code naming

- Αγγλικά, SCREAMING_SNAKE_CASE, prefix του domain
- Παραδείγματα: `LAW_FIRM_NOT_FOUND`, `SUBSCRIPTION_NOT_FOUND`, `PAYMENT_INVALID_PERIOD`

---

## 5. Logging

### Format
- Structured JSON
- Γλώσσα: αγγλικά

### Message format
```
"Law firm not found [lawFirmId=3fa85f64]"
```
- Contextual values πάντα εντός `[]`
- Ίδια values επαναλαμβάνονται και ως `attributes` για machine querying

### Log entry structure
```json
{
  "timestamp": "2026-04-26T10:30:00.123Z",
  "level": "ERROR",
  "service": "law-platform",
  "module": "tenancy",
  "traceId": "abc123",
  "message": "Law firm not found [lawFirmId=3fa85f64]",
  "exception": "com.katsadourose.law_platform.core.exception.NotFoundException",
  "attributes": {
    "lawFirmId": "3fa85f64"
  }
}
```

### Mandatory fields
- `timestamp`, `level`, `service`, `module`, `traceId`, `message`
- `attributes` — παρόν πάντα, κενό object αν δεν υπάρχουν values
- `exception` — μόνο σε ERROR level

---

## 6. Mapping

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