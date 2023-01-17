DROP DATABASE booking;
CREATE DATABASE booking;

\c booking

CREATE TABLE users (
  user_id serial PRIMARY KEY,
  username VARCHAR NOT NULL,
  password VARCHAR NOT NULL,
  email VARCHAR NOT NULL,
  first_name VARCHAR NOT NULL,
  last_name VARCHAR NOT NULL
);

CREATE TABLE stations (
  station_id serial PRIMARY KEY,
  name VARCHAR NOT NULL,
  city VARCHAR NOT NULL,
  country VARCHAR NOT NULL
);

CREATE TABLE trains (
  train_id serial PRIMARY KEY,
  train_user_id VARCHAR NOT NULL,
  departure_station_id INTEGER REFERENCES stations (station_id),
  arrival_station_id INTEGER REFERENCES stations (station_id),
  date_time TIMESTAMP NOT NULL,
  available_seats_first INTEGER NOT NULL,
  available_seats_business INTEGER NOT NULL,
  available_seats_standard INTEGER NOT NULL,
  flexible_price NUMERIC NOT NULL,
  Non_flexible_price NUMERIC NOT NULL

);

CREATE TABLE fares (
  fare_id serial PRIMARY KEY,
  ticket_type VARCHAR NOT NULL,
  travel_class VARCHAR NOT NULL CHECK (travel_class IN ('First', 'Business', 'Standard')),
  price NUMERIC NOT NULL
);

CREATE TABLE reservations (
  reservation_id serial PRIMARY KEY,
  user_id INTEGER REFERENCES users (user_id),
  outbound_train_id INTEGER REFERENCES trains (train_id),
  return_train_id INTEGER REFERENCES trains (train_id),
  travel_class VARCHAR NOT NULL CHECK (travel_class IN ('First', 'Business', 'Standard')),
  ticket_type VARCHAR NOT NULL CHECK (ticket_type IN ('flexible', 'not flexible')),
  number_of_tickets Integer NOT NULL
);


INSERT INTO users (username, password, email, first_name, last_name) VALUES 
  ('Nour', 'admin', 'nelbessi98@gmail.com', 'Nour', 'ELBESSI'),
  ('Mariem', 'admin', 'mchatti@gmail.com', 'Mariem', 'Chatti');

INSERT INTO stations (name, city, country) VALUES
  ('Paris Nord', 'Paris', 'France'),
  ('London st Pancras', 'London', 'UK'),
  ('Berlin Hbf', 'Berlin', 'Germany');

INSERT INTO trains (train_user_id ,departure_station_id, arrival_station_id, date_time, available_seats_first, available_seats_business, available_seats_standard, flexible_price, non_flexible_price) VALUES
  ('PAR12', 1, 2, '2023-01-01 12:00:00', 40, 70, 150, 100, 80),
  ('LON12', 2, 1, '2023-01-03 08:00:00', 40, 70, 150, 100, 80),
  ('BER12', 1, 3, '2023-12-31 08:00:00', 70, 70, 150, 200, 180);
 

INSERT INTO fares (ticket_type, travel_class, price) VALUES
  ('flexible', 'Standard', 100),
  ('not flexible', 'Standard', 80),
  ('flexible', 'Business', 150),
  ('not flexible', 'Business', 120),
  ('flexible', 'First', 200),
  ('not flexible', 'First', 180);

INSERT INTO reservations (user_id, outbound_train_id, return_train_id, travel_class, ticket_type, number_of_tickets) VALUES
  (1, 1, 2, 'Standard', 'flexible', 2),
  (2, 2, 1, 'Business', 'not flexible', 1),
  (1, 3, 1, 'First', 'flexible', 1);



