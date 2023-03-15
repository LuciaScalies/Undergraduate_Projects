#Lucia Scalies
#CIS 112 Database Concepts
#HW 3
#October 16, 2015
#Relational Notation
#CUSTOMERS(CID(PK), First_Name, Last_Name, Email, WorkEmail)
#PHONENUMBERS(Phone(PK), Phone_Type, CID(FK))
#ARTIST(AID(PK), Artist_First_Name, Artist_Last_Name)
#PAINTINGS(PID(PK), Painting, DateMade, AID(FK), PaintingMedium, Length, Width)
#PURCHASES(PID(PK, FK), CID(PK, FK), PurchaseDate, Price, Tax)
#CID in PHONENUMBERS must exist in CUSTOMERS
#AID in PAINTINGS must exist in ARTIST
#PID in PURCHASES must exist in PAINTINGS
#CID in PURCHASES must exist in CUSTOMERS
use lucias;

#drops tables
drop table tbl_purchases;
drop table tbl_phonenumbers;
drop table tbl_paintings;
drop table tbl_artist;
drop table tbl_customers;

#creates table tbl_customers
create table tbl_customers( #Stores customer information
	CID int unsigned not null auto_increment primary key, #Surrogate Key, Customer ID
    First_Name varchar(15) not null, #Customer's First Name
    Last_Name varchar(15) not null, #Customer's Last Name
    Email varchar(60) not null, #Customer's E-mail address
    WorkEmail bit default 0 not null #If Customer's E-mail address is their work email WorkEmail = 1, otherwise = 0
);

#populates tbl_customers with data
insert into tbl_customers values(null, 'John', 'Baker', 'jb@moma.net', 1);
insert into tbl_customers values(null, 'Johan', 'Smythe', 'js@gmail.com', 0);
insert into tbl_customers values(null, 'Alex', 'Diaz', 'ad@foggmuseum.net', 1);
insert into tbl_customers values(null, 'Nick', 'Early', 'ne@yahoo.com', 0);
insert into tbl_customers values(null, 'Don', 'Coover', 'dc@carnegie.net', 1);
insert into tbl_customers values(null, 'Ann Veal', 'Delay', 'avd@gmail.com', 0);

#creates table tbl_phonenumbers
create table tbl_phonenumbers( #Stores customer's phone number information
	Phone varchar(12) not null primary key, #Phone Number stored in ###-###-#### format
    Phone_Type varchar(10) not null, #Denotes the type of the Phone Number
    CID int unsigned not null, #Foreign Key, Customer ID
    constraint FK_PHN_CUS foreign key (CID) references tbl_customers(CID) #Enforces referential integrity
);

#populates tbl_phonenumbers with data
insert into tbl_phonenumbers values('101-212-3333', 'Home', 5);
insert into tbl_phonenumbers values('123-456-7891', 'Work', 2);
insert into tbl_phonenumbers values('145-236-0147', 'Cell', 5);
insert into tbl_phonenumbers values('212-444-1965', 'Work', 1);
insert into tbl_phonenumbers values('213-345-3126', 'Cell', 4);
insert into tbl_phonenumbers values('353-535-0912', 'Work', 5);
insert into tbl_phonenumbers values('426-111-2101', 'Other', 3);
insert into tbl_phonenumbers values('432-543-1234', 'Other', 6);
insert into tbl_phonenumbers values('486-082-1379', 'Home', 3);
insert into tbl_phonenumbers values('999-867-5309', 'Other', 1);

#creates table tbl_artist
create table tbl_artist(#Stores artist information
	AID int unsigned not null auto_increment primary key, #Surrogate Key, Artist ID
    Artist_First_Name varchar(15) not null, #Artist's First Name
    Artist_Last_Name varchar(15) not null #Artist's Last Name
);

#populates tbl_artist with data
insert into tbl_artist values(null, 'Claude', 'Monet');
insert into tbl_artist values(null, 'Paul', 'Cezanne');
insert into tbl_artist values(null, 'Pierre-August', 'Renoir');
insert into tbl_artist values(null, 'Pablo', 'Picasso');
insert into tbl_artist values(null, 'Edgar', 'Degas');
insert into tbl_artist values(null, 'Henri', 'Matisse');
insert into tbl_artist values(null, 'Winslow', 'Homer');

#adds the birthdate column to tbl_artist
alter table tbl_artist add birthdate date not null;

#updates tbl_artist by adding every artist's birthdate
update tbl_artist set birthdate = '1840-11-14' where AID =  1;
update tbl_artist set birthdate = '1841-02-25' where AID = 3;
update tbl_artist set birthdate = '1839-01-19' where AID = 2;
update tbl_artist set birthdate = '1833-02-24' where AID = 7;
update tbl_artist set birthdate = '1881-10-25' where AID = 4;
update tbl_artist set birthdate = '1834-07-19' where AID = 5;
update tbl_artist set birthdate = '1869-12-31' where AID = 6;

#creates table tbl_paintings
create table tbl_paintings( #Stores Painting Information
	PID int unsigned not null auto_increment primary key, #Surrogate Key, Painting ID
    Painting varchar(35) not null, #Name of the Painting
    DateMade date not null, #Year Painting was made
    AID int unsigned not null, #Artist ID, Foreign Key, associates painting with an artist
    PaintingMedium varchar(35) not null, #Medium used
    Length decimal(18, 1), #In Centimeters
    Width decimal(18, 1), #in Centimeters
    constraint FK_PAI_ART foreign key (AID) references tbl_artist(AID)
);

#populates tbl_paintings with data
insert into tbl_paintings values(null, 'Women in a Garden', '1867-01-01', 1, 'Oil on Canvas', 82.0, 100.0);
insert into tbl_paintings values(null, 'Self-Portrait', '1895-01-01', 2, 'Watercolor', 26.0, 22.0);
insert into tbl_paintings values(null, 'The Theater Box', '1874-01-01', 3, 'Oil on Canvas', 80.0, 63.5);
insert into tbl_paintings values(null, 'Portrait of Gertrude Stein', '1906-01-01', 4, 'Oil on Canvas', 100.0, 81.3);
insert into tbl_paintings values(null, 'The Dance Class', '1876-01-01', 5, 'Oil on Canvas', 85.0, 75.0);
insert into tbl_paintings values(null, 'Impression Sunrise', '1872-01-01', 1, 'Oil on Canvas', 48.0, 63.0);
insert into tbl_paintings values(null, 'Pyramid of Skulls', '1901-01-01', 2, 'Oil on Canvas', 39.0, 46.5);
insert into tbl_paintings values(null, 'Massacre in Korea', '1954-01-01', 4, 'Oil on Plywood', 110.0, 210.0);
insert into tbl_paintings values(null, 'Woman with a Hat', '1905-01-01', 6, 'Oil on Canvas', 39.4, 59.7);
insert into tbl_paintings values(null, 'The Blue Boat', '1892-01-01', 7, 'Watercolor', 15.3, 21.5);
insert into tbl_paintings values(null, 'Self-Portrait', '1907-01-01', 4, 'Oil on Canvas', null, null);

#inserts the records from question 8 into tbl_paintings
#insert into tbl_paintings values(null, 'Guernica', '1937-01-01', 4, 'Oil on Canvas', 349, 776);
#insert into tbl_paintings values(null, 'Window at Tangier', '1912-01-01', 6, 'Oil on Canvas', null, null);

#creates table tbl_purchases
create table tbl_purchases( #Tracks the Relationship between Customers and Paintings
	PID int unsigned not null, #Painting ID, Concatenated Primary Key, Foreign Key
    CID int unsigned not null, #Customer ID, Concatenated Primary Key, Foreign Key
    PurchaseDate date not null, #Date of Purchase, YYYY-MM-DD
    Price decimal(13, 4) not null, #Currency in USD
    Tax decimal(13, 4) not null, #Currency in USD
    constraint PK_purchases primary key (PID, CID), #Creates Concatenated Primary Key
    constraint FK_PUR_PAI foreign key (PID) references tbl_paintings(PID), #Enforces Referential Integrity
    constraint FK_PUR_CUS foreign key (CID) references tbl_customers(CID) #Enforces Referential Integrity
);

#populates tbl_purchases with data
insert into tbl_purchases values(1, 1, '2008-10-16', 60000.00, 3000.00);
insert into tbl_purchases values(2, 1, '2007-07-13', 30000.00, 5000.00);
insert into tbl_purchases values(3, 2, '2009-09-17', 80000.00, 10000.00);
insert into tbl_purchases values(4, 3, '2010-06-26', 100000.00, 25000.00);
insert into tbl_purchases values(5, 3, '2009-08-20', 80000.00, 10000.00);
insert into tbl_purchases values(6, 4, '2008-01-16', 250000.00, 50000.00);
insert into tbl_purchases values(7, 4, '2007-03-20', 150000.00, 50000.00);
insert into tbl_purchases values(8, 4, '2006-02-05', 45000.00, 5000.00);
insert into tbl_purchases values(9, 4, '2009-04-03', 200000.00, 75000.00);
insert into tbl_purchases values(10, 4, '2009-05-29', 20000.00, 2500.00);
insert into tbl_purchases values(11, 5, '2010-11-09', 90000.00, 20000.00);

#displays all records from each table created above
select * from tbl_customers;
select * from tbl_phonenumbers;
select * from tbl_artist;
select * from tbl_paintings;
select * from tbl_purchases;