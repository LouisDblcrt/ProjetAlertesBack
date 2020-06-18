create table administrator (
    id int primary key auto_increment,
    login varchar(150) not null,
    password varchar(150) not null,
    name varchar(150) not null,
    surname varchar(150) not null
);

create table user(
id int primary key,
phone_number varchar(10),
email varchar(150),
password varchar(128) not null,
UNIQUE(email)
);

create table alert_owner(
id int primary key auto_increment,
enterprise varchar(150) not null,
description varchar(300),
priority_max int not null
);

create table alert(
id int primary key auto_increment,
name varchar(100) not null,
description varchar(300),
alert_date date not null,
lieu varchar(50) not null,
alert_owner int not null,
constraint FK_ALERT_OWNER foreign key(alert_owner) references alert_owner(id)
);

create table subscription(
id_user int,
id_alert_owner int,
date_subscription date,
constraint PK_SUBSCRIPTION primary key(id_user,id_alert_owner),
constraint FK_ID_USER foreign key(id_user) references user(id),
constraint FK_ID_ALERT_OWNER foreign key(id_alert_owner) references alert_owner(id)
);

alter table alert add column criticite int;
alter table alert modify alert_date DATETIME ;