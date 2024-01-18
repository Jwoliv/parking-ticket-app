create table _user (
     id bigserial not null,
     bonus_money float4,
     change float4,
     date_born date,
     firstname varchar(255),
     lastname varchar(255),
     personal_key varchar(255),
     surname varchar(255),
     primary key (id)
);

create table address (
     id bigserial not null,
     city varchar(255),
     number varchar(255),
     street varchar(255),
     primary key (id)
);

create table parking (
     id bigserial not null,
     available_parking_spaces bigint,
     description varchar(255),
     price_per_hour float4,
     title varchar(255),
     total_parking_spaces bigint,
     address_id bigint,
     primary key (id)
);

create table ticket (
    id bigserial not null,
    amount_bonus_money float4,
    amount_payed_money integer,
    change float4,
    end_time timestamp(6),
    key varchar(255),
    number_place bigint,
    start_time timestamp(6),
    parking_id bigint,
    user_id bigint,
    primary key (id)
);