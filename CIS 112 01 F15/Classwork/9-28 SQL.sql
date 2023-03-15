use lucias;
#Relational Notation
#DEPARTMENT(DID, department)
#EMPLOYEE(EID, fname, lname, salary, birthdate, DID)
#DID in EMPLOYEE must exist in DEPARTMENT
create table tbl_department(
	DID int unsigned not null auto_increment primary key, #surrogate Primary Key
    department varchar(20) not null
);

insert into tbl_department values(null, 'Accounting');
insert into tbl_department values(null, 'Engineering');

select * from tbl_department;

create table tbl_employee(
	EID int unsigned not null auto_increment primary key, #surrogate Primary Key
    fname varchar(20) not null,
    lname varchar(20) not null,
    salary decimal(13,4) not null,
    birthdate date not null,
    vacation_days int not null default 0,
    DID int unsigned not null,
    constraint FK_EMP_DEP foreign key (DID) references tbl_department(DID)
);

insert into tbl_employee values(null, 'Bob', 'Cobb', 50000.99, '1990-01-01', 25, 1);

select * from tbl_employee;

drop table tbl_employee;