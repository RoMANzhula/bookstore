CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    author VARCHAR(255),
    description TEXT,
    price FLOAT
);