create table order_orders (
  id int8 not null,
  customer_name VARCHAR(255) not null,
  drink varchar(255) not null,
  primary key (id)
);

create sequence seq_order;