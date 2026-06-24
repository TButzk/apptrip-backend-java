CREATE TABLE app_user (
    id UUID PRIMARY KEY,
    name VARCHAR(120) NOT NULL,
    password VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    role VARCHAR(20) NOT NULL DEFAULT 'USER'
);

CREATE TABLE route (
    id UUID PRIMARY KEY,
    name VARCHAR(180) NOT NULL,
    user_id UUID NOT NULL REFERENCES app_user(id),
    status VARCHAR(20) NOT NULL DEFAULT 'DRAFT',
    published_at TIMESTAMP,
    finalized_at TIMESTAMP,
    minimum_distance_meters INTEGER NOT NULL DEFAULT 25
);

CREATE TABLE place (
    id UUID PRIMARY KEY,
    name VARCHAR(180) NOT NULL,
    route_id UUID NOT NULL REFERENCES route(id) ON DELETE CASCADE,
    latitude DOUBLE PRECISION NOT NULL,
    longitude DOUBLE PRECISION NOT NULL,
    sequence INTEGER NOT NULL,
    captured_at TIMESTAMP NOT NULL,
    client_point_id UUID UNIQUE,
    accuracy_meters DOUBLE PRECISION,
    street VARCHAR(255),
    street_number VARCHAR(50),
    complement VARCHAR(255),
    city VARCHAR(120),
    postal_code VARCHAR(30),
    country VARCHAR(120),
    state VARCHAR(120),
    neighborhood VARCHAR(120),
    type SMALLINT
);
CREATE INDEX idx_place_route_sequence ON place(route_id, sequence);

CREATE TABLE post (
    id UUID PRIMARY KEY,
    title VARCHAR(180),
    message TEXT,
    date TIMESTAMP NOT NULL,
    user_id UUID NOT NULL REFERENCES app_user(id),
    place_id UUID NOT NULL REFERENCES place(id) ON DELETE CASCADE
);
CREATE INDEX idx_post_place_date ON post(place_id, date);

CREATE TABLE media (
    id UUID PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    url TEXT NOT NULL,
    post_id UUID NOT NULL REFERENCES post(id) ON DELETE CASCADE,
    type VARCHAR(20) NOT NULL,
    storage_filename VARCHAR(255),
    content_type VARCHAR(120),
    size_bytes BIGINT
);

CREATE TABLE comment (
    id UUID PRIMARY KEY,
    message TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL,
    user_id UUID NOT NULL REFERENCES app_user(id),
    post_id UUID NOT NULL REFERENCES post(id) ON DELETE CASCADE
);

CREATE TABLE rating (
    id UUID PRIMARY KEY,
    place_id UUID NOT NULL REFERENCES place(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES app_user(id),
    value INTEGER NOT NULL CHECK (value BETWEEN 1 AND 5),
    updated_at TIMESTAMP NOT NULL,
    CONSTRAINT uk_rating_place_user UNIQUE(place_id, user_id)
);

CREATE TABLE route_link (
    id UUID PRIMARY KEY,
    place_id UUID NOT NULL REFERENCES place(id) ON DELETE CASCADE,
    linked_route_id UUID NOT NULL REFERENCES route(id) ON DELETE CASCADE,
    user_id UUID NOT NULL REFERENCES app_user(id),
    created_at TIMESTAMP NOT NULL,
    CONSTRAINT uk_route_link_place_route_user UNIQUE(place_id, linked_route_id, user_id)
);

CREATE TABLE favorite_places (
    id UUID PRIMARY KEY,
    name VARCHAR(180),
    user_id UUID NOT NULL REFERENCES app_user(id),
    place_id UUID NOT NULL REFERENCES place(id) ON DELETE CASCADE
);
