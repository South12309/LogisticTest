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
create table trucks_drivers
(
    id              bigserial primary key,
    driver_id       bigint references drivers (id),
    truck_id        bigint references trucks (id)
);

--Таблица стоянок. Связь с таблицей грузовиков один-ко-многим
create table parkings
(
    id              bigserial primary key,
    address         varchar(255),
    square          int
);

