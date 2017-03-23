
create table user(
	id int primary key AUTO_INCREMENT,
	name varchar(50),
	password varchar(50)
);

create table product(
	id int primary key AUTO_INCREMENT,
	name varchar(255),
	price double,
	description varchar(255) 
);

create table wishlist(
	u_id int,
	p_id int,
	primary key (u_id,p_id),
	FOREIGN KEY(u_id) REFERENCES user(id),
	FOREIGN KEY(p_id) REFERENCES product(id)
);


insert into user(name,password) values ("xuefei","123");			
insert into user(name,password) values ("wang","1");
insert into user(name,password) values ("justice","2");
insert into user(name,password) values ("nanda","3");
insert into user(name,password) values ("MacCree","highnoon");


insert into product(name, price,description) values("Steve Jobs",49.99,"a book shows what is Steve Jobs");
insert into product(name, price,description) values("Java",33.07,"OCA Java SE 7 Programmer I Certification Guide");
insert into product(name, price,description) values("sale",7.99,"The Three-Body Problem");
insert into product(name, price,description) values("C++",61.56,"The C++ Programming Language, 4th Edition");
insert into product(name, price,description) values("JavaScript",30.15,"JavaScript and JQuery: Interactive Front-End Web Development");
insert into product(name, price,description) values("MySql",41.07,"MySQL (5th Edition) (Developer's Library)");