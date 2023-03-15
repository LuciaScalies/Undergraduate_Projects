use lucias;

#Relational Notation
#TEAM(TeamID(PK), TeamName, TeamCity);
#PLAYER(PlayerID(PK), FirstName, LastName, JerseyNumber, DraftDate, TeamID(FK));
#TeamID in PLAYER must exist in TEAM

drop table tbl_player;
drop table tbl_team;

create table tbl_team(
	TeamID int unsigned not null auto_increment primary key,
    TeamName varchar(20) not null,
    TeamCity varchar(50) not null
);

insert into tbl_team values(null, 'Steelers', 'Pittsburgh');
insert into tbl_team values(null, 'Eagles', 'Philadelphia');
insert into tbl_team values(null, 'Bills', 'Buffalo');

create table tbl_player(
	PlayerID int unsigned not null auto_increment primary key,
    FirstName varchar(20) not null,
    LastName varchar(20) not null,
    JerseyNumber varchar(2) not null, #2-Digit Number, but has no value in calculations
    DraftDate date not null,
    TeamID int unsigned not null,
    constraint FK_PLA_TEA foreign key (TeamID) references tbl_team(TeamID)
);

insert into tbl_player values(null, 'Mike', 'Adams', '76', '2012-01-01', 1);
insert into tbl_player values(null, 'Will', 'Allen', '26', '2004-01-01', 1);
insert into tbl_player values(null, 'Nate', 'Allen', '29', '2010-01-01', 2);
insert into tbl_player values(null, 'Brent', 'Celek', '87', '2007-01-01', 2);
insert into tbl_player values(null, 'Shecky', 'Green', '14', '2007-02-02', 2);

#Query A displays Team Name, Player Last Name, Player First Name, and draft date only for teams with players
#Query A continued sorted by Team Name in alphabetical order and draft date in reverse chronological order
select tbl_team.TeamName, tbl_player.LastName, tbl_player.FirstName, tbl_player.DraftDate
	from tbl_player
	inner join tbl_team
	on tbl_team.TeamID = tbl_player.TeamID
    order by tbl_team.TeamName asc, tbl_player.DraftDate desc;
    
#Query B displays Team Name, Player Last Name, Player First Name, and draft date only for all teams, regardless of if they have players
#Query B continued sorted by Team Name in alphabetical order and draft date in reverse chronological order
select tbl_team.TeamName, tbl_player.LastName, tbl_player.FirstName, tbl_player.DraftDate
	from tbl_team
    left join tbl_player
    on tbl_team.TeamID = tbl_player.TeamID
    order by tbl_team.TeamName asc, tbl_player.DraftDate desc;
    
#Query C displays player data for those on the Pittsburgh Steelers drafted after 2010
select * from tbl_player where TeamID = 1 and DraftDate > '2010-01-01';

#Query D displays player Last Name, First Name, and Jersey Number for all players drafted between 2009 and the present
select LastName, FirstName, JerseyNumber
	from tbl_player
    where DraftDate > '2009-01-01';
    
#Query E displays Team Name and a count of players on each team, even if the team has no players. Data is sorted from highest count to lowest count
select tbl_team.TeamName, count(tbl_player.TeamID)
	from tbl_team
    left join tbl_player
    on tbl_team.TeamID = tbl_player.TeamID
    group by tbl_team.TeamName
    order by count(tbl_player.TeamID) desc;
    
#Query F displays Team Name and a count of players on each team, but only teams which have players.
select tbl_team.TeamName, count(tbl_player.TeamID)
	from tbl_team
    inner join tbl_player
    on tbl_team.TeamID = tbl_player.TeamID
    group by tbl_team.TeamName;
    
#Query G displays Team Name and a count of players on each team, even if the team had no players. Data is sorted from lowest count to highest count
select tbl_team.TeamName, count(tbl_player.TeamID)
	from tbl_team
    left join tbl_player
    on tbl_team.TeamID = tbl_player.TeamID
    group by tbl_team.TeamName
    order by count(tbl_player.TeamID) asc;
    
#Query H displays Team Name and a count of players on each team if the number of players > 2. Data is sorted from lowest count to highest count
select tbl_team.TeamName, count(tbl_player.TeamID)
	from tbl_team
    left join tbl_player
    on tbl_team.TeamID = tbl_player.TeamID
    group by tbl_team.TeamName
    having count(tbl_player.TeamID > 2)
    order by count(tbl_player.TeamID) asc;
    
#Query I displays all players with a last name beginning with 'a'
select * from tbl_player where LastName like 'a%';

#Query J displays all players with a last name containing 'e' that play for the Pittsburg Steelers
select * from tbl_player where LastName like '%e%' and TeamID = 1;

#Query K displays all players with a first name ending in 'e' drafted after 01/01/2009
select * from tbl_player where FirstName like '%e' and DraftDate > '2009-01-01';

#alters the player table to include a Salary column
alter table tbl_player add Salary decimal(13, 4) not null;

#updates the player table to include values in the Salary column
update tbl_player set Salary = 200000 where PlayerID = 1;
update tbl_player set Salary = 500000 where PlayerID = 2;
update tbl_player set Salary = 500000 where PlayerID = 3;
update tbl_player set Salary = 400000 where PlayerID = 4;
update tbl_player set Salary = 40000 where PlayerID = 5;

#Query L displays TeamName, number of players, and average salary of players, but only for teams with players. Results sorted from highest to lowest
select tbl_team.TeamName, count(tbl_player.TeamID), avg(tbl_player.Salary)
	from tbl_team
    inner join tbl_player
    on tbl_team.TeamID = tbl_player.TeamID
    group by tbl_team.TeamName
    order by avg(tbl_player.Salary) desc;