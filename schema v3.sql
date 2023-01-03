  drop SCHEMA BOOK_STORE;
 CREATE SCHEMA BOOK_STORE;
use book_store;
CREATE TABLE PUBLISHER(
  Name varchar(50),
  Address varchar(100),
  Phone varchar(25),
  PRIMARY KEY(Name)
);


CREATE TABLE BOOK (
  ISBN INTEGER PRIMARY KEY,
  Title varchar(30),
  Author varchar(30),
  Publisher_name varchar(30),
  Publication_Year date,
  Selling_price float,
  Category varchar(20) ,
  in_stock int,
  threshold int,
  foreign key (Publisher_name) references PUBLISHER (Name) on update cascade on delete set null
  );

-- CREATE TABLE AUTHORS(
--   Book_id int,
--   Author_name varchar(50),
--   primary key (Author_name),
--   foreign key (Book_id) references BOOK(ISBN) on update cascade
-- );


-- CREATE TABLE BORROWER(
--   Card_no int,
--   Name varchar(50),
--   Address varchar(100),
--   Phone char(9),
--   PRIMARY KEY(Card_no)
-- );

CREATE TABLE BOOK_ORDER(
  Order_id INTEGER PRIMARY key,
  Book_id int,
  quantity int,
  foreign key (Book_id) references BOOK(ISBN) on update cascade
--  foreign key (Borrower_id) references BORROWER(Card_no) on update cascade
);

-- operations

-- 1- add new book
DELIMITER //
create procedure Add_new_books (in isbn INTEGER, in title varchar(30),in Author varchar(30),
  in Publisher_name varchar(30),
  in Publication_Year date,
  in Selling_price float,
  in Category varchar(20), in instock int,in threshold int )   
begin 
	
    insert into BOOK values(isbn,title,author, publisher_name,publication_year,selling_price,
    category, instock,threshold);

 end
 //
DELIMITER ;
insert into publisher values ("aly","aff","012");
call Add_new_books(12345,"good","ali","aly","2020-1-1",120.5,"Science",4,3);


-- 2- modify existing book


DELIMITER // 
CREATE TRIGGER  modify_existing_books  before update ON BOOK 
FOR EACH ROW
BEGIN
IF New.in_stock < 0   THEN  
SIGNAL SQLSTATE '45000'
SET MESSAGE_TEXT = 'the quantity of a book in stock can not be negative.';
END IF;
END;
//
DELIMITER ; 

-- update book set in_stock = in_stock -7 ;


-- 3- place orders on books



DELIMITER // 
CREATE TRIGGER  place_orders_on_books  after update ON BOOK 
FOR EACH ROW
BEGIN
declare ordernum int;
select count(order_id)+1 from book_order into ordernum;
IF New.in_stock < new.threshold   THEN  
insert into BOOK_ORDER values(ordernum,new.isbn,new.threshold - new.in_stock);
END IF;
END;
//
DELIMITER ; 

update book set in_stock = in_stock - 2 where isbn = 1234;



-- 4- confirm orders

DELIMITER // 
CREATE TRIGGER  confirm_orders  before delete ON BOOK_ORDER
FOR EACH ROW
BEGIN
update book set in_stock = in_stock + old.quantity where isbn = old.Book_id;
END;
//
DELIMITER ; 
delete from book_order where Order_id = 1;



-- 5- search for books

delimiter //
create procedure Search_a_book(in isbn INTEGER, in title varchar(30)) 
begin 
    select *from book where book.ISBN =isbn and book.Title = title; 
 end 
                      //
                      DELIMITER ;
                      
call search_a_book(123,"good"); 

delimiter //
create procedure Search_by_category(in thecategory varchar(30)) 
begin 
    select *from book where book.Category = thecategory; 
 end 
                      //
                      DELIMITER ;
                      
call search_by_category("geography"); 

delimiter //
create procedure Search_by_author(in author varchar(30)) 
begin 
    select *from book where book.author = author; 
 end 
                      //
                      DELIMITER ;
                      
call search_by_author("ali"); 

delimiter //
create procedure Search_by_publisher(in publisher varchar(30)) 
begin 
    select *from book where book.publisher_name = publisher; 
 end 
                      //
                      DELIMITER ;
                      
call search_by_publisher("aly"); 
