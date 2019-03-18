create database social_network;

USE social_network;

create table address
(
  id      int auto_increment
    primary key,
  city    varchar(255) null,
  country varchar(255) null,
  home    varchar(255) null
);

create table event
(
  id           int auto_increment
    primary key,
  name         varchar(255) null,
  about_event  varchar(255) not null,
  date_event   date         not null,
  id_moderator int          not null
);

create table forum
(
  id           int auto_increment
    primary key,
  name         varchar(255) not null,
  topic        varchar(255) null,
  id_moderator int          not null
);

create table forum_message
(
  id           int auto_increment
    primary key,
  message      varchar(400) null,
  date         date         null,
  id_recipient int          not null,
  id_forum     int          not null
);

create table info
(
  id          int auto_increment
    primary key,
  birthday    date         null,
  school      varchar(255) null,
  high_school varchar(255) null,
  about_user  varchar(400) null,
  skype       varchar(50)  null,
  first_name  varchar(255) not null,
  last_name   varchar(255) not null,
  phone       varchar(20)  null
);

create table message
(
  id           int auto_increment
    primary key,
  message      varchar(400) null,
  id_recipient int          not null,
  id_sender    int          not null,
  date         date         not null
);

create table public_message
(
  id           int auto_increment
    primary key,
  message      varchar(400) null,
  id_recipient int          not null,
  date         date         not null
);

create table role
(
  id   int auto_increment
    primary key,
  name varchar(100) not null
);

create table user
(
  id         int auto_increment
    primary key,
  login      varchar(255) not null,
  password   varchar(255) not null,
  email      varchar(50)  not null,
  info_id    int          not null,
  address_id int          not null,
  constraint user_email_uindex
    unique (email),
  constraint user_login_uindex
    unique (login)
);

create table user_event
(
  id_user  int not null,
  id_event int not null
);

create table user_forum
(
  id_user  int not null,
  id_forum int not null
);

create table user_friend
(
  id_user   int not null,
  id_friend int not null
);

create table user_role
(
  id_user int not null,
  id_role int not null
);

INSERT INTO role VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN')