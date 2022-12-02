show tables;
desc user;
select * from `user` u ;
delete from user;
desc blog;
select * from blog b ;
delete from blog ;
desc category;
select * from category c ;
delete from category ;
desc post;
select * from post p ;

insert into user values(id, name, password);
insert into blog values(id, title, profile);
insert into category values(null, title, id);

-- user login
select name from user where id='ddo@gmail.com' and password='1234';

