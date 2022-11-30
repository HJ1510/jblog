show tables;
desc user;
select * from `user` u ;
desc blog;
select * from blog b ;
desc category;
select * from category c ;
desc post;
select * from post p ;

insert into user values(id, name, password);
insert into blog values(id, title, profile);
insert into category values(null, title, id);
