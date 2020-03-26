一.mysql的核心知识之服务管理

1.mysql的service服务管理与登录管理

查看mysql服务进程：ps -ef | grep mysql

service服务管理：cp -a mysql.server /etc/rc.d/init.d/mysql

启动命令：service mysql start

关闭命令：service mysql stop

重新启动命令：service mysql restart

查看状态命令：service mysql status

登录管理： ln -s /usr/local/mysql/bin/* /bin

登录命令：mysql -uroot -p

默认端口号：3306

配置文件：/etc/my.cnf

2.mysql的可视化图形界面与命令行操作

命令行模式：

登录命令：mysql -u用户 -p密码

退出命令：exit; quit;

3.细讲数据库的创建使用

直接创建数据库 db1

create database db1;

查看当前在哪个库里边

select database();

判断是否存在，如果不存在则创建数据库 db2

create database if not exists db2;

创建数据库并指定字符集为 gbk

create database db3 default character set gbk;

查看当前mysql使用的字符集

show variables like 'character%';

4.细讲常用数据类型

mysql常见数据类型

![](https://i.imgur.com/qYpGu7c.png)

![](https://i.imgur.com/K0EhgbV.png)

![](https://i.imgur.com/hRlpMUO.png)

5.mysql数据表必备知识之创建表

语法：

CREATE TABLE 表名 (
字段名1 字段类型1 约束条件1 说明1,
字段名2 字段类型2 约束条件2 说明2,
字段名3 字段类型3 约束条件3 说明3
);

create table 新表名 as select * from 旧表名 where 1=2;(注意：建议这种创建表的方式用于日常测试，
因 为可能索引什么的会复制不过来)

create table 新表名 like 旧表名;

约束条件：

comment ----说明解释

not null ----不为空

default ----默认值

unsigned ----无符号（即正数）

auto_increment ----自增

zerofill ----自动填充

unique key ----唯一值

创建sql

CREATE TABLE student (

id tinyint(5) zerofill auto_increment not null comment '学生学号',

name varchar(20) default null comment '学生姓名',

age tinyint default null comment '学生年龄',

class varchar(20) default null comment '学生班级',

sex char(5) not null comment '学生性别',

unique key (id)

)engine=innodb charset=utf8;

CREATE TABLE student (

id tinyint(5) auto_increment default null comment '学生学号',

name varchar(20) default null comment '学生姓名',

age tinyint default null comment '学生年龄',

class varchar(20) default null comment '学生班级',

sex char(5) not null comment '学生性别',

unique key (id)

)engine=innodb charset=utf8;

6.mysql数据表必备知识之查看

如何查看表的基本结构信息

查看数据库中的所有表：show tables；

查看表结构：desc 表名;

查看创建表的sql语句：show create table 表名;

\G :有结束sql语句的作用，还有把显示的数据纵向旋转90度

\g :有结束sql语句的作用

7.mysql数据表必备知识之表结构维护与删除

修改表名

rename table 旧表名 to 新表名;

rename table student to user;

添加列

给表添加一列：alter table 表名 add 列名 类型;

alter table user add addr varchar(50);

alter table add 列名 类型 comment '说明';

alter table user add famliy varchar(50) comment '学生父母';

给表最前面添加一列：alter table 表名 add 列名 类型 first;

alter table user add job varchar(10) first;

给表某个字段后添加一列：alter table 表名 add 列名 类型 after 字段名;

alter table user add servnumber int(11) after id;

注意：没有给表某个字段前添加一列的说法。

修改列类型

alter table 表名 modify 列名 新类型;

alter table user modify servnumber varchar(20);

修改列名

alter table 表名 change 旧列名 新列名 类型;

alter table user change servnumber telephone varchar(20);

删除列

alter table 表名 drop 列名;

alter table user drop famliy;

修改字符集

alter table 表名 character set 字符集;

alter table user character set GBK;

mysql表的删除

drop table 表名；

drop table user;

看表是否存在，若存在则删除表：drop table if exists 表名;

drop table if exists teacher;

二.mysql核心知识之DML数据操纵语言

普通的插入表数据

insert into 表名（字段名） values（字段对应值）;

insert into employee (empno,ename,job,mgr,hiredate,sal,deptnu) values ('1000','小明','经
理','10001','2019-03-03','12345.23','10');

insert into 表名 values（所有字段对应值）;

insert into employee values ('1001','小明','经理','10001','2019-03-03','12345.23','10');

蠕虫复制（将一张表的数据复制到另一张表中）

insert into 表名1 select * from 表名2;

insert into 表名1（字段名1，字段名2） select 字段名1，字段名2 from 表名2;

insert into emp (empno,ename) select empno,ename from employee;

建表复制

create table 表名1 as select 字段名1，字段名2 from 表名2;

create table emp as select empno ,ename from employee;

一次性插入多个数据

insert into 表名 (字段名) values (对应值1),(对应值2),(对应值3);

创建sql：


某个公司的员工表

CREATE TABLE employee(

empno INT PRIMARY KEY comment '雇员编号',

ename VARCHAR(20) comment '雇员姓名',

job VARCHAR(20) comment '雇员职位',

mgr INT comment '雇员上级编号',

hiredate DATE comment '雇佣日期',

sal DECIMAL(7,2) comment '薪资',

deptnu INT comment '部门编号');

2.mysql深入剖析表数据的修改以及删除

修改（更新）：

update 表名 set 字段名1=值1 where 字段名=值;

update 表名 set 字段名1=值1,字段名2=值2 where 字段名=值;

删除：

delete from 表名 where 字段名=值;

truncate table 表名;

delete from 表名;

drop table 表名;

注意事项：

在删改数据之前，你会怎么做？

会对数据进行备份操作，以防万一，可以进行数据回退

delete与truncate与drop 这三种删除数据的共同点都是删除数据，他们的不同点是什么?

delele 会把删除的操作记录给记录起来，以便数据回退，不会释放空间，而且不会删除定义。

truncate不会记录删除操作，会把表占用的空间恢复到最初，不会删除定义

drop会删除整张表，释放表占用的空间。

删除速度：

drop > truncate > delete

3.mysql核心知识之中文乱码问题

详细讲解汉字显示乱码问题

查看当前mysql使用的字符集：show variables like 'character%';

![](https://i.imgur.com/kiwG7Ym.png)

临时：set names gbk;

永久：修改配置文件my.cnf里边的

[client]

default-character-set=gbk

作用于外部的显示

[mysqld]

character_set_server=gbk

作用于内部，会作用于创建库表时默认字符集

修改库的字符集编码

alter database xiaoxiao default character set gbk;

修改表的字符集编码

alter table employee default character set utf8;

4.mysql核心知识之DQL数据查询语言与项目高级查询









