#ITEM(IID(PK), ItemDesc, Price)
#CUSTOMER(CID(PK), FirstName, LastName)
#ORDER(OID(PK), CID(FK), OrderDate)
#CID in ORDER must exist in CID in CUSTOMER
#ORDER_ITEM(OID(PK,FK), IID(PK,FK), Quantity)
#OID in ORDER_ITEM must exist in OID in ORDER
#IID in ORDER_ITEM must exist in IID in ITEM
use lucias;

create table tbl_item(
	IID int unsigned not null auto_increment primary key,
    ItemDesc varchar(50) not null,
    Price decimal(13, 4) not null
);

insert into tbl_item values(null, 'Unobtainium', 50000000);
insert into tbl_item values(null, 'Mega Ring Bases no Keystone', 86.25);
select * from tbl_item;

create table tbl_customer(
	CID int unsigned not null auto_increment primary key,
    FirstName varchar(20) not null,
    LastName varchar(20) not null
);

insert into tbl_customer values(null, 'Steve', 'Rodgers');
insert into tbl_customer values(null, 'Steven', 'Stone');
select * from tbl_customer;

create table tbl_order(
	OID int unsigned not null auto_increment primary key,
    CID int unsigned not null,
    OrderDate date not null,
    constraint FK_ORD_CUS foreign key (CID) references tbl_customer(CID)
);

insert into tbl_order values(null, 1, '1942-07-04');
insert into tbl_order values(null, 2, '2014-11-23');
select * from tbl_order;

create table tbl_order_item(
	OID int unsigned not null,
    IID int unsigned not null,
    Quantity int unsigned not null,
    constraint PK_order_item primary key (OID, IID),
    constraint FK_ORD_ITM_ORD foreign key (OID) references tbl_order(OID),
    constraint FK_ORD_ITM_ITM foreign key (IID) references tbl_item(IID)
);

insert into tbl_order_item values(1, 1, 1);
insert into tbl_order_item values(2, 2, 7);
select * from tbl_order_item;