CREATE USER revabanking_app
WITH PASSWORD 'revabank';

GRANT ALL PRIVILEGES
ON database postgres
TO revabanking_app;

drop table if exists Customers;
drop table if exists Accounts;
drop table if exists Roles;

/*                 Table Creation                    */
-- Need a customer table that holds their id, first name, last name and email

create table Customers(
	customer_id serial not null,
	account_id serial not null,
	first_name varchar(20) not null,
	last_name varchar(20) not null,
	user_name varchar (20) not null,
	user_password varchar (20) not null,
	email varchar(256) not null,

	constraint pk_customers
	primary key (customer_id)
	
);

-- Create an account table that stores account information for customers, accounts should relate to customers
-- by id, and the account numbers should be randomly generated (if possible)
create table Accounts(
	customer_id serial not null,
	account_number integer not null,
	ac_firstname varchar(20) not null,
	ac_lastname varchar(20) not null,
	balance numeric,
	
	constraint pk_accountId
	primary key (account_number)
);

create table Roles(
	role_id Int not null,
	role_name varchar(20) not null,
	
	constraint pk_roles
	primary key (role_id)
);

/*     End of Table Creation       */


/* Forign Key constraints  */

alter table accounts add constraint fk_customer_id 
foreign key (customer_id) references customer (customer_id) on delete cascade on update cascade;

/* Inserting values into the customers table */

insert into customers (first_name, last_name, user_name, user_password, email )
values ('Dave', 'Brown', 'Dbrown', 'password', 'davebrown@gmail.com');






