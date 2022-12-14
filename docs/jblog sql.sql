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
delete from blog ;3
desc category;
select * from category c ;
delete from category ;
desc post;
select * from post p ;
delete from post ;

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

-- admin category
insert into category values(null, '기본', '카테고리를 지정하지 않은 경우', 'go');
insert into category values(null, '일기', '하루 기록', 'go');

SELECT c.`no`, c.title , c.`desc`, (SELECT count(p.no) FROM post p where category_no = 6) as countPost -- countPost 구현..ㅜㅜ
from category c , post p, blog b 
where b.id = c.id and c.no = p.category_no and b.id ='go' order by c.no desc;

SELECT c.no, c.title, c.desc from category c, blog b where b.id = c.id and b.id = 'go' order by c.no desc;

(SELECT count(p.no) FROM post p where category_no = 6) as a;

DELETE from category where no=11;

-- admin write
INSERT into post values(null, '두번째 글입니다', '스프링 공부진행중', now(), 6);
INSERT into post values(null, '1208', '일기 씀', now(), 8);
INSERT into post values(null, '도우너 첫 글', '도우너예요 블로그 시작합니다', now(), 7);
SELECT c.no as categoryNo, c.title as categoryTitle from category c, blog b where b.id = c.id and b.id = 'go' order by c.no asc;
SELECT p.category_no as categoryNo from post p;
SELECT * from post p , category c where p.category_no = c.no;

-- blog index
SELECT b.title , b.profile , c.title as categoryTitle, p.title as postTitle, p.contents, p.reg_date as regDate from blog b , category c , post p where b.id =c.id and c.`no` =p.category_no ;

SELECT title , contents, reg_date as regDate from post p where max(regDate);

-- 가장 최근 글
SELECT c.title as categoryTitle, p.title as postTitle , p.contents FROM post p, category c where p.category_no =c.`no` and id ='go' ORDER BY reg_date DESC LIMIT 1;

-- 카테고리 리스트(최근글 날짜 포함)
SELECT c.title as categoryTitle, p.reg_date as regDate() FROM post p, category c where p.category_no =c.`no` and id='go' ORDER BY reg_date desc;