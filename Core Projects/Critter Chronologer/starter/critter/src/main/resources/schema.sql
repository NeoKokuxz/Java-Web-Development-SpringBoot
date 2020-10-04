create table if not exists critterTable(
    id bigint not null,
    type varchar(255),
    name varchar(255),
    ownerId bigint not null,
    birthday datetime,
    notes varchar(255),
    primary key (id)
);

create table if not exists customerTable(
  customer_id bigint not null,
  name varchar(255),
  critter_id bigint not null,
  primary key (customer_id)
);

create table if not exists employeeTable(
    employee_id bigint not null,
    name varchar(255),
    primary key (employee_id)
);