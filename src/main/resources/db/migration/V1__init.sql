--Таблица стоянок. Связь с таблицей грузовиков один-ко-многим
create table parkings
(
    id              bigserial primary key,
    address         varchar(255),
    square          int
);

--Таблица водителей. Связь с таблицей грузовиков многое-ко-многим
create table drivers
(
    id              bigserial primary key,
    fio             varchar(255)
);

--Таблица грузовиков. Связь с таблицей водителей многие-к-одному
create table trucks
(
    id              bigserial primary key,
    model       	varchar(255),
    number       	varchar(10),
    parking_id        bigint references parkings (id)
);

--Таблица связи водителей и грузовиков
create table drivers_trucks
(
    id              bigserial primary key,
    driver_id       bigint references drivers (id),
    truck_id        bigint references trucks (id)
);

insert into parkings (address, square) values ('cherkessk', 30);
insert into parkings (address, square) values ('psyzh', 50);
insert into parkings (address, square) values ('chapai', 10);

insert into drivers (fio) values ('Ivanov Ivan Ivanovich');
insert into drivers (fio) values ('Petrov Petr Petrovich');
insert into drivers (fio) values ('Nikolaev Nikolay Nikolaevich');

insert into trucks (model, number, parking_id) values ('MAN', 'B001HO09', 1);
insert into trucks (model, number, parking_id) values ('DAF', 'B002HO09', 2);
insert into trucks (model, number, parking_id) values ('Gazel', 'B003HO09', 3);

insert into drivers_trucks (driver_id, truck_id) values (1, 1);
insert into drivers_trucks (driver_id, truck_id) values (1, 3);
insert into drivers_trucks (driver_id, truck_id) values (2, 1);
insert into drivers_trucks (driver_id, truck_id) values (2, 2);
insert into drivers_trucks (driver_id, truck_id) values (3, 1);
insert into drivers_trucks (driver_id, truck_id) values (3, 2);
insert into drivers_trucks (driver_id, truck_id) values (3, 3);

