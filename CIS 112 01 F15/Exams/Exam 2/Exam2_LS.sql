#Exam 2
#Lucia Scalies

#Question 3
create table tbl_Product(
	ProductID int unsigned auto_increment not null primary key, #Primary Key, Surrogate Key
	ProductName varchar(15) not null,
	Price decimal(13, 4) unsigned not null
);

create table tbl_Vendor(
	VendorID int unsigned auto_increment not null primary key, #Primary Key, Surrogate Key
	VendorName varchar(25) not null,
	Contact_Phone varchar(13) not null #Phone Number (000)000-0000
);

create table tbl_Purchase(
	PurchaseID int unsigned auto_increment not null primary key, #Primary Key, Surrogate Key
	Purchase_Date date() not null,
	VendorID int unsigned not null, #Foreign Key
	constraint FK_PUR_VEN foreign key (VendorID) references (tbl_Vendor.VendorID)
);

create table tbl_Purchase-Products(
	PurchaseID int unsigned not null, #Primary Key, Foreign Key
	ProductID int unsigned not null, #Primary Key, Foreign Key
	Quantity int unsigned not null,
	constraint PK_PUR-PRO primary key (PurchaseID,ProductID),
	constraint FK_PUR-PRO_PUR foreign key (PurchaseID) references (tbl_Purchase.PurchaseID),
	constraint FK_PUR-PRO_PRO foreign key (ProductID) references (tbl_Product.ProductID)
);

#Question 4
select tbl_department.DepartmentName, count(tbl_course.dept_id), sum(tbl_course.credits)
from tbl_department d
inner join tbl_course c
on d.dept_id = c.dept_id
group by d.DepartmentName
having count(c.dept_id) > 5 and sum(c.credits) > 16
order by sum(c.credits) asc;

#Question 5
select tbl_department.DepartmentName, tbl_course.CourseName
from tbl_department d
left join tbl_course c
on d.dept_id = c.dept_id
where d.DepartmentName like 'C%'