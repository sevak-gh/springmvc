create table product(id int unsigned not null auto_increment primary key,
                     name varchar(50),
                     price decimal not null,
                     dt datetime not null);

create table user(id int unsigned not null auto_increment primary key,
                  username varchar(50) not null,
                  password varchar(50) not null,
                  enabled tinyint not null);                  
