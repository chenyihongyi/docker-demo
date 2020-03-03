一.Redis介绍
1.redis是一个使用c语言编写的通过键值对存储高性能非关系内存数据库。

2.Redis提供了五种数据类型来存储键值对中的值

3.Redis也是一种NoSQL数据库。

4.什么是非关系型？

4.1 关系型数据库：有行有列的数据库叫关系型数据库。

4.2 非关系型数据库，顾名思义，就是数据和数据之间完全是独立的。

5.什么是Nosql数据库？

Nosql 也叫Not Only SQL（不仅仅是SQL）

Nosql可以作为关系型数据库的良好补充，但是不能替代关系型数据库，

6.Nosql数据库的分类

键值对

Redis(只能根据key获取value，不能再去操作value中的数据)

列存储

Hbase(将大的数据库进行分块之后，存储到HBASE数据库中)

文档型

MongoDB(数据操作会更加灵活，文档就类似于一个java对象,有属性和属 性值，而属性值又可以是一个对象)

图形

7.Redis应用场景:

7.1 内存数据库(登陆信息、购物车信息、用户浏览记录等)

7.2缓存服务器(商品数据、广告数据等等)(最多使用)

7.3解决分布式集群架构中的session分离问题(session共享)

7.4 任务队列(秒杀、抢购、12306等等)

7.5 支持发布订阅的消息模式

7.6 应用排行榜

7.7 网站访问统计

7.8 数据国企处理(可以精确到毫秒)

8.Linux(CentOS7版本)单机版安装 

安装版本:3.2.9

8.1.wget http://download.redis.io/releases/redis-3.2.9.tar.gz

8.2 在线安装:yuminstall -y -wget

8.3 下载并解压缩Redis源码包

tar -zxf redis-3.2.9.tar,gz

8.4 拷贝redis-3.2.9/redis.conf配置文件到Redis安装目录

1.cp redis.conf/kkb/server/redis/bin/

2. 第二步:修改redis.conf

vim redis.conf

3.将damonize由no 改为yes

4.默认绑定是回环地址,默认不能被其他机器访问

bind 127.0.0.1

5.是否开启保护模式，由yes改为no

protected-mode no
6.启动服务
./redis-server redis.conf

9.其他命令说明:

redis-server：启动redis

redis-cli : 进入redis命令客户端

redis-benchmark：性能测试的工具

redis-check-aof ： aof文件进行检查的工具

redis-check-dump：rdb文件进行检查的工具

redis-setinel：启动哨兵监控服务。

10.windows版本Redis

https://jingyan.baidu.com/article/7f766daff725cf4101e1d0d1.html

D:\Program Files\Redis

1.选择Redis安装目录，cmd

2.在命令行窗口输入redis-server.exe redis.windows.conf指令执行报错误

[13164] 27 Dec 20:57:07.820 # Creating Server TCP listening socket
 127.0.0.1:637 9: bind: No error。
 
那么可以输入如下的命令依次执行第一条指令：redis-cli.exe，

第二条指令：shutdown第三条指令：exit

3.在命令行窗口输入redis-server.exe redis.windows.conf，
就会显示Redis服务器的信息，就代表Redis服务器已经启动了。

10.数据类型

10.1 String类型(字符串类型)

string类型适合查询操作，string类型存储对象，需要进行对象转 换为 json串进行存储。

get、set、setnx

incr、incrby、decr、decrby(只能针对整数类型的数据进行使用)

incr命令,它是原子操作,用来生成数据库的自增主键，是非常安全 且高效的

incr使用场景:分布式数据库中订单ID的生成。

10.2 hash类型(散列类型)

hash类型适合增删改操作。

hset、 hget、 hdel(存取某个数据库字段，不能对多个字段赋 值)
eg:>hset hash name zhangsan, 
>hget hash name

hmget、 hmset(可以对多个字段赋值)

使用场景：存储增删改操作居多的pojo对象数据。

10.3 list类型(队列类型)

底层是双向链表进行存储的，特点就是两端操作比较方便，适 合于实现队列(FIFO)和栈(后入先出)

使用场景:适合只对list列表两端进行操作的场景。

list类型存储的数据特点:有序可重复

lpush、lpop、 rpush、rpop、lrange

可以用来作为消息队列进行使用。

可以用来实现商品评论表

key的设计很重要:

1. 使用谁作为key，使用谁查询方便

2.key的定义,一定要有意义。

3.key的定义举例:

item ：1 {id:1, name:'zhangsan'}

10.4 set类型(集合类型)

set类型存储的数据特点:无序不重复

sadd、srem(删除)、smembers(查询去重元素的个数)

eg: sadd set a a b b c 4 3 // 返回结果为5

可以进行多集合操作:sdiff(差集操作),sinter(交集操 作),sunion(并集操作)

10.5 zset类型(有序集合类型、sortedset)

对集合中的每个元素都设置一个分数，根据分数排序。

zset类型存储的数据结构特点:不重复，有序

底层还是一个set集合，但是该集合给每个member设置一个 score, 通过score进行排序。

zadd, zrem, zrange, zscore

eg: zadd zset 30 a 40 c 20 b

eg: zrange zset 0 -1 // 结果 “c”"a" "b"

使用场景:销售排行榜

销售作为分数

销售人员或者商品作为member

11. Redis事务
redis中实务是通过4个命令来实现的: multi(开启事务)、exec(提交事务)、discard(清除事务队列)、watch(监控，实现乐观锁)、unwatch（清除所有事务监控的键）

redis中的单个命令都是原子性的。redis中的事物主要是用来管理redis命令集合。

redis事物是没有回滚操作的。

回滚是比较耗性能的.

redis出现问题，都是语法问题和类型问题，这些问题，都是在编码阶段必须要解决的问题

redis事务执行原理图
![](https://i.imgur.com/PyN8G3K.png)

12.Redis持久化

redis的持久化解决的是当机器宕机，对于数据的一种处理思路。

rbd方式:

默认方式

Redis会在指定情况下触发快照:

1.符合自定义配置的快照规则

2.执行save或者bgsave命令

3.执行flushall

4.执行主从复制操作。

满足一定条件之后, 使用快照方式去进行持久化

* redis.conf(条件是以save开头的，一行是一个条件,条件之间是 或的关系)

save 900 1

save 300 10

save 60 10000

dbfilename dump.rdb 持久化名称

优点:性能好(隔一段时间才写磁盘)

缺点: 很大可能发生数据丢失(在一段时间，没有来得及及时存储的时候，突 然宕机，就会出现数据丢失)

不开启rdb方式:

redis.conf

save ""屏蔽rdb开启方式

特别说明:

Redis 启动后会读取RDB快照文件,将数据从硬盘载入到内存。

根据数据量大小与结构和服务器性能不同,这个快照条件也需要不同。通常将记录一千万个 字符串类型键、大小为1GB的快照文件载入到内存中需要花费20-30秒钟。

rdb快照原理
![](https://i.imgur.com/0eVX27s.png)

注意事项：

1.redis在进行快照的过程中不会修改rdb文件，只有快照结束后才会将旧的文件替换成新 的，也就是任何时候RDB文件都是完整的。

2.这就使得我们可以可以通过定时备份rdb文件来实现redis数据库的备份，rdb文件是经 过压缩的二进制文件，占用的空间会小于内存中的数据，更加利于传输。

rdb优缺点
![](https://i.imgur.com/UgTHzA7.png)

如果用作缓存服务器，那么不用设置为aof方式，因为缓存数据在数据库中存有一份。

aof方式

需要手动开启，在 redis.conf文件中，配置appendonly yes参数开启，aof的持久化文件名后 缀名为.aof

一旦开启了aof方式的持久化，那么每一次执行增删改操作，就会往磁盘中 保存一次(保存是命令),导致性能会有所降低,可以使用一些硬件来弥补(SSD固态硬盘)。

其实AOF的每一次写操作的持久化，都不是理论上的实时操作。(磁盘同步)

优点：数据安全(很少发生数据丢失)

缺点: 性能低(每次写操作，都要去写磁盘)
![](https://i.imgur.com/zqbzKh1.png)

13.Redis主从复制

当只有一台机器作为数据库的话，如果磁盘损坏，那么数据就会全部丢失，此时需要使用另一种机制 来保证这中情况的发生不会造成太大的影响。这种机制就是主从复制机制，简单理解，就是给一台 机器做一个数据备份。

Redis的主从复制有两个角色：Master(主机),Slave(丛机)。

Master的作用:对外提供读写功能。

Slave的作用:可以对外提供读的功能，也可以不提供。但是绝对不会提供写的功能。它的数据来源， 只会是从它的主机同步过来.

Redis主机是如何将数据同步到从机中的呢？

一种是全量同步，一种是增量同步。而且redis的同步都是从主机push到从机中的。由于redis是单 线程的，所以如果它一边进行对外提供读写功能，就会有堵塞情况发生。所以redis针对该问题， 会产生一个新的进程去进行同步来解决。

主从复制这种机制，只能实现数据的复制，无法实现服务之间的高 可用。

全量同步

Redis的全量同步过程主要分三个阶段

同步快照阶段：Mater创建并发快照给slave，slave载入并解析快照，Mater同时将 此阶段所产生的新的写命令存储到缓冲区。

同步写缓冲阶段:Master向slave同步存储在缓冲区的写操作命令。

同步增量阶段:Master向slave同步写操作命令。

全量同步原理图

![](https://i.imgur.com/kP0Cj9r.png)

增量同步

Redis增量同步主要指slave完成初始化后开始正常工作时，Master发生的写操作同步到 slave的过程。

通常情况下，Master每执行一个写命令就会向slave发送相同的写命令，然后slave接收 并执行。

增量同步原理图

![](https://i.imgur.com/kBATJ7j.png)

14.Redis哨兵(sentinel)机制
通过redis的哨兵机制，可以实现Redis主从服务器之间的切换，而且完成多个从服务器之间的选举操 作。
Redis主从复制的缺点,没有办法对master进行动态选举,需要使用Sentinel机制完成动态选举。

哨兵进程的作用:

![](https://i.imgur.com/fJMEde2.png)

故障判断原理分析

![](https://i.imgur.com/8qUlJHw.png)

15.Redis的分布式锁
什么是锁？
单应用中使用锁：(单进程多线程)
synchronize、Reentrantlock
分布式应用中使用锁:(多进程多线程)
分布式锁是控制分布式系统之间同步访问共享资源的一种方式。
分布式锁又是什么？
分布式锁的注意事项
















