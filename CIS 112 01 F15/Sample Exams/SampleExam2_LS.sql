Lucia Scalies
#Question 3
create table tbl_Employee(
	EmployeeID int unsigned not null auto increment primary key,
	FirstName varchar(20) not null,
	LastName varchar(20) not null
);

create table tbl_Department(
	DepartmentID int unsigned not null auto increment primary key,
	Department varchar(25) not null
);

create table tbl_Project(
	ProjectID int unsigned not null auto increment primary key,
	Project_Name varchar(25) not null,
	Project_Date date not null,
	DepartmentID int unsigned not null, #Foreign Key
	Budget decimal(13,4) unsinged not null, #USD
	constraint FK_PRO_DEP foreign key (DepartmentID) references tbl_Department(DepartmentID)
);

create table tbl_Project-Employee(
	ProjectID int unsigned not null, #Foreign Key
	EmployeeID int unsigned not null, #Foreign Key
	constraint PK_Project-Employee primary key (ProjectID, EmployeeID),
	constraint FK_PRO-EMP_PRO foreign key (ProjectID) references tbl_Project(ProjectID),
	constraint FK_PRO-EMP_EMP foreign key (EmployeeID) references tbl_Employee(EmployeeID)
);

#Question 4
select tblFriends.LastName, tblFriends.FirstName, tblClothes.Name
from tblFriends F
left join tblClothes C
on C.Friend_ID = F.Friend_ID
order by F.LastName asc, C.Description asc;

#Question 5
select tblFriends.LastName, tblClothes.Description, tblClothes.Price, tblClothes.Purchase_Date
from tblFriends F
inner join tblClothes C
on C.Friend_ID = F.Friend_ID
where F.LastName = 'Brown' and F.FirstName = 'Alice'
or F.LastName = 'Harris' and F.FirstName = 'George'
or C.Name = 'shirt' and C.Price > 5;

#Question 6
select tblRetailers.Name, sum(tblSales.Retail_Price - tblSales.Wholesale_Price)
from tblRetailers R
inner join tblSales S
on R.UPC = S.UPC
group by R.Name
having sum(S.Retail_Price - S.Wholesale_Price) >= 10000
order by R.Name asc, sum(S.Retail_Price - S.Wholesale_Price) desc;