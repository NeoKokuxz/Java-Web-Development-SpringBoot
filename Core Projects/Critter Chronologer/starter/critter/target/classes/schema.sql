create table if not exists critterTable(
    id bigint not null,
    type varchar(255),
    name varchar(255),
    ownerId bigint not null,
    birthday datetime,
    notes varchar(255),
    primary key (id)
);
