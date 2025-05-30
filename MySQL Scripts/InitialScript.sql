create database weather_db;
use weather_db;

create table city(
	id	int primary key auto_increment,
    name varchar(255) not null,
    lat double not null,
    lon double not null
);

create table forecast(
	id	int primary key auto_increment,
    city_id int references city(id) on update cascade on delete cascade,
    date date not null,
    max_temp int not null,
    rainy_day tinyint not null,
    rain_description varchar(255)
);