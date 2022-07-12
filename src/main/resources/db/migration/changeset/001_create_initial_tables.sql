CREATE TABLE IF NOT EXISTS tester
(
    id  BIGINT PRIMARY KEY,
    first_name TEXT,
    last_name  TEXT,
    country    VARCHAR(2),
    last_login TIMESTAMP

);

CREATE TABLE IF NOT EXISTS device
(
    id    BIGINT PRIMARY KEY,
    description TEXT
);

CREATE TABLE IF NOT EXISTS country
(
    id    BIGINT PRIMARY KEY,
    name VARCHAR(2)
);

CREATE TABLE IF NOT EXISTS tester_device
(
    device_id BIGINT REFERENCES device (id),
    tester_id BIGINT REFERENCES tester (id)

);

CREATE TABLE IF NOT EXISTS bug
(
    id        BIGINT PRIMARY KEY,
    device_id BIGINT REFERENCES device (id),
    tester_id BIGINT REFERENCES tester (id)
);
