#AUTHORS(AID(PK), First_Name, Last_Name);
#BOOKS(BID(PK), AID(FK), Title);
#AID in BOOKS must exist in AUTHORS;
#LIBRARIES(LID(PK), Name);
#LIBRARY_INVENTORY(IID(PK), BID(FK), LID(FK));
#BID in LIBRARY_INVENTORY must exist in BOOKS;
#LID in LIBRARY_INVENTORY must exist in LIBRARIES;
use lucias;

drop table tbl_library_inventory;
drop table tbl_libraries;
drop table tbl_books;
drop table tbl_authors;

create table tbl_authors(
	AID int unsigned not null auto_increment primary key, #Surrogate Key
    First_Name varchar(20) not null,
    Last_Name varchar(20) not null
);

insert into tbl_authors values(null, 'Herman', 'Melville');
insert into tbl_authors values(null, 'Alice', 'Walker');
alter table tbl_authors add column Birthdate date not null;
update tbl_authors set Birthdate = '1819-08-01' where AID = 1;
update tbl_authors set Birthdate = '1944-02-09' where AID = 2;
select * from tbl_authors;

create table tbl_books(
	BID int unsigned not null auto_increment primary key, #Surrogate Key
    AID int unsigned not null, #Foreign Key
    Title varchar(20),
    constraint FK_BOO_AUT foreign key (AID) references tbl_authors(AID)
);

insert into tbl_books values(null, 1, 'Moby Dick');
insert into tbl_books values(null, 1, 'Billy Budd');
insert into tbl_books values(null, 2, 'The Color Purple');
alter table tbl_books add column Publication_Date date not null;
update tbl_books set Publication_Date = '1854-01-01' where BID = 1;
update tbl_books set Publication_Date = '1924-01-01' where BID = 2;
update tbl_books set Publication_Date = '1982-01-01' where BID = 3;
select * from tbl_books;

create table tbl_libraries(
	LID int unsigned not null auto_increment primary key, #Surrogate Key
    Name varchar(20) not null
);

insert into tbl_libraries values(null, 'Downtown Library');
insert into tbl_libraries values(null, 'Union Library');
alter table tbl_libraries add column Address varchar(50) not null;
alter table tbl_libraries add column City varchar(20) not null;
alter table tbl_libraries add column State varchar(2) not null;
alter table tbl_libraries add column Zip_Code varchar(5) not null;
update tbl_libraries set Address = '34 Elm Street' where LID = 1;
update tbl_libraries set City = 'Newton' where LID = 1;
update tbl_libraries set State = 'PA' where LID = 1;
update tbl_libraries set Zip_Code = '15530' where LID = 1;
update tbl_libraries set Address = '18 Walnut Street' where LID = 2;
update tbl_libraries set City = 'York' where LID = 2;
update tbl_libraries set State = 'PA' where LID = 2;
update tbl_libraries set Zip_Code  = '15430' where LID = 2;
select * from tbl_libraries;

create table tbl_library_inventory(
	IID int unsigned not null auto_increment primary key, #Surrogate Key
    BID int unsigned not null, #Foreign Key
    LID int unsigned not null, #Foreign Key
    constraint FK_LIB_INV_BOO foreign key (BID) references tbl_books(BID),
    constraint FK_LIB_INV_LIB foreign key (LID) references tbl_libraries(LID)
);

insert into tbl_library_inventory values(null, 1, 1);
insert into tbl_library_inventory values(null, 1, 2);
insert into tbl_library_inventory values(null, 2, 2);
insert into tbl_library_inventory values(null, 3, 2);
insert into tbl_library_inventory values(null, 3, 1);
select * from tbl_library_inventory;