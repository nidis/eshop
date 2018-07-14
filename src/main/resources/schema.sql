drop table if exists address;
drop table if exists product;
drop table if exists customer;
drop table if exists cart;
drop table if exists cart_item;
drop table if exists delivery;
drop table if exists `order`;
drop table if exists invoice;
drop table if exists warehouse;
drop table if exists image;

create table address(
    id bigint not null auto_increment primary key,
    customer_id bigint not null,
    type varchar(40) not null,
    street varchar(100) not null,
    city varchar(50) not null,
    postcode varchar(25) not null);

create table product(
    id bigint not null auto_increment primary key,
    description varchar(200) not null,
    price decimal not null);

create table customer(
    id bigint not null auto_increment primary key,
    name varchar(100) not null,
    surname varchar(100) not null,
    email varchar(100) not null,
    phone varchar(25) not null);

create table cart(
    id bigint not null auto_increment primary key,
    session_id varchar(36) null,
    ip_address varchar(45) null,
    customer_id bigint null,
    status varchar(20) not null,
    time_created timestamp not null);

create table cart_item(
    id bigint not null auto_increment primary key,
    cart_id bigint not null,
    product_id bigint not null,
    quantity integer not null);

create table delivery(
    id bigint not null auto_increment primary key,
    order_id bigint not null,
    method varchar(30) not null);

create table `order`(
    id bigint not null auto_increment primary key,
    cart_id bigint not null,
    customer_id bigint not null,
    time_created timestamp not null,
    delivery_id bigint not null,
    status varchar(20) not null);

create table invoice(
    id bigint not null auto_increment primary key,
    order_id bigint not null,
    type varchar(30) not null);

create table warehouse(
    id bigint not null auto_increment primary key,
    product_id bigint not null,
    quantity integer not null);

create table image(
    id bigint not null auto_increment primary key,
    file_location varchar(400) null,
    product_id bigint not null);

alter table address add foreign key (customer_id) references customer(id);
alter table cart add foreign key (customer_id) references customer(id);
alter table cart_item add foreign key (cart_id) references cart(id);
alter table cart_item add foreign key (product_id) references product(id);
alter table delivery add foreign key (order_id) references `order`(id);
alter table `order` add foreign key (cart_id) references cart(id);
alter table `order` add foreign key (customer_id) references customer(id);
alter table `order` add foreign key (delivery_id) references delivery(id);
alter table invoice add foreign key (order_id) references `order`(id);
alter table warehouse add foreign key (product_id) references product(id);
alter table image add foreign key (product_id) references product(id);
