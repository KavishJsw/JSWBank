use mydb;
create table user(firstName varchar(33),lastName varchar(33),email varchar(44),password varchar(44))
select * from user;

INSERT INTO user (FIRSTNAME,LASTNAME,EMAIL,password)
VALUES ("a","b","a@a.com","test");

use mydb;
create table dashboard(accountno varchar(33),amount varchar(33))
select * from dashboard;
INSERT INTO dashboard (accountno,amount)
VALUES ("323","776");