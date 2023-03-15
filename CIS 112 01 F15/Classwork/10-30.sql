use lucias;

drop table if exists tblPlayer;
drop table if exists tblTeam;

create table tblTeam(
	team_ID int unsigned not null auto_increment primary key, #Surrogate Key
    team_name varchar(20) not null
);

create table tblPlayer(
	player_ID int unsigned not null auto_increment primary key, #Surrogate Key
    team_ID int unsigned not null, #Foreign Key
    constraint FK_PLA_TEA foreign key (team_ID) references tblTeam(team_ID)
);

#qry1
select t.team_name, count(p.team_ID)
from tblTeam t
left join tblPlayer p
on t.team_ID = p.team_ID
where t.team_name like 'G%'
group by t.team_name
having count(p.team_ID) < 10
order by count(p.team_ID) asc;

#qry2
select d.Department, e.Last_Name, e.First_Name
from tbl_departments d
left join tbl_employees e
on d.DID = e.DID
group by d.Department asc, e.Last_Name asc, e.First_Name asc;