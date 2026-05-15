CREATE TABLE habitats (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) UNIQUE NOT NULL
);

CREATE TABLE creatures (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    habitat_id BIGINT REFERENCES habitats(id),
    status VARCHAR(20) NOT NULL DEFAULT 'ACTIVE',
    UNIQUE(habitat_id, name)
);

CREATE TABLE users (
    id BIGSERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    phone VARCHAR(50),
    role VARCHAR(50) NOT NULL
);

CREATE TABLE observations (
    id BIGSERIAL PRIMARY KEY,
    creature_id BIGINT REFERENCES creatures(id),
    user_id BIGINT REFERENCES users(id),
    note TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE feeding_schedules (
    id BIGSERIAL PRIMARY KEY,
    creature_id BIGINT REFERENCES creatures(id),
    feeding_time TIME NOT NULL
);