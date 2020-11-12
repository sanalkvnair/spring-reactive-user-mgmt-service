CREATE DATABASE admin;

create table USERS(
id SERIAL PRIMARY KEY,
	first_name varchar(30) NOT NULL,
 last_name varchar(30) NOT NULL, email varchar(50) UNIQUE NOT NULL,
  password varchar(30) NOT NULL, username varchar(30) UNIQUE NOT NULL,
  user_enabled boolean DEFAULT TRUE NOT NULL,
	token_expired boolean DEFAULT TRUE,
  created_on timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL,
  last_login timestamp DEFAULT CURRENT_TIMESTAMP NOT NULL
);

insert into USERS(first_name, last_name, email, password, username)
values('Admin','Admin', 'admin@test.com', 'admin!123', 'admin');