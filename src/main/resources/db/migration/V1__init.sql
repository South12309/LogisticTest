DROP SCHEMA IF EXISTS logistic;
CREATE SCHEMA logistic;

--SET SCHEMA = logistic;

--Таблица стоянок. Связь с таблицей грузовиков один-ко-многим
create table logistic.parkings
(
    id bigserial NOT NULL,
    address character varying(255),
    square integer,
    CONSTRAINT parkings_pkey PRIMARY KEY (id)
);

--Таблица водителей. Связь с таблицей грузовиков многое-ко-многим
create table logistic.drivers
(
    id bigserial NOT NULL,
    surname character varying(50),
    name character varying(50),
    patronymic character varying(50),
    CONSTRAINT drivers_pkey PRIMARY KEY (id)
);

--Таблица грузовиков. Связь с таблицей водителей многие-к-одному
create table logistic.trucks
(
    id bigserial NOT NULL,
    model character varying(255),
    "number" character varying(10),
    parking_id bigint,
    CONSTRAINT trucks_pkey PRIMARY KEY (id),
    CONSTRAINT trucks_parking_id_fkey FOREIGN KEY (parking_id)
        REFERENCES logistic.parkings (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE

);
--Таблица связи водителей и грузовиков
create table logistic.drivers_trucks
(
    driver_id bigint,
    truck_id bigint,
    CONSTRAINT drivers_trucks_pkey PRIMARY KEY (driver_id, truck_id),
    CONSTRAINT drivers_trucks_driver_id_fkey FOREIGN KEY (driver_id)
        REFERENCES logistic.drivers (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    CONSTRAINT drivers_trucks_truck_id_fkey FOREIGN KEY (truck_id)
        REFERENCES logistic.trucks (id) MATCH SIMPLE
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

insert into logistic.parkings (address, square) values ('cherkessk', 30);
insert into logistic.parkings (address, square) values ('psyzh', 50);
insert into logistic.parkings (address, square) values ('chapai', 10);

insert into logistic.drivers (surname, name, patronymic) values ('Ivanov', 'Ivan', 'Ivanovich');
insert into logistic.drivers (surname, name, patronymic) values ('Petrov', 'Petr', 'Petrovich');
insert into logistic.drivers (surname, name, patronymic) values ('Nikolaev', 'Nikolay', 'Nikolaevich');

insert into logistic.trucks (model, number, parking_id) values ('MAN', 'B001HO09', 1);
insert into logistic.trucks (model, number, parking_id) values ('DAF', 'B002HO09', 2);
insert into logistic.trucks (model, number, parking_id) values ('Gazel', 'B003HO09', 3);

insert into logistic.drivers_trucks (driver_id, truck_id) values (1, 1);
insert into logistic.drivers_trucks (driver_id, truck_id) values (1, 3);
insert into logistic.drivers_trucks (driver_id, truck_id) values (2, 1);
insert into logistic.drivers_trucks (driver_id, truck_id) values (2, 2);
insert into logistic.drivers_trucks (driver_id, truck_id) values (3, 1);
insert into logistic.drivers_trucks (driver_id, truck_id) values (3, 2);
insert into logistic.drivers_trucks (driver_id, truck_id) values (3, 3);

