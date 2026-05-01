CREATE TABLE law_firms (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    tax_number VARCHAR(255) NOT NULL UNIQUE,
    address VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now(),
    updated_at TIMESTAMP NOT NULL DEFAULT now()
);

CREATE TABLE law_firm_subscriptions (
    id UUID PRIMARY KEY,
    law_firm_id UUID NOT NULL REFERENCES law_firms(id),
    status VARCHAR(50) NOT NULL DEFAULT 'ACTIVE',
    plan VARCHAR(50) NOT NULL DEFAULT 'STARTER',
    current_period_start TIMESTAMP NOT NULL,
    current_period_end TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL DEFAULT now(),
    UNIQUE(law_firm_id)
);

CREATE TABLE law_firm_payments (
    id UUID PRIMARY KEY,
    law_firm_id UUID NOT NULL REFERENCES law_firms(id),
    amount NUMERIC(10,2) NOT NULL,
    currency VARCHAR(10) NOT NULL DEFAULT 'EUR',
    paid_at TIMESTAMP NOT NULL,
    period_start TIMESTAMP NOT NULL,
    period_end TIMESTAMP NOT NULL,
    notes TEXT,
    recorded_by UUID NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);
