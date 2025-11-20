CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE IF NOT EXISTS users (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  name VARCHAR(150) NOT NULL,
  email VARCHAR(200) NOT NULL UNIQUE,
  password VARCHAR(200) NOT NULL,
  balance NUMERIC(20,2) NOT NULL DEFAULT 0,
  created_at TIMESTAMP DEFAULT now()
);

CREATE TABLE IF NOT EXISTS transactions (
  id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
  user_id UUID NOT NULL REFERENCES users(id) ON DELETE CASCADE,
  target_user_id UUID REFERENCES users(id),
  type VARCHAR(20) NOT NULL,
  amount NUMERIC(20,2) NOT NULL,
  description TEXT,
  created_at TIMESTAMP DEFAULT now()
);