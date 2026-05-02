CREATE TABLE law_firm_users (
    id UUID PRIMARY KEY,
    law_firm_id UUID NOT NULL,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password_hash VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL,
    active BOOLEAN NOT NULL,
    last_login_at TIMESTAMP,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL,
    bar_number VARCHAR(255),
    bar_association VARCHAR(255),
    specialization VARCHAR(255),
    FOREIGN KEY (law_firm_id) REFERENCES law_firms(id),
    UNIQUE (law_firm_id, email)
);