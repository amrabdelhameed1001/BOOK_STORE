-- drop SCHEMA BOOK_STORE;
 CREATE SCHEMA BOOK_STORE;
 use BOOK_STORE;
 
 CREATE TABLE user_info(
 customerID INTEGER,
 userName VARCHAR(50),
 Password VARCHAR(100),
 Lname VARCHAR(30),
 Fname VARCHAR(20), 
 Email VARCHAR(30),
 Phone CHAR(11),
 Address VARCHAR(100)
 );
 
 CREATE TABLE shopping_cart(
 customerID INTEGER,
 bookISBN INTEGER,
 bookQuantity INTEGER
 );
 
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

CREATE TABLE PUBLISHER(
  Name varchar(50),
  Address varchar(100),
  Phone varchar(25),
  PRIMARY KEY(Name)
);

CREATE TABLE AUTHORS(
  Book_id int,
  Author_name varchar(50),
  primary key (Author_name),
  foreign key (Book_id) references BOOK(ISBN) on update cascade
);


CREATE TABLE BORROWER(
  Card_no int,
  Name varchar(50),
  Address varchar(100),
  Phone char(9),
  PRIMARY KEY(Card_no)
);

CREATE TABLE BOOK_ORDER(
  Order_id INTEGER PRIMARY key,
  Book_id int,
  Borrower_id int,
  foreign key (Book_id) references BOOK(ISBN) on update cascade,
  foreign key (Borrower_id) references BORROWER(Card_no) on update cascade
);
