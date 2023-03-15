#Relational Notation
#ADVISOR(AID, first_name, last_name)
#STUDENT(SID, first_name, last_name, birth_date, credits, gpa, is_full_time, AID)
#AID in STUDENT must exist in ADVISOR
#COURSE(CID(PK), CourseCode, CourseSection, CourseName, Credits)
#ENROLLMENT(SID(PK,FK), CID(PK,FK))
use lucias;

create table tbl_advisor(
	AID int unsigned not null auto_increment primary key, #Surrogate Primary Key
    first_name varchar(20) not null,
    last_name varchar(20) not null
);

insert into tbl_advisor values(null, 'Bob', 'Cobb');
insert into tbl_advisor values(null, 'Jill', 'Skill');
select * from tbl_advisor;

create table tbl_student(
	SID int unsigned not null auto_increment primary key, #Surrogate Primary Key
    first_name varchar(20) not null,
    last_name varchar(20) not null,
    birth_date date not null,
    credits int unsigned not null default 0,
    gpa decimal(4,3) unsigned not null default 0.0,
    is_full_time bit not null, #full time = 1
    AID int unsigned not null,
    constraint FK_STU_ADV foreign key (AID) references tbl_advisor(AID)
);

insert into tbl_student values(null, 'Mary', 'Contrary', '1993-07-04', 120, 3.75, 1, 1);
insert into tbl_student values(null, 'Josh', 'Posh', '1997-01-22', 0, 0.0, 1, 2);
select * from tbl_student;

create table tbl_course(
	CID int unsigned not null auto_increment primary key, #surrogate primary key
    CourseCode varchar(6) not null,
    CourseSection int unsigned not null,
    CourseName varchar(30) not null,
    Credits int unsigned not null default 4
);

insert into tbl_course (CourseCode, CourseSection, CourseName) values('CIS112', 1, 'Database Concepts');
insert into tbl_course (CourseCode, CourseSection, CourseName) values('ENG112', 1, 'Honors Composition');
select * from tbl_course;

create table tbl_enrollment(
	SID int unsigned not null,
    CID int unsigned not null,
    constraint PK_enrollment primary key (SID, CID),
    constraint FK_ENR_STU foreign key (SID) references tbl_student(SID),
    constraint FK_ENR_COU foreign key (CID) references tbl_course(CID)
);

insert into tbl_enrollment values(1, 1);
insert into tbl_enrollment values(1, 2);
select * from tbl_enrollment;