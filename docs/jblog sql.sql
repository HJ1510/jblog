create database jblog;

drop user'jblog'@'192.168.0.95';

create user 'jblog'@'192.168.0.%' identified by 'jblog';

grant all privileges on jblog.* to 'jblog'@'192.168.0.%';

flush privileges;

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
select id, name from user where id='ddo' and password='1234';


-- blog index
SELECT b.title , b.profile , c.no as categoryNo , c.title as categoryTitle , p.title as postTitle , p.contents , p.reg_date as regDate
from blog b , category c , post p 
where b.id = c.id and c.no = p.category_no;

-- blog admin
SELECT id, title, profile from blog b where id='go';

UPDATE blog set title='고길동의 블로그', profile='profile' where id='go';
-- UPDATE blog set title=#{title } where id=#{id }; <= 파일업로드 구현 후 수정 필요 


