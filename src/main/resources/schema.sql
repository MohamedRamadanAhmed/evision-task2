DROP TABLE IF EXISTS user;
create table user
(
    id int primary key auto_increment,
    user_name varchar_ignorecase(50) unique  not null,
    e_password varchar_ignorecase(200) not null
);
DROP TABLE IF EXISTS transaction;

create table bank_transaction
(
   id        int primary key auto_increment,
   user_name varchar_ignorecase(50) ,
   transaction_type varchar_ignorecase(50)  not null ,
   old_balance decimal(15,2),
   new_balance decimal(15,2),
   constraint fk_transaction_user foreign key (user_name) references user (user_name)
    
);

