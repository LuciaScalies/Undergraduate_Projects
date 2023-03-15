use lucias;
#EMPLOYEES(EID(PK), DID(FK), First_Name, Last_Name, BirthDate, Salary, VacationDays, HireDate)
#DEPARTMENTS(DID(PK), Department, PhoneNumber)
#DID in EMPLOYEES must exist in DEPARTMENTS

drop table tbl_employees;
drop table tbl_departments;

create table tbl_departments(
	DID int unsigned not null auto_increment primary key, #Surrogate Key, Department ID
    Department varchar(40) not null,
    PhoneNumber varchar(12) not null #Phone Number
);

insert into tbl_departments values(null, 'Accounting', '111-222-3333');
insert into tbl_departments values(null, 'Human Resources', '222-333-1111');
insert into tbl_departments values(null, 'Research and Development', '999-999-1234');

create table tbl_employees(
	EID int unsigned not null auto_increment primary key, #Surrogate Key, Employee ID
    DID int unsigned not null, #Foreign Key, Department ID
    First_Name varchar(20) not null,
    Last_Name varchar(20) not null,
    BirthDate date not null,
    Salary decimal(13, 4) unsigned not null,
    VacationDays decimal(4, 1) unsigned not null,
    HireDate date not null,
    constraint FK_EMPS_DEP foreign key (DID) references tbl_departments(DID)
);

insert into tbl_employees values(null, 1, 'Bob', 'Cobb', '1990-01-01', 50000, 7.5, '2010-01-10');
insert into tbl_employees values(null, 1, 'Jerry', 'Berry', '1980-02-02', 60000, 10.5, '2002-06-01');
insert into tbl_employees values(null, 2, 'Ann Veal', 'Delay', '1970-03-03', 70000, 15.0, '1995-07-07');
insert into tbl_employees values(null, 2, 'Walt', 'John', '1960-04-04', 95000, 17.0, '1985-10-20');
insert into tbl_employees values(null, 1, 'Sara', 'Shore', '1991-08-08', 55000, 5.5, '2011-03-21');
insert into tbl_employees values(null, 3, 'Jane', 'Jones', '1992-03-05', 35000, 10.0, '2013-10-04');
update tbl_employees set Salary = 60000.01 where EID = 2;
update tbl_employees set VacationDays = 15.2 where EID = 3;
delete from tbl_employees where EID = 4;

select * from tbl_employees;
select * from tbl_departments;

#Query A displays all employmees sorted by salary in descending order
select First_Name, Last_Name, Salary
	from tbl_employees
    order by Salary desc;

#Query B displays all employees whose salary is greater than $50,000 sorted by hire date in reverse chronological order
select Last_Name, First_Name, HireDate, Salary
	from tbl_employees
    where Salary > 50000
    order by HireDate desc;

#Query C displays all employees sorted by department, with departments in alphabetical order
select tbl_employees.First_Name, tbl_employees.Last_Name, tbl_departments.Department
	from tbl_departments
    inner join tbl_employees
    on tbl_departments.DID = tbl_employees.DID
    order by tbl_departments.Department asc;