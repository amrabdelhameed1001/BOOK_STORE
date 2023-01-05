drop Schema BOOK_STORE;
 CREATE SCHEMA BOOK_STORE;
 use BOOK_STORE;
 
 CREATE TABLE user_info(
 userID INTEGER,
 userName VARCHAR(50) ,
 Password VARCHAR(100),
 Lname VARCHAR(30),
 Fname VARCHAR(20), 
 Email VARCHAR(30) ,
 Phone CHAR(11),
 Address VARCHAR(100),
 type VARCHAR(8),
 primary key(userID),
 unique (userName),
 unique (Email)
 );

CREATE TABLE PUBLISHER(
  Name varchar(50),
  Address varchar(100),
  Phone varchar(25),
  PRIMARY KEY(Name)
);
 
 
 --  CREATE TABLE user_types(
--  userName VARCHAR(50),
--  type VARCHAR(8),
--  
--  );
 
 CREATE TABLE BOOK (
  ISBN INTEGER PRIMARY KEY,
  Title varchar(30),
  Author varchar(30),
  Publisher_name varchar(30),
  Year CHAR(4),
  Selling_price float,
  Category varchar(20),
  foreign key (Publisher_name) references PUBLISHER (Name) on update cascade on delete set null
  );
  
  
 CREATE TABLE done_orders(
 userID INTEGER,
 bookISBN INTEGER,
 bookQuantity INTEGER,
 price FLOAT,
 Date date,
   foreign key (userID) references user_info (userID) on update cascade on delete set null,
   foreign key (bookISBN) references book (ISBN) on update cascade on delete set null
 );
 

CREATE TABLE AUTHORS(
  Book_id int,
  Author_name varchar(50),
  primary key (Author_name),
  foreign key (Book_id) references BOOK(ISBN) on update cascade
);


CREATE TABLE BOOK_ORDER(
  Order_id INTEGER,
  Book_id int,
  quantity int,
  primary key(Order_id),
  foreign key (Book_id) references BOOK(ISBN) on update cascade
);

 CREATE TABLE shopping_cart(
 customerID INTEGER,
 bookISBN INTEGER,
 bookQuantity INTEGER,
 foreign key (customerID) references user_info(userID) on update cascade,
 foreign key (bookISBN) references BOOK(ISBN) on update cascade
 );
 
