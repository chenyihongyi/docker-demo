一、计算机基础:

1.常⻅的JDK源码⾥面HashMap的默认容量量16

int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16

2.异或运算 (一个数与另一个数异或两次是其本身， 一个数和⾃自身异或结果是0 )

public static void swap2(int a, int b){

System.out.printf("a=%d, b=%d",a,b);

a = a^b; // a1 = a^b

b = b^a; // b = b^a^b

a = a^b; // a = a1^b = a^b^a

System.out.printf("\na=%d, b=%d",a,b);

}

解释：

a1=a^b

b=b^a1=b^a^b=a

//此时a1=a^b b=a

a=a1^b=a^b^a=b

二：javaSE基础

1.说下java数据类型分类

基础数据类型：byte、short、int、long、float、double、char、boolean

引⽤用数据类型：其他都是引⽤用类型

String和Enum分别是什什么类型：引⽤用类型

2.== 和equals的区别

基本数据类型⽐比较 要⽤用==判断是否相等

引⽤用数据类型： ==⽐比较的是内存地址是否⼀一样，不不同对象的内存地址不不⼀一样，equals⽐比较的

是具体的内容， 也可以让开发者去定义什什么条件去判断两个对象是否⼀一样

3.try-catch-finally异常处理理模块的返回值问题

在执行try、catch中的return之前一定会执行finally中的代码（如果finally存在），如
果finally中有return语句句，就会直接执行finally中的return方法，所以finally中的return语句
一定会被执行的

执⾏行行流程：finally执⾏行行前的代码⾥里里⾯面有包含return，则会先确定return返回值，然后再执
⾏行行finally的代码，最后再执行return

public class try_catch_finally {
    public static void main(String[] args) {
        System.out.println(test1());  //3
        System.out.println(test2());  //4
    }

    public static int test1() {
        int a = 1;
        try {
            System.out.println(a / 0);
            a = 2;
        } catch (ArithmeticException e) {
            a = 3;
            return a;
        } finally {
            a = 4;
        }
        return a;
    }

    public static int test2() {
        int a = 1;
        try {
            System.out.println(a / 0);
            a = 2;
        } catch (ArithmeticException e) {
            a = 3;
            return a;
        } finally {
            a = 4;
            return a;
        }
    }
}

4.IO流实现文件复制

public class IO文件拷贝 {

    public static void main(String[] args) {
        test();
    }

    public static void test() {
        try (
                FileInputStream fis = new FileInputStream("C:\\Users\\ElvisChen\\Desktop\\test.txt");
                BufferedInputStream bis = new BufferedInputStream(fis);
                FileOutputStream fos = new FileOutputStream("C:\\Users\\ElvisChen\\Desktop\\copy.txt");
                BufferedOutputStream bos = new BufferedOutputStream(fos)) {
            int size;
            byte[] buf = new byte[1024];
            while((size = bis.read(buf)) != -1){
                bos.write(buf,0, size);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

JDK7之后的写法，JDK9⼜又进⾏行行了了改良，但是变化不不⼤大，记住下⾯面的写法即可需要关闭的资源只要实现了了java.lang.AutoCloseable，就可以⾃自动被关闭try()⾥里里⾯面可以定义多个资源，它们的关闭顺序是最后在try()定义的资源先关闭

5.找出某⽬目录下的所有⼦目录以及子文件并打印到控制台上

public class 打印所有子目录以及子文件 {

    public static void main(String[] args) {
        //找出某⽬目录下的所有⼦子⽬目录以及⼦子⽂文件并打印到控制台上
        List<String> paths = new ArrayList<>();

        getAllFilePaths(new File("E:\\java_api"),paths);

        for (String path : paths) {
            System.out.println(path);
        }
    }

    private static void getAllFilePaths(File filePath,List<String> paths) {
        File[] files = filePath.listFiles();
        if (files == null) {
            return;
        }
        for (File f : files) {
            if (f.isDirectory()) {
                paths.add(f.getPath());
                getAllFilePaths(f,paths);
            } else {
                paths.add(f.getPath());
            }
        }
    }
}

6.常⽤用字符串串的考查点

String str1= new String("xdclass.net"); 

String str2= "xdclass.net"; 

String str3= "xdclass.net";

System.out.println(str1 == str2) //false

System.out.println(str2 == str3) //true

String s2 = s1 + ".net"; //变量量 + 常量量 = 来⾃自堆

String s3 = "xdclass" + ".net"; //常量 + 常量 = 来⾃常量池

比较引⽤用的内存地址是否一样

第一个是false： new 创建新的对象会开辟新的空间，所以地址不不一样

第二个是true：都是从常量量池⾥里里⾯面获取，"xdclass.net" 存在于常量池中

如果需要两个都为true，应该怎么修改？

如果需要第一个输出为true，只需要把变量量改为常量量即可 fianl String s1 = "xdclass";
不管是new String("XXX")和直接常量赋值, 都会在字符串常量量池创建.只是new String("XXX")方
式会在堆中创建一个对象去指向常量池的对象, 普通的常量赋值是直接赋值给变量

7.String、StringBuffer与StringBuilder的区别？分别在哪些场景下使⽤用？

三者都是final， 不不允许被继承

在本质都是char[]字符数组实现

String、StringBuffer与StringBuilder中，String是不可变对象，另外两个是可变的

StringBuilder 效率更更快，因为它不需要加锁，不具备多线程安全

StringBuffer⾥⾯操作⽅法⽤用synchronized ，效率相对更更低,是线程安全的；

使⽤用场景：

操作少量的数据用String，但是常改变内容且操作数据多情况下最好不要用 String ，

因为每次生成中间对象性能会降低

单线程下操作大量的字符串用StringBuilder，虽然线程不不安全但是不影响

多线程下操作⼤量的字符串，且需要保证线程安全 则用StringBuffer

8.⾯面向对象的四⼤大特性是？分别解释下

抽象

关键词abstract声明的类叫作抽象类，abstract声明的⽅方法叫抽象⽅方法
一个类⾥里里包含了一个或多个抽象⽅方法，类就必须指定成抽象类

抽象方法属于一种特殊⽅方法，只含有一个声明，没有⽅方法体

抽象⽀支付

pay(⾦金金额，订单号)，默认实现是本地支付，微信支付，支付宝支付，银行卡支付

封装

封装是把过程和数据包围起来，对数据的访问只能通过已定义的接口即方法

在java中通过关键字private，protected和public实现封装。

封装把对象的所有组成部分组合在一起，封装定义程序如何引用对象的数据，

封装实际上使用方法将类的数据隐藏起来，控制用户对类的修改和访问数据的程度。 适当的
封装可以让代码更更容易易理理解和维护，也加强了代码的安全性

类封装

⽅法封装

继承

⼦类继承父类的特征和行为，使得子类对象具有父类的方法和属性，⽗类也叫

基类，具有公共的⽅方法和属性

动物<-猫

动物<-狗

abstract class AbsPay{

}

WeixinPay extends AbsPay{

}

AliPay extends AbsPay{

}

多态

同一个行为具有多个不同表现形式的能⼒

优点：减少耦合、灵活可拓拓展

一般是继承类或者重写方法实现

9.Overload和Override的区别？

重载Overload：表示同一个类中可以有多个名称相同的⽅法，但这些方法的参数列表各不相同，参
数个数或类型不同

重写Override：表示子类中的⽅法可以与父类中的某个方法的名称和参数完全相同

接⼝是否可以继承接口？接口是否⽀持多继承？类是否支持多继承？接口⾥面是否可以有方法实现

接口⾥里里可以有静态⽅方法和⽅方法体

接口中所有的⽅方法必须是抽象⽅方法（JDK8之后就不是）

接⼝不是被类继承了了，而是要被类实现

接口支持多继承, 类不支持多个类继承

一个类只能继承一个类，但是能实现多个接口,接口能继承另一个接口，接口的继承使⽤用extends关

键字，和类继承一样

是否了解JDK8⾥⾯接口新特性

interface中可以有static⽅法，但必须有方法实现体，该⽅法只属于该接口，接口名直接调用
该⽅法

接⼝中新增default关键字修饰的方法，default方法只能定义在接口中，可以在⼦类或⼦接口

中被重写default定义的方法必须有方法体

⽗接口的default⽅法如果在子接口或子类被重写，那么子接口实现对象、⼦类对象，调用该
⽅法，以重写为准

本类、接口如果没有重写父类（即接口）的default方法，则在调⽤default⽅法时，使⽤父类
（接口） 定义的default⽅法逻辑

三.java集合

1.说下Vector和ArrayList、LinkedList联系和区别？分别的使⽤用场景

ArrayList：底层是数组实现，线程不安全，查询和修改非常快，但是增加和删除慢

LinkedList: 底层是双向链表，线程不安全，查询和修改速度慢，但是增加和删除速度快

Vector: 底层是数组实现，线程安全的，操作的时候使用synchronized进行加锁

使⽤用场景

Vector已经很少⽤用了了

增加和删除场景多则⽤用LinkedList

查询和修改多则⽤用ArrayList

2.如果需要保证线程安全，ArrayList应该怎么做，有几种⽅方式

方式一：⾃己写个包装类，根据业务一般是add/update/remove加锁

方式二：Collections.synchronizedList(new ArrayList<>()); 使⽤用synchronized加锁

⽅式三：CopyOnWriteArrayList<>() 使用ReentrantLock加锁

3.了解CopyOnWriteArrayList吗？和
Collections.synchronizedList实现线程安全有什什么区别, 使⽤用场景是怎样的？

CopyOnWriteArrayList：执行修改操作时，会拷贝一份新的数组进行操作（add、set、
remove等)，代价十分昂贵，在执行完修改后将原来集合指向新的集合来完成修改操作，源
码里⾯用ReentrantLock可重入锁来保证不会有多个线程同时拷贝一份数组
场景：读高性能，适用读操作远远⼤于写操作的场景中使用(读的时候是不需要加锁的，
直接获取，删除和增加是需要加锁的, 读多写少)

Collections.synchronizedList：线程安全的原因是因为它几乎在每个方法中都使用了
synchronized同步*锁

场景：写操作性能比CopyOnWriteArrayList好，读操作性能并不如
CopyOnWriteArrayList

CopyOnWriteArrayList的设计思想是怎样的,有什什么缺点？

：设计思想：读写分离+最终一致

缺点：内存占⽤用问题,写时复制机制,内存里会同时驻扎两个对象的内存，旧的对象和新写入的对象,
如果对象大则容易易发生Yong GC和Full GC

4.说下ArrayList的扩容机制是怎样的

JDK1.7之前ArrayList默认大小是10，JDk1.7之后是0

未指定集合容量，默认是0，若已经指定大小则集合大小为指定的；

当集合第一次添加元素的时候，集合大小扩容为10

ArrayList的元素个数大于其容量，扩容的大小= 原始大小+原始大小/2

调试代码

List<String> list = new ArrayList<>();

for(int i=0;i<10;i++){

list.add(""+i);

}

System.out.println(list.size());  //10

list.add("xdclass.net");

System.out.println(list.size());  //15

5.设计一个简单的ArrayList【需要包含 构造函数(有参和⽆无参)、add(obj)、 扩容机制】

public class ArrayListDemo01 implements Serializable {

    //第一次扩容的容量
    private static final int DEFAULT_CAPACITY = 10;

    //用于初始化空的list
    private static final Object[] EMPTY_ELEMRNT_DATA = {};

    //实际存储的元素
    transient Object[] elementData;

    //实际list集合大小,从0开始
    private int size;

    public ArrayListDemo01() {
        this.elementData = EMPTY_ELEMRNT_DATA;
    }

    public ArrayListDemo01(int initialCapcity) {
        if (initialCapcity > 0) {
            this.elementData = new Object[initialCapcity];
        } else if (initialCapcity == 0) {
            this.elementData = EMPTY_ELEMRNT_DATA;

        } else {
            throw new IllegalArgumentException("参数异常");
        }
    }

    public boolean add(Object e){
        //判断容量
ensureCapcityInternal(size +1);

        //使用下标复制,尾部插入
        elementData [size++] = e;

        return true;
    }

    private void ensureCapcityInternal(int minCapacity){

        //如果是初次扩容，则使用默认的容量
        if(elementData == EMPTY_ELEMRNT_DATA){
            minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        }

        //是否需要扩容,需要的最少容量大于现在数组的长度则要扩容
        if(minCapacity - elementData.length > 0){
            int oldCapacity = elementData.length;

            int newCapacity = oldCapacity + (oldCapacity >> 1);

            //如果新容量 < 最小容量, 则将最新的容量赋值给新的容量
            if(newCapacity - minCapacity < 0){
                newCapacity = minCapacity;
            }

            //创建新数组
            Object[] objects = new Object[newCapacity];

            //将旧的数组复制到新的数组里面
            System.arraycopy(elementData, 0 , objects, 0, elementData.length);

            //修改引用
            elementData = objects;
        }
    }
}

 /**
     * 通过下标获取对象
     * @param index
     * @return
     */
    public Object get(int index){
        rangeCheck(index);
        return elementData[index];
    }

    private void rangeCheck(int index){
        if(index > size || size < 0){
            throw new IndexOutOfBoundsException("数组越界");
        }
    }

    /**
     * 判断对象所在的位置
     * @param o
     * @return
     */
    public int indexOf(Object o) {
        if (o == null) {
            for (int i = 0; i < size; i++) {
                if (elementData[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                if (o.equals(elementData[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    public Object set(int index, Object obj){
        rangeCheck(index);
        Object oldValue = elementData[index];
        elementData[index] = obj;
        return oldValue;
    }

    /**
     * 根据索引删除元素
     * @param index
     * @return
     */
    public Object remove(int index){
        rangeCheck(index);

        //用于并发判断
        modCount++;

        Object oldValue = elementData[index];

        //计算要删除的位置后面有几个元素
        int numMoved = size - index -1;

        if(numMoved>0){
            System.arraycopy(elementData,index+1, elementData,index,numMoved);
        }

        //将多出的位置位空,没有引用对象，垃圾收集器看可以回收，如果不为空，将会保存一个引用，可能会造成内存泄漏
        elementData[--size] = null;

        return oldValue;
    }

    //获取数组实际大小
    public int size(){
        return this.size;
    }
}

public class ArrayList扩容Demo01Test {

    public static void main(String[] args) {

        ArrayList扩容Demo01 list = new ArrayList扩容Demo01();

        for(int i = 0; i<12; i++){
            list.add(""+i);
        }

        for(int i = 0; i<list.size(); i++){
            System.out.println(list.get(i));
        }
    }
}

四.Map

1.HashMap和Hashtable 的区别

HashMap：底层是基于数组+链表，非线程安全的，默认容量是16、允许有空的健和值

Hashtable：基于哈希表实现，线程安全的(加了了synchronized)，默认容量是11，不允许有
null的健和值

2.对象的 hashCode()和equals()，使⽤用场景

hashcode

顶级类Object里面的方法，所有的类都是继承Object,返回是一个int类型的数

根据一定的hash规则(存储地址，字段，⻓长度等)，映射成一个数组，即散列值

equals

顶级类Object⾥里里⾯面的⽅方法，所有的类都是继承Object,返回是一个boolean类型

根据⾃自定义的匹配规则，用于匹配两个对象是否一样，一般逻辑如下
//判断地址是否一样

//非空判断和Class类型判断

//强转

//对象里面的字段⼀一匹配

使⽤用场景：对象比较、或者集合容器里面排重、⽐较、排序

2.HashMap和TreeMap应该怎么选择，使⽤用场景

hashMap: 散列列桶(数组+链表)，可以实现快速的存储和检索，但是确实包含⽆无序的元素，适⽤用于在
map中插⼊入删除和定位元素

treeMap:使⽤用存储结构是一个平衡二叉树->红⿊黑树，可以⾃自定义排序规则，要实现Comparator接
⼝

能便捷的实现内部元素的各种排序，但是一般性能比HashMap差，适⽤于安装自然排序或者自定义排
序规则(写过微信⽀支付签名⼯工具类就⽤用这个类)

3.Set和Map的关系

核心就是不保存重复的元素，存储一组唯一的对象

set的每一种实现都是对应Map⾥里里⾯面的一种封装，

HashSet对应的就是HashMap，treeSet对应的就是treeMap

4.常见Map的排序规则是怎样的？

按照添加顺序使用LinkedHashMap，按照自然排序使用TreeMap，⾃自定义排序
TreeMap(Comparetor c)

5.介绍下你了了解的HashMap

HashMap底层(数组+链表+红⿊黑树 jdk8才有红黑树)

数组中每一项是一个链表，即数组和链表的结合体

Node<K,V>[] table 是数组，数组的元素是Entry(Node继承Entry)，Entry元素是一个

key-value的键值对，它持有一个指向下个Entry的引用，table数组的每个Entry元素同时也作为当前

Entry链表的⾸节点，也指向了该链表的下个Entry元素

在JDK1.8中，链表的⻓长度⼤大于8，链表会转换成红黑树

6.能否解释下什么是Hash碰撞？常见的解决办法有哪些，hashmap采用哪种方法

hash碰撞的意思是不同key计算得到的Hash值相同，需要放到同个bucket中

常⻅的解决办法：链表法、开发地址法、再哈希法等

HashMap采用的是链表法

7.HashMap底层是 数组+链表+红⿊黑树，为什么要⽤用这几类结构呢？

数组 Node<K,V>[] table ，根据对象的key的hash值进行在数组⾥面是哪个节点

链表的作用是解决hash冲突，将hash值一样的对象存在一个链表放在hash值对应的槽位

红黑树 JDK8使用红黑树来替代超过8个节点的链表，主要是查询性能的提升，从原来的O(n)到
O(logn),通过hash碰撞，让HashMap不断产⽣碰撞，那么相同的key的位置的链表就会不断增长，当对这个
Hashmap的相应位置进行查询的时候，就会循环遍历这个超级大的链表，性能就会下降，所以改用红
⿊树

8.为啥选择红⿊树⽽不用其他树，比如二叉查找树，为啥不一直开始就用红黑树，⽽是到8的长度后才变换

二叉查找树在特殊情况下也会变成一条线性结构，和原先的链表存在一样的深度遍历问题，查找性能
就会慢，使用红黑树主要是提升查找数据的速度，红黑树是平衡二叉树的一种，插入新数据后会通过左旋，右旋、变色等操作来保持平衡，解决单链表查询深度的问题

数据量量少的时候操作数据，遍历线性表⽐比红⿊黑树所消耗的资源少，且前期数据少 平衡二叉树保持平衡是需要消耗资源的，所以前期采用线性表，等到一定数之后变换到红黑树

9.说下hashmap的put和get的核⼼心逻辑（JDK8以上版本）

![](https://i.imgur.com/66QOxTl.png)

get核心流程

final Node<K,V> getNode(int hash, Object key) {

Node<K,V>[] tab; Node<K,V> first, e; int n; K k;

if ((tab = table) != null && (n = tab.length) > 0 &&

(first = tab[(n - 1) & hash]) != null) {

//获取⾸首节点，hash碰撞概览⼩小，通常链表第⼀一个节点就是值，没必要去循环

遍历，处于效率

if (first.hash == hash && // always check first node

((k = first.key) == key || (key != null &&

key.equals(k))))

return first;

//如果不不⽌止⼀一个节点，就需要循环遍历，存在多个hash碰撞

if ((e = first.next) != null) {

//判断是否是红⿊黑树，如果是则调⽤用树的查找

if (first instanceof TreeNode)

return ((TreeNode<K,V>)first).getTreeNode(hash,

key);

//链表结构，则循环遍历获取节点

do {

if (e.hash == hash &&

((k = e.key) == key || (key != null &&

key.equals(k))))

return e;

} while ((e = e.next) != null);

}

}

return null;

}

10.了解ConcurrentHashMap吗？为什么性能比hashtable高，说下原理理

ConcurrentHashMap线程安全的Map, hashtable类基本上所有的方法都是采⽤用synchronized

进⾏行行线程安全控制

高并发情况下效率就降低

ConcurrentHashMap是采用了分段锁的思想提高性能，锁粒度更更细化

11.jdk1.7和jdk1.8里面ConcurrentHashMap实现的区别有没了解

JDK8之前，ConcurrentHashMap使用锁分段技术，将数据分成一段段存储，每个数据段配置一把

锁，即segment类，这个类继承ReentrantLock来保证线程安全

技术点：Segment+HashEntry

JKD8的版本取消Segment这个分段锁数据结构，底层也是使用Node数组+链表+红⿊树，从⽽实现对

每一段数据就行加锁，也减少了并发冲突的概率，CAS(读)+Synchronized(写)

技术点：Node+Cas+Synchronized

12.说下ConcurrentHashMap的put的核心逻辑（JDK8以上版本）

spread(key.hashCode()) 重哈希，减少碰撞概率

tabAt(i) 获取table中索引为i的Node元素

casTabAt(i) 利用CAS操作获取table中索引为i的Node元素

put的核⼼心流程

1、key进行重哈希spread(key.hashCode())

2、对当前table进行⽆无条件循环

3、如果没有初始化table，则用initTable进行初始化

4、如果没有hash冲突，则直接用cas插⼊新节点，成功后则直接判断是否需要扩容，然后结束

5、(fh = f.hash) == MOVED 如果是这个状态则是扩容操作，先进行扩容

6、存在hash冲突，利⽤synchronized (f) 加锁保证线程安全

7、如果是链表，则直接遍历插入，如果数量大于8，则需要转换成红⿊树

8、如果是红黑树则按照红黑树规则插⼊

9、最后是检查是否需要扩容addCount()

推荐资料料：

https://docs.oracle.com/javase/8/docs/api/java/util/concurrent/ConcurrentHashMap.html

https://www.jianshu.com/p/5dbaa6707017

五.并发编程

1.什么是进程、线程、协程，他们之间的关系是怎样的

进程: 本质上是一个独立执行的程序，进程是操作系统进行资源分配和调度的基本概念，操作系统进
行资源分配和调度的一个独立单位

线程:是操作系统能够进行运算调度的最小单位。它被包含在进程之中，是进程中的实际运作单位。

一个进程中可以并发多个线程，每条线程执行不同的任务，切换受系统控制。

协程: 又称为微线程，是一种用户态的轻量级线程，协程不像线程和进程需要进行系统内核上的上下

文切换，协程的上下文切换是由用户⾃⼰决定的，有自己的上下文，所以说是轻量量级的线程，也称之

为⽤户级别的线程就叫协程，一个线程可以多个协程,线程进程都是同步机制，而协程则是异步

Java的原生语法中并没有实现协程,目前python、Lua和GO等语言支持
关系：一个进程可以有多个线程，它允许计算机同时运行两个或多个程序。线程是进程的最小执行单
位，CPU的调度切换的是进程和线程，进程和线程多了之后调度会消耗大量的CPU，CPU上真正运行的
是线程，线程可以对应多个协程

2.协程对于多线程有什么优缺点吗

优点：
非常快速的上下⽂切换，不用系统内核的上下文切换，减⼩开销

单线程即可实现高并发，单核CPU可以支持上万的协程

由于只有一个线程，也不存在同时写变量的冲突，在协程中控制共享资源不需要加锁

缺点：

协程⽆法利⽤多核资源，本质也是个单线程

协程需要和进程配合才能运行在多CPU上

目前java没成熟的第三方库，存在风险

调试debug存在难度，不利于发现问题

3.说下并发和并行的区别，举些例⼦说下

并发 concurrency：
一台处理器上同时处理任务, 这个同时实际上是交替处理多个任务，程序中可以同时拥有两个或者多
个线程，当有多个线程在操作时,如果系统只有一个CPU,则它根本不可能真正同时进行一个以上的线
程,它只能把CPU运行时间划分成若干个时间段,再将时间段分配给各个线程执行

并行 parallellism：

多个CPU上同时处理多个任务，一个CPU执行一个进程时，另一个CPU可以执行另一个进

程，两个进程互不抢占CPU资源，可以同时进⾏
并发指在一段时间内宏观上去处理多个任务。 并行指同一个时刻，多个任务确实真的同时运行。

例子：

并发是一⼼多用，听课和看电影，但是CPU大脑只有一个，所以轮着来

并行：⽕火影忍者中的影分身，有多个你出现，可以分别做不同的事情

一个项⽬目经理理A和3个程序B C D的故事

单线程

并发：A给B讲完需求，B⾃自⼰己去实现，期间A继续给C和D讲，不⽤用等待某个程序员去完成，期间项目
经理理没空闲下来

并行：直接找3个项目经理理分别分配给3个程序员

4.java里⾯实现多线程有哪几种方式，有什么不同，比较常用哪种

继承Thread

继承Thread，重写里面run方法，创建实例，执行start

优点：代码编写最简单直接操作

缺点：没返回值，继承一个类后，没法继承其他的类，拓展性差

public class ThreadDemo1 extends Thread {
@Override
public void run() {
System.out.println("继承Thread实现多线程，名
称："+Thread.currentThread().getName());
}
}

public static void main(String[] args) {
ThreadDemo1 threadDemo1 = new ThreadDemo1();
threadDemo1.setName("demo1");
threadDemo1.start();
System.out.println("主线程名
称："+Thread.currentThread().getName());
}

实现Runnable

自定义类实现Runnable，实现里面run方法，创建Thread类，使用Runnable接口的实现对象

作为参数传递给Thread对象，调用Strat⽅法

优点：线程类可以实现多个接口，可以再继承一个类

缺点：没返回值，不能直接启动，需要通过构造一个Thread实例例传递进去启动

public class ThreadDemo2 implements Runnable {
@Override
public void run() {
System.out.println("通过Runnable实现多线程，名
称："+Thread.currentThread().getName());
}
}
public static void main(String[] args) {

ThreadDemo2 threadDemo2 = new ThreadDemo2();

Thread thread = new Thread(threadDemo2);

thread.setName("demo2");

thread.start();

System.out.println("主线程名

称："+Thread.currentThread().getName());

}

JDK8之后采用lambda表达式

public static void main(String[] args) {

Thread thread = new Thread(()->{

System.out.println("通过Runnable实现多线程，名

称："+Thread.currentThread().getName());

});

thread.setName("demo2");

thread.start();

System.out.println("主线程名称："+Thread.currentThread().getName());
}


通过Callable和FutureTask方式

创建callable接口的实现类，并实现call方法，结合FutureTask类包装Callable对象，实
现多线程

优点：有返回值，拓展性也⾼

缺点：jdk5以后才支持，需要重写call方法，结合多个类比如FutureTask和Thread类

public class MyTask implements Callable<Object> {

    @Override
    public Object call() throws Exception {

        System.out.println("通过Callable实现多线程,名称: "+Thread.currentThread().getName());

        return "这是返回值";
    }
}


public class CallableTest {

    public static void main(String[] args) {

        FutureTask<Object> futureTask = new FutureTask<>(()->{

            System.out.println("通过Callable实现多线程,名称: "+Thread.currentThread().getName());

            return "这是返回值";

        });

/*        MyTask myTask = new MyTask();

        FutureTask<Object> futureTask = new FutureTask<>(myTask);*/

        //FutureTask继承了了Runnable，可以放在Thread中启动执行

        Thread thread = new Thread(futureTask);

        thread.setName("demo3");

        thread.start();

        System.out.println("主线程名称: "+Thread.currentThread().getName());

        try {

            System.out.println(futureTask.get());

        } catch (InterruptedException e) {

            e.printStackTrace();

        } catch (ExecutionException e) {

            e.printStackTrace();
        }
    }
}

通过线程池创建线程

自定义Runnable接口，实现run方法，创建线程池，调用执行方法并传入对象

优点：安全高性能，复用线程

缺点: jdk5后才支持，需要结合Runnable进行使用

public class ThreadDemo4  implements Runnable{

    @Override

    public void run() {

        System.out.println("通过线程池+runnable实现多线程, 名称: "+Thread.currentThread().getName());
    }
}

public class ExecutorServiceTest {

    public static void main(String[] args) {

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for(int i = 0; i<10; i++){

            executorService.execute(new ThreadDemo4());
        }

        System.out.println("主线程名称: " +Thread.currentThread().getName());

        //关闭线程池
        executorService.shutdown();
    }
}

一般常用的Runnable 和 第四种线程池+Runnable，简单方便扩展，和高性能 (池化的思想)

5.java线程常见的基本状态有哪些，这些状态分别是做什么的

JDK的线程状态分6种，JVM⾥面9种，我们一般说JDK的线程状态

常见的5种状态

创建(NEW): ⽣成线程对象，但是并没有调⽤用该对象start(), new Thread()

就绪(Runnable)：当调用线程对象的start()方法，线程就进入就绪状态，但是此刻线程调度还没

把该线程设置为当前线程，就是没获得CPU使⽤用权。 如果线程运行后，从等待或者睡眠中回来之

后，也会进入就绪状态

注意:有些文档把就绪和运行两种状态统一称为 “运行中”

运行(Running)

程序将处于就绪状态的线程设置为当前线程，即获得CPU使⽤权，这个时候线程进入运行

状态，开始运行run⾥面的逻辑

阻塞(Blocked)

等待阻塞：进⼊该状态的线程需要等待其他线程作出一定动作(通知或中断)，这种状态的

话CPU不会分配过来，他们需要被唤醒，可能也会无限等待下去。⽐如调⽤用wait(状态就会变成

WAITING状态)，也可能通过调用sleep(状态就会变成TIMED_WAITING), join或者发出IO请

求，阻塞结束后线程重新进入就绪状态

同步阻塞：线程在获取synchronized同步锁失败，即锁被其他线程占⽤用，它就会进入同

步阻塞状态

等待(WAITING)：进入该状态的线程需要等待其他线程做出一些特定动作（通

知或中断）。

超时等待(TIMED_WAITING)：该状态不同于WAITING，它可以在指定的时间后⾃行返回

死亡(TERMINATED):一个线程run方法执行结束，该线程就死亡了，不能进入就绪状态

6.多线程开发里面常⽤的方法，sleep/yield/join wait/notify/notifyAll, 分别解释下

sleep

属于线程Thread的方法

让线程暂缓执行，等待预计时间之后再恢复

交出CPU使⽤权，不会释放锁

进入阻塞状态TIME_WAITGING，睡眠结束变为就绪Runnable

yield

属于线程Thread的方法

t1/t2/t3

暂停当前线程的对象，去执行其他线程

交出CPU使⽤用权，不会释放锁，和sleep类似

作用：让相同优先级的线程轮流执行，但是不保证一定轮流

注意：不会让线程进入阻塞状态，直接变为就绪Runnable，只需要重新获得CPU使⽤用权

join

属于线程Thread的方法

在主线程上运行调用该方法，会让主线程休眠，不会释放已经持有的对象锁

让调用join方法的线程先执行完毕，再执行其他线程

类似让救护车警⻋优先通过

wait

属于Object的⽅方法

当前线程调用对象的wait方法，会释放锁，进入线程的等待队列

需要依靠notify或者notifyAll唤醒，或者wait(timeout)时间自动唤醒

notify

属于Object的⽅法

唤醒在对象监视器器上等待的单个线程，选择是任意的

notifyAll

属于Object的方法

唤醒在对象监视器器上等待的全部线程

7.线程有几种状态和API，画出转换流程图

![](https://i.imgur.com/5iO8zwB.png)

8.平时业务代码⾥面使⽤过多线程吗，能举例几个多线程的业务场景吗？

异步任务：用户注册短信(邮箱)验证、记录日志

定时任务：定期备份⽇日志、备份数据库

分布式计算：Hadoop处理理任务mapreduce，master-wark(单机单进程)

服务器器编程：Socket⽹网络编程，一个连接一个线程

在Java中可以有哪些方法来保证线程安全

加锁,⽐比如synchronize/ReentrantLock

使用volatile声明变量，轻量级同步，不能保证原子性(需要解释)

使⽤线程安全类(原⼦类AtomicXXX，并发容器，同步容器

CopyOnWriteArrayList/ConcurrentHashMap等

ThreadLocal本地私有变量/信号量Semaphore等

8.了解volatile关键字不？能否解释下，然后这和synchronized有什么大的区别

volatile是轻量级的synchronized，保证了共享变量的可见性，被volatile关键字修饰的变
量，如果值发生了变化，其他线程立刻可见，避免出现脏读现象

volatile：保证可见性，但是不能保证原子性

synchronized：保证可见性，也保证原子性

使用场景

1、不能修饰写入操作依赖当前值的变量，⽐如num++、num=num+1,不是原子操作，⾁眼看起来
是，但是JVM字节码层面不止一步

2、由于禁⽌了指令重排，所以JVM相关的优化没了，效率会偏弱

为什么会出现脏读？

JAVA内存模型简称 JMM

JMM规定所有的变量存在主内存，每个线程有自己的工作内存,线程对变量的操作都在工作内存中进
⾏行，不能直接对主内存进行操作

使用volatile修饰变量

每次读取前必须从主内存属性最新的值

每次写入需要⽴刻写到主内存中

volatile关键字修饰的变量随时看到的自己的最新值，假如线程1对变量v进行修改，那么线程2
是可以⻢上看见

9.说volatile可以避免指令重排，能否解释下什么是指令重排

指令重排序分两类 编译器重排序和运行时重排序

JVM在编译java代码或者CPU执行JVM字节码时，对现有的指令进行重新排序，主要⽬的是优化运行
效率(不改变程序结果的前提)

int a = 3 //1

int b = 4 //2

int c =5 //3

int h = a*b*c //4

定义顺序 1,2,3,4

计算顺序 1,3,2,4 和 2,1,3,4 结果都是一样

虽然指令重排序可以提高执行效率，但是多线程上可能会影响结果，有什么解决办法？

解决办法：内存屏障

解释：内存屏障是屏障指令，使CPU对屏障指令之前和之后的内存操作执行结果的一种约束

知道 happens-before吗，能否简单解释下？

先行发生原则，volatile的内存可见性就体现了该原则之⼀

例子：

//线程A操作

int k = 1;

//线程B操作

int j = k;

//线程C操作

int k = 2

分析：

假设线程A中的操作“k=1”先行发生于线程B的操作“j=k”，那确定在线程B的操作执行后，变量j的值

一定等于1，依据有两个：⼀一是先行发生原则，“k=1”的结果可以被观察到；二是第三者线程C还没出

现，线程A操作结束之后没有其他线程会修改变量k的值。

但是考虑线程C出现了，保持线程A和线程B之间的先行发生关系，线程C出现在线程A和线程B的操作

之间，但是线程C与线程B没有先行发⽣关系，那j的值会是多少？答案是1和2都有可能，因为线程C

对变量k的影响可能会被线程B观察到，也可能不会，所以线程B就存在读取到不符合预期数据的风
险，不具备多线程安全性

⼋大原则

1、程序次序规则

2、管程锁定规则

3、volatile变量规则

4、线程启动规则

5、线程中断规则

6、线程终止规则

7、对象终结规则

8、传递性

六.并发编程

1.并发编程三要素是否知道，能否分别解释下，举个简单的例子

原子性:一个不可再被分割的颗粒，原⼦性指的是一个或多个操作要么全部执行成功要么全部执行失
败，期间不不能被中断，也不不存在上下文切换，线程切换会带来原子性的问题

int num = 1; // 原⼦子操作
num++; // ⾮原子操作，从主内存读取num到线程工作内存，进行 +1，再把num写到主内存, 除
非用原子类，即java.util.concurrent.atomic⾥的原⼦变量类

解决办法是可以用synchronized 或 Lock(比如ReentrantLock) 来把这个多步操作“变成”原
⼦操作，但是volatile，前⾯面有说到不不能修饰有依赖值的情况

public class XdTest {

private int num = 0;

//使用lock，每个对象都是有锁，只有获得这个锁才可以进行对应的操作

Lock lock = new ReentrantLock();

public void add1(){

lock.lock();

try {

num++;

}finally {

lock.unlock();

}

}
//使用synchronized，和上述是一个操作，这个是保证方法被锁住而已，上述的是代码块被

锁住

public synchronized void add2(){

num++;

}

}
解决核心思想：把一个方法或者代码块看做一个整体，保证是一个不可分割的整体

有序性: 程序执行的顺序按照代码的先后顺序执行，因为处理器可能会对指令进行重排序

JVM在编译java代码或者CPU执行JVM字节码时，对现有的指令进行重新排序，主要目的是优化运行

效率(不改变程序结果的前提)

int a = 3 //1

int b = 4 //2

int c =5 //3

int h = a*b*c //4

上⾯的例子 执行顺序1,2,3,4 和 2,1,3,4 结果都是一样，指令重排序可以提高执行效率，但是

多线程上可能会影响结果

假如下⾯的场景，正常是顺序处理

//线程1

before();//处理初始化工作，处理完成后才可以正式运行下⾯的run方法

flag = true; //标记资源处理好了，如果资源没处理好，此时程序就可能出现问题

//线程2

while(flag){

run(); //核⼼心业务代码

}

指令重排序后，导致顺序换了，程序出现问题，且难排查

//线程1

flag = true; //标记资源处理好了，如果资源没处理好，此时程序就可能出现问题

//线程2

while(flag){

run(); //核⼼心业务代码

}

before();//处理初始化工作，处理完成后才可以正式运行下⾯的run方法

可⻅性: 一个线程A对共享变量的修改,另一个线程B能够立刻看到

// 线程 A 执行

int num = 0;

// 线程 A 执⾏

num++;

// 线程 B 执行

System.out.print("num的值：" + num);

线程A执行 i++ 后再执行线程 B，线程 B可能有2个结果，可能是0和1。

因为 i++ 在线程A中执行运算，并没有立刻更新到主内存当中，而线程B就去主内存当中读取并打

印，此时打印的就是0；也可能线程A执行完成更新到主内存了,线程B的值是1。

所以需要保证线程的可见性

synchronized、lock和volatile能够保证线程可见性

2.说下你知道的调度算法，比如进程间的调度

先来先服务调度算法：

按照作业/进程到达的先后顺序进行调度 ，即：优先考虑在系统中等待时间最长的作业

排在长进程后的短进程的等待时间长，不利于短作业/进程

短作业优先调度算法：

短进程/作业（要求服务时间最短）在实际情况中占有很大比例，为了使得它们优先执⾏

对⻓作业不友好

⾼响应⽐优先调度算法:

在每次调度时，先计算各个作业的优先权：优先权=响应比=（等待时间+要求服务时间）/
要求服务时间,
因为等待时间与服务时间之和就是系统对该作业的响应时间，所以 优先权=响应比=响应
时间/要求服务时间，选择优先权高的进行服务需要计算优先权信息，增加了系统的开销

时间⽚轮转调度算法:

轮流的为各个进程服务，让每个进程在一定时间间隔内都可以得到响应

由于⾼频率的进程切换，会增加了开销，且不区分任务的紧急程度

优先级调度算法:

根据任务的紧急程度进行调度，⾼优先级的先处理，低优先级的慢处理

如果⾼优先级任务很多且持续产生，那低优先级的就可能很慢才被处理

常⻅见的线程间的调度算法是怎样的，java是哪种

线程调度是指系统为线程分配CPU使用权的过程，主要分两种

协同式线程调度(分时调度模式)：线程执行时间由线程本身来控制，线程把自己的⼯作执行完之后，

要主动通知系统切换到另外一个线程上。最大好处是实现简单，且切换操作对线程⾃己是可知的，没

啥线程同步问题。坏处是线程执行时间不可控制，如果一个线程有问题，可能一直阻塞在那里

抢占式线程调度：每个线程将由系统来分配执行时间，线程的切换不由线程本身来决定（Java中，

Thread.yield()可以让出执行时间，但⽆法获取执行时间）。线程执行时间系统可控，也不会有一

个线程导致整个进程阻塞

Java线程调度就是抢占式调度,优先让可运行池中优先级高的线程占用CPU,如果可运行池中的线程优

先级相同,那就随机选择一个线程

所以我们如果希望某些线程多分配一些时间，给一些线程少分配一些时间，可以通过设置线程优先级
来完成。

JAVA的线程的优先级，以1到10的整数指定。当多个线程可以运行时，VM一般会运行最⾼优先级的线
程（Thread.MIN_PRIORITY⾄至Thread.MAX_PRIORITY）

在两线程同时处于就绪runnable状态时，优先级越高的线程越容易被系统选择执行。但是优先级并
不是100%可以获得，只不过是机会更大而已。

有⼈人会说，wait,notify不不就是线程本身控制吗？

其实不是，wait是可以让出执行时间，notify后⽆无法获取执行时间，随机等待队列里⾯获取而已

3.开发里面用过java里面有哪些锁？分别解释下

悲观锁：当线程去操作数据的时候，总认为别的线程会去修改数据，所以它每次拿数据的时候都会上
锁，别的线程去拿数据的时候就会阻塞，比如synchronized

乐观锁：每次去拿数据的时候都认为别人不会修改，更新的时候会判断是别人是否回去更新数据，通
过版本来判断，如果数据被修改了就拒绝更新，比如CAS是乐观锁，但严格来说并不是锁，通过原子
性来保证数据的同步，比如说数据库的乐观锁，通过版本控制来实现，CAS不会保证线程同步，乐观
的认为在数据更新期间没有其他线程影响

小结：悲观锁适合写操作多的场景，乐观锁适合读操作多的场景，乐观锁的吞吐量会⽐悲观锁多

公平锁：指多个线程按照申请锁的顺序来获取锁，简单来说 如果一个线程组里，能保证每个线程都

能拿到锁 比如ReentrantLock(底层是同步队列列FIFO:First Input First Output来实现)

非公平锁：获取锁的方式是随机获取的，保证不了每个线程都能拿到锁，也就是存在有线程饿死,一
直拿不到锁，⽐如synchronized、ReentrantLock

小结：非公平锁性能高于公平锁，更能重复利用CPU的时间

可重⼊锁：也叫递归锁，在外层使用锁之后，在内层仍然可以使用，并且不发生死锁

不可重入锁：若当前线程执行某个方法已经获取了该锁，那么在方法中尝试再次获取锁时，就会获取
不到被阻塞

小结：可重入锁能一定程度的避免死锁 synchronized、ReentrantLock 重入锁

private void meathA(){

//获取锁 TODO

meathB();

}

private void meathB(){

//获取锁 TODO

//其他操作

}

自旋锁：一个线程在获取锁的时候，如果锁已经被其它线程获取，那么该线程将循环等待，然后不断

的判断锁是否能够被成功获取，直到获取到锁才会退出循环,任何时刻最多只能有一个执行单元获得
锁.

小结：不会发生线程状态的切换，一直处于用户态，减少了线程上下文切换的消耗，缺点是循环会消
耗CPU

常见的自旋锁：TicketLock,CLHLock,MSCLock

4.用过java⾥面有哪些锁？分别解释下

共享锁：也叫S锁/读锁，能查看但无法修改和删除的一种数据锁，加锁后其它用户可以并发读取、查询数
据，但不能修改，增加，删除数据，该锁可被多个线程所持有，用于资源数据共享

互斥锁：也叫X锁/排它锁/写锁/独占锁/独享锁/ 该锁每一次只能被一个线程所持有,加锁后任何线程试图
再次加锁的线程会被阻塞，直到当前线程解锁。例子：如果线程A对data1加上排他锁后，则其他线程
不能再对data1加任何类型的锁,获得互斥锁的线程即能读数据又能修改数据

死锁：两个或两个以上的线程在执行过程中，由于竞争资源或者由于彼此通信而造成的一种阻塞的现象，若
⽆外力作用，它们都将无法让程序进行下去

下面三种是Jvm为了提高锁的获取与释放效率而做的优化，针对Synchronized的锁升级，锁的状态是通过
对象监视器在对象头中的字段来表明，是不可逆的过程，

偏向锁：一段同步代码一直被一个线程所访问，那么该线程会自动获取锁，获取锁的代价更低，

轻量级锁：当锁是偏向锁的时候，被其他线程访问，偏向锁就会升级为轻量级锁，其他线程会通过自旋的形
式尝试获取锁，但不会阻塞，且性能会⾼点

重量级锁：当锁为轻量级锁的时候，其他线程虽然是自旋，但自旋不会一直循环下去，当自旋一定次数的时
候且还没有获取到锁，就会进入阻塞，该锁升级为重量级锁，重量级锁会让其他申请的线程进入阻塞，性能
也会降低

分段锁、行锁、表锁

5.写个多线程死锁的例子

public class DeadLockDemo {

    private static String locka = "locka";

    private static String lockb = "lockb";

    public void methodA(){

        synchronized (locka){
            System.out.println("我是A方法中获得了锁A "+Thread.currentThread().getName() );

            //让出CPU执行权，不释放锁
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized(lockb){
                System.out.println("我是A方法中获得了锁B "+Thread.currentThread().getName() );
            }
        }
    }

    public void methodB(){
        synchronized (lockb){
            System.out.println("我是B方法中获得了锁B "+Thread.currentThread().getName() );

            //让出CPU执行权，不释放锁
            try {
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            synchronized(locka){
                System.out.println("我是B方法中获得了锁A "+Thread.currentThread().getName() );
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("主线程运行开始运行: " + Thread.currentThread().getName());
        DeadLockDemo deadLockDemo = new DeadLockDemo();

        new Thread(()->{
           deadLockDemo.methodA();
        }).start();

        new Thread(()->{
            deadLockDemo.methodB();
        }).start();

        System.out.println("主线程运行结束: "+Thread.currentThread().getName());
    }
}

线上死锁

public class DeadLockDemoOnline {

    private static String locka = "locka";

    private static String lockb = "lockb";

    public void methodA(){

        synchronized (locka){
            System.out.println("我是A方法中获得了锁A "+Thread.currentThread().getName() );

            //让出CPU执行权，不释放锁
            try {
                //Thread.sleep(2000);
                System.out.println("methodA");
            } catch (Exception e) {
                e.printStackTrace();
            }

            synchronized(lockb){
                System.out.println("我是A方法中获得了锁B "+Thread.currentThread().getName() );
            }
        }
    }


    public void methodB(){
        synchronized (lockb){
            System.out.println("我是B方法中获得了锁B "+Thread.currentThread().getName() );

            //让出CPU执行权，不释放锁
            try {
                //Thread.sleep(2000);
                System.out.println("methodB");

            } catch (Exception e) {
                e.printStackTrace();
            }

            synchronized(locka){
                System.out.println("我是B方法中获得了锁A "+Thread.currentThread().getName() );
            }
        }
    }

    public static void main(String [] args){

        System.out.println("主线程运行开始运行："+Thread.currentThread().getName());

        DeadLockDemoOnline deadLockDemo = new DeadLockDemoOnline();

        for(int i=0;i<5;i++){
            new Thread(()->{
                deadLockDemo.methodA();
            }).start();

            new Thread(()->{
                deadLockDemo.methodB();
            }).start();
        }
        System.out.println("主线程运行结束："+Thread.currentThread().getName());
    }
}

死锁的4个必要条件

互斥条件：资源不能共享，只能由一个线程使用

请求与保持条件：线程已经获得一些资源，但因请求其他资源发生阻塞，对已经获得的资源保持不释放

不可抢占：有些资源是不可强占的，当某个线程获得这个资源后，系统不能强行回收，只能由线程使用完自己释放

循环等待条件：多个线程形成环形链，每个都占用对方申请的下个资源

只要发⽣死锁，上⾯的条件都成立；只要一个不满足，就不会发生死锁

解决死锁，通过调整锁的范围

public class FixDeadLockDemo {

    private static String locka = "locka";

    private static String lockb = "lockb";

    public void methodA(){
    
        synchronized (locka){
        
            System.out.println("我是A方法中获得了锁A "+Thread.currentThread().getName() );

            //让出CPU执行权，不释放锁
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

        }
        synchronized (lockb){
            System.out.println("我是A方法中获得了锁B "+Thread.currentThread().getName() );
        }
    }

    public void methodB(){
        synchronized (lockb){
            System.out.println("我是B方法中获得了锁B "+Thread.currentThread().getName() );
            //让出CPU执行权，不释放锁
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        synchronized (locka){
            System.out.println("我是B方法中获得了锁A "+Thread.currentThread().getName() );
        }
    }

    public static void main(String[] args) {
        System.out.println("主线程运行开始运行："+Thread.currentThread().getName());

        FixDeadLockDemo deadLockDemo = new FixDeadLockDemo();

        for(int i = 0; i<10;i++){
            new Thread(()->{
                deadLockDemo.methodA();
            }).start();

            new Thread(()->{
                deadLockDemo.methodB();
            }).start();
        }
        System.out.println("主线程运行结束："+Thread.currentThread().getName());
    }
}

6.设计一个简单的不不可重入锁

不可重入锁：若当前线程执行某个方法已经获取了该锁，那么在⽅方法中尝试再次获取锁时，就会获取不到被阻塞

同⼀个线程，重复获取锁失败，形成死锁，这个就是不可重入锁

public class UnreentrantLock {

    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException{

        System.out.println("进入wait等待 " + Thread.currentThread().getName());

        //判断是否已经被锁，如果被锁则当前请求的线程进行等待
        while (isLocked){
            System.out.println("进入wait等待 " + Thread.currentThread().getName());
            wait();
        }
        //进行加锁
        isLocked = true ;
    }

    public synchronized void unlock(){
        System.out.println("进入unlock解锁 " +Thread.currentThread().getName());
        isLocked = false;
        //唤醒对象锁池里面的一个线程
        notify();
    }
}

public class UnreentrantLockMain {

 private UnreentrantLock unreentrantLock = new UnreentrantLock();

    //加锁建议在try里面，解锁建议在finally
    public void  methodA(){

        try {
            unreentrantLock.lock();
            System.out.println("methodA方法被调用");
            methodB();

        }catch (InterruptedException e){
            e.fillInStackTrace();

        } finally {
            unreentrantLock.unlock();
        }
    }

    public void methodB(){

        try {
            unreentrantLock.lock();
            System.out.println("methodB方法被调用");
        }catch (InterruptedException e){
            e.fillInStackTrace();
        } finally {
            unreentrantLock.unlock();
        }
    }

    public static void main(String [] args){

        for(int i=0 ;i<10;i++){
            //演示的是同个线程
            new UnreentrantLockMain().methodA();
        }
    }
}

7.设计一个简单的可重入锁

public class ReentrantLock {

    private boolean isLocked = false;

    //用于记录是不是重入的线程
    private Thread lockedOwer = null;

    //累计加锁次数，加锁一次累加1，解锁一次减少1
    private int lockedCount = 0;

    public synchronized void lock() throws InterruptedException{
        System.out.println("进入lock加锁 " +Thread.currentThread().getName());
        Thread thread = Thread.currentThread();

        //判断是否是同个线程获取锁, 引用地址的比较
        while(isLocked && lockedOwer !=thread){
            System.out.println("进入wait等待 " + Thread.currentThread().getName());
            System.out.println("当前锁状态 isLocked = " +isLocked);
            System.out.println("当前count数量 lockedCount = " +lockedCount);
            wait();
        }

        //进行加锁
        isLocked = true ;
        lockedOwer = thread ;
        lockedCount++;
    }

    public synchronized void unlock(){
        System.out.println("进入unlock解锁 " +Thread.currentThread().getName());

        Thread thread = Thread.currentThread();

        //线程A加的锁，只能由线程A解锁，其他线程B不能解锁
        if(thread == this.lockedOwer){
            lockedCount--;
            if(lockedCount == 0){
                isLocked = false;
                lockedOwer = null;
                //唤醒对象锁池里面的一个线程
                notify();
            }
        }
    }
}

public class ReentrantLockMain {

    private ReentrantLock reentrantLock = new ReentrantLock();

    //加锁建议在try⾥里里⾯面，解锁建议在finally
    public void methodA(){
        try {
            reentrantLock.lock();
            System.out.println("methodA方法被调用");
            methodB();

        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public void methodB() {

        try {
            reentrantLock.lock();
            System.out.println("methodB方法被调用");
        } catch (InterruptedException e) {
            e.fillInStackTrace();
        }finally {
            reentrantLock.unlock();
        }
    }

    public static void main(String[] args) {
        for(int i = 0; i<10; i++){
            new ReentrantLockMain().methodA();
        }
    }
}

8.对synchronized了解不，能否介绍下你对synchronized的理解

synchronized是解决线程安全的问题，常⽤用在同步普通方法、静态方法、代码块中
非公平、可重入

每个对象有一个锁和一个等待队列，锁只能被一个线程持有，其他需要锁的线程需要阻塞等待。锁被释放

后，对象会从队列列中取出一个并唤醒，唤醒哪个线程是不确定的，不保证公平性

两种形式：

方法：⽣成的字节码⽂件中会多一个 ACC_SYNCHRONIZED 标志位，当一个线程访问方法时，会去检查是

否存在ACC_SYNCHRONIZED标识，如果存在，执行线程将先获取monitor，获取成功之后才能执行方法

体，⽅法执⾏行完后再释放monitor。在方法执⾏行期间，其他任何线程都无法再获得同一个monitor对象，

也叫隐式同步

代码块：加了 synchronized 关键字的代码段，⽣成的字节码⽂件会多出 monitorenter 和

monitorexit 两条指令，每个monitor维护着一个记录着拥有次数的计数器, 未被拥有的monitor的该

计数器为0，当一个线程获执⾏行monitorenter后，该计数器自增1;当同一个线程执行monitorexit指令

的时候，计数器再自减1。当计数器为0的时候,monitor将被释放.也叫显式同步

两种本质上没有区别，底层都是通过monitor来实现同步, 只是⽅法的同步是一种隐式的⽅式来实现，无
需通过字节码来完成

jdk1.6后进行了优化，你知道哪些大的变化

有得到锁的资源进入Block状态,涉及到操作系统用户模式和内核模式的切换，代价比较高

jdk6进⾏了优化，增加了从偏向锁到轻量级锁再到重量级锁的过渡，但是在最终转变为重量级锁之后，性
能仍然较低

9.了解CAS不，能否解释下什么是CAS

全称是Compare And Swap，即比较再交换，是实现并发应用到的一种技术

底层通过Unsafe类实现原⼦性操作操作包含三个操作数 — 内存地址（V）、预期原值（A）和新值
(B)。

如果内存位置的值与预期原值相匹配，那么处理器会自动将该位置值更更新为新值 ，若果在第一轮循

环中，a线程获取地址⾥面的值被b线程修改了，那么a线程需要自旋，到下次循环才有可能机会执

⾏。

CAS这个是属于乐观锁，性能较悲观锁有很大的提高

AtomicXXX 等原子类底层就是CAS实现，一定程度比synchonized好，因为后者是悲观锁

10.CAS会存在什么比较严重的问题？

1、自旋时间长CPU利⽤率增加，CAS里面是一个循环判断的过程，如果线程一直没有获取到状态，
cpu资源会一直被占用

2、存在ABA问题

能否解释下什么是ABA问题，怎么避免这个问题呢？

如果一个变量V初次读取是A值，并且在准备赋值的时候也是A值，那就能说明A值没有被修改过吗？其
实是不能的，因为变量V可能被其他线程改回A值，结果就是会导致CAS操作误认为从来没被修改过，
从而赋值给V
给变量加一个版本号即可，在比较的时候不仅要比较当前变量的值 还需要比较当前变量的版本号。

在java5中，已经提供了AtomicStampedReference来解决问题，检查当前引用是否等于预期引

用，其次检查当前标志是否等于预期标志，如果都相等就会以原子的方式将引用和标志都设置为新值

七 、并发编程底层原理

1.知道AQS吗？能否介绍下，它的核⼼思想是什么

AQS的全称为（AbstractQueuedSynchronizer），这个类在java.util.concurrent.locks
包下面。它是一个Java提高的底层同步工具类，比如CountDownLatch、ReentrantLock，
Semaphore，ReentrantReadWriteLock，SynchronousQueue，FutureTask等皆是基于
AQS的

只要搞懂了AQS，那么J.U.C中绝大部分的api都能轻松掌握

简单来说：是用一个int类型的变量表示同步状态，并提供了⼀系列的CAS操作来管理这个同步状态
对象

一个是 state（用于计数器，类似gc的回收计数器）

一个是线程标记（当前线程是谁加锁的），

一个是阻塞队列列（用于存放其他未拿到锁的线程)

例子：线程A调用了lock()方法，通过CAS将state赋值为1，然后将该锁标记为线程A加锁。如果线

程A还未释放锁时，线程B来请求，会查询锁标记的状态，因为当前的锁标记为线程A，线程B未能匹

配上，所以线程B会加入阻塞队列，直到线程A触发了 unlock()方法，这时线程B才有机会去拿到

锁，但是不一定肯定拿到

acquire(int arg) 好⽐加锁lock操作

tryAcquire()尝试直接去获取资源，如果成功则直接返回,AQS⾥面未实现但没有定义成

abstract，因为独占模式下只用实现tryAcquire-tryRelease，而共享模式下只用实现

tryAcquireShared-tryReleaseShared，类似设计模式里面的适配器器模式

addWaiter() 根据不同模式将线程加入等待队列的尾部，有Node.EXCLUSIVE互斥模式、

Node.SHARED共享模式；如果队列不为空，则以通过compareAndSetTail方法以CAS将当前线程

节点加入到等待队列的末尾。否则通过enq(node)方法初始化一个等待队列

acquireQueued()使线程在等待队列中获取资源，一直获取到资源后才返回,如果在等待过程

中被中断，则返回true，否则返回false

release(int arg)好比解锁unlock

独占模式下线程释放指定量的资源，⾥面是根据tryRelease()的返回值来判断该线程是

否已经完成释放掉资源了；在自义定同步器在实现时，如果已经彻底释放资源(state=0)，要返回

true，否则返回false

unparkSuccessor方法用于唤醒等待队列列中下一个线程

你知道的AQS有几种同步方式，实现同步器一般要覆盖哪些方法

独占式: 比如ReentrantLock

共享式：比如Semaphore

存在组合：组合式的如ReentrantReadWriteLock，AQS为使用提供了底层支撑，使用者可以自由

组装实现

1. boolean tryAcquire(int arg)

2. boolean tryRelease(int arg)

3. int tryAcquireShared(int arg)

4. boolean tryReleaseShared(int arg)

5. boolean isHeldExclusively()

不需要全部实现，根据获取的锁的种类可以选择实现不同的方法，⽐如

实现支持独占锁的同步器应该实现tryAcquire、 tryRelease、isHeldExclusively

实现支持共享获取的同步器应该实现tryAcquireShared、tryReleaseShared、

isHeldExclusively

注意：线程获取锁成功后直接返回，不会进入等待队列里面，只有失败的时候才会

获取失败则将当前线程封装为Node.EXCLUSIVE的Node节点插入AQS阻塞队列列的尾部

调用LockSupport.park(this)方式阻塞自己

2.java⾥面的公平锁和⾮非公平锁你知道多少，有没看过ReentrantLock源码？

![](https://i.imgur.com/kmX3OTX.png)

公平锁和⾮公平锁核心区别

![](https://i.imgur.com/HuJnxpi.png)

3.ReentrantLock和synchronized使用的场景是什么，实现机制有什么不同

ReentrantLock和synchronized都是独占锁

synchronized：

1、是悲观锁会引起其他线程阻塞，java内置关键字，

2、无法判断是否获取锁的状态，锁可重入、不可中断、只能是非公平

3、加锁解锁的过程是隐式的,用户不用手动操作,优点是操作简单但显得不够灵活

4、一般并发场景使用足够、可以放在被递归执行的方法上,且不⽤担⼼线程最后能否正确

释放锁

5、synchronized操作的应该是对象头中mark word

ReentrantLock：

1、是个Lock接口的实现类，是悲观锁，

2、可以判断是否获取到锁，可重入、可判断、可公平可不公平

3、需要手动加锁和解锁,且解锁的操作尽量要放在finally代码块中,保证线程正确释放锁

4、在复杂的并发场景中使⽤在重⼊时要却确保重复获取锁的次数必须和重复释放锁的次数
一样，否则可能导致其他线程无法获得该锁。

5、创建的时候通过传进参数true创建公平锁,如果传入的是false或没传参数则创建的是
非公平锁

6、底层不同是AQS的state和FIFO队列来控制加锁

4.知道ReentrantReadWriteLock吗？和ReentrantLock有啥不同？

ReentrantReadWriteLock

1、读写锁接口ReadWriteLock接口的一个具体实现，实现了读写锁的分离，

2、支持公平和非公平，底层也是基于AQS实现

3、允许从写锁降级为读锁

流程：先获取写锁，然后获取读锁，最后释放写锁；但不能从读锁升级到写锁

4、重入：读锁后还可以获取读锁；获取了写锁之后既可以再次获取写锁又可以获取读锁

核⼼：读锁是共享的，写锁是独占的。 读和读之间不会互斥，读和写、写和读、写和写之
间才会互斥，主要是提升了读写的性能

ReentrantLock是独占锁且可重入的，相⽐synchronized而言功能更加丰富也更适合复杂的并发

场景，但是也有弊端，假如有两个线程A/B访问数据，加锁是为了防⽌线程A在写数据， 线程B在读

数据造成的数据不一致； 但线程A在读数据，线程C也在读数据，读数据是不会改变数据没有必要加

锁，但是还是加锁了，降低了程序的性能，所以就有了ReadWriteLock读写锁接口

4.并发编程里面解决生产消费者模型你知道哪几种方式？

核心：要保证⽣产者不会在缓冲区满时放入数据，消费者也不会在缓冲区空时消耗数据

常用的同步方法是采⽤信号或加锁机制

1、wait() / notify()方法

2、await() / signal()方法

用ReentrantLock和Condition实现等待/通知模型

3、Semaphore信号量

4、BlockingQueue阻塞队列

ArrayBlockingQueue

LinkedBlockingQueue

put方法用来向队尾存入元素，如果队列满，则阻塞

take方法用来从队首取元素，如果队列为空，则阻塞

你知道阻塞队列BlockingQueue不？介绍下常见的阻塞队列

BlockingQueue: j.u.c包下的提供了线程安全的队列访问的接口，并发包下很多高级同步类的实
现都是基于阻塞队列实现的

1、当阻塞队列进行插入数据时，如果队列已满，线程将会阻塞等待直到队列非满

2、从阻塞队列读数据时，如果队列为空，线程将会阻塞等待直到队列⾥面是非空的时候

常见的阻塞队列

ArrayBlockingQueue：

基于数组实现的一个阻塞队列，需要指定容量⼤小，FIFO先进先出顺序

LinkedBlockingQueue：

基于链表实现的一个阻塞队列，如果不指定容量大小，默认

Integer.MAX_VALUE, FIFO先进先出顺序

PriorityBlockingQueue：

一个支持优先级的无界阻塞队列，默认情况下元素采用自然顺序升序排序，也

可以自定义排序实现 java.lang.Comparable接口

DelayQueue：

延迟队列，在指定时间才能获取队列列元素的功能，队列头元素是最接近过期的

元素，⾥面的对象必须实现 java.util.concurrent.Delayed 接口并实现CompareTo和

getDelay方法

5.你知道非阻塞队列ConcurrentLinkedQueue不，它怎么实现线程安全的？

线程安全原因：

ConcurrentLinkedQueue是基于链表实现的无界线程安全队列，采用FIFO进行排序

保证线程安全的三要素：原子、有序、可见性

1、底层结构是Node，链表头部和尾部节点是head和tail，使用节点变量和内部类属性使用

volatile声明保证了有序和可见性

2、插入、移除、更新操作使用CAS无锁操作，保证了原子性

3、假如多线程并发修改导致 CAS 更新失败，采用for循环插入保证更新操作成功

6.平时多线程⽤的挺多的，写出3条你遵循的多线程最佳实践

给不同模块的线程起名称，方便便后续排查问题

使用同步代码块或者同步的方法的时候，尽量减小同步范围

多用并发集合少用同步集合

支持线程安全

同步集合：Hashtable/Vector/同步工具类包装Collections.synXXX

并发集合：ConcurrentHashMap、CopyOnWriteArrayList

线上业务需要使用多线程，优先考虑线程池是否更加合适，然后判断哪种线程池比较好，最后才是自己创建单一线程

用过线程池不? 有什么好处， java里里有哪些是常用的线程池

好处：重用存在的线程，减少对象创建销毁的开销，有效的控制最大并发线程数，提高系统资源的使
⽤率，同时避免过多资源竞争，避免堵塞，且可以定时定期执行、单线程、并发数控制，配置任务过多任务后的拒绝策略略等功能
类别

newFixedThreadPool

一个定长线程池，可控制线程最大并发数

newCachedThreadPool

一个可缓存线程池

newSingleThreadExecutor

一个单线程化的线程池，用唯一的工作线程来执行任务

newScheduledThreadPool

一个定长线程池，支持定时/周期性任务执行

7.是否知道线程池里面的坑

Executors创建的线程池底层也是调用 ThreadPoolExecutor，只不过使用不同的参数、队列、
拒绝策略略等,如果使用不当，会造成资源耗尽问题；

直接使用ThreadPoolExecutor让使用者更加清楚线程池允许规则，常见参数的使用，避免风险

常见的线程池问题：

newFixedThreadPool和newSingleThreadExecutor:

队列使用LinkedBlockingQueue，队列长度为 Integer.MAX_VALUE，可能造成堆积,导致OOM

![](https://i.imgur.com/WM7UEZJ.png)

newScheduledThreadPool和newCachedThreadPool:

线程池里面允许最大的线程数是Integer.MAX_VALUE，可能会创建过多线程，导致OOM

![](https://i.imgur.com/DWxCFOx.png)

8.ThreadPoolExecutor构造函数⾥面的参数你是否掌握，能否解释下各个参数的作用

public ThreadPoolExecutor(int corePoolSize,

int maximumPoolSize,

long keepAliveTime,

TimeUnit unit,

BlockingQueue<Runnable> workQueue,

ThreadFactory threadFactory,

RejectedExecutionHandler handler)

corePoolSize：核心线程数，线程池也会维护线程的最少数量，默认情况下核心线程会一直存活，

即使没有任务也不会受存keepAliveTime控制

坑：在刚创建线程池时线程不会立即启动，到有任务提交时才开始创建线程并逐步线程数目达到

corePoolSize

maximumPoolSize：线程池维护线程的最大数量，超过将被阻塞

坑：当核心线程满，且阻塞队列也满时，才会判断当前线程数是否小于最大线程数，才决定是否创建新线程

keepAliveTime：非核心线程的闲置超时间，超过这个时间就会被回收，直到线程数量等于corePoolSize

unit：指定keepAliveTime的单位，如TimeUnit.SECONDS、TimeUnit.MILLISECONDS

workQueue:线程池中的任务队列,常用的是 ArrayBlockingQueue、LinkedBlockingQueue、SynchronousQueue

threadFactory：创建新线程时使用的工厂

handler: RejectedExecutionHandler是一个接口且只有一个方法,线程池中的数量大于maximumPoolSize，
对拒绝任务的处理策略，默认有4种策略AbortPolicy、CallerRunsPolicy、DiscardOldestPolicy、DiscardPolicy

八.消息队列

1.你用过消息队列，引入队列有啥优缺点，对比其他消息中间产品，选择这款的原因是啥？

优点：解耦系统、异步化、削峰

缺点: 系统可用性降低、复杂度增高、维护成本增高

主流消息队列Apache ActiveMQ、Kafka、RabbitMQ、RocketMQ

ActiveMQ：http://activemq.apache.org/

Kafka：http://kafka.apache.org/

RabbitMQ：http://www.rabbitmq.com/

RocketMQ：http://rocketmq.apache.org/

2.消息队列的发送方式有哪几种，使用场景分别是怎样的？

发送⽅方式一般分三种

SYNC 同步发送

应用场景：重要通知邮件、报名短信通知、营销短信系统等

ASYNC 异步发送

应用场景：对RT时间敏感,可以支持更高的并发，回调成功触发相对应的业务，比如注册成功后通知积分系统发放优惠券

ONEWAY 无需要等待响应

应用场景：主要是日志收集，适用于某些耗时非常短，但对可靠性要求并不高的场景, 也就是

LogServer, 只负责发送消息，不等待服务器回应且没有回调函数触发，即只发送请求 不等待应答

有没用过延迟消息，使用场景是怎样的？

什么是延迟消息：

Producer 将消息发送到消息队列broker服务端，但并不期望这条消息立马投递，而是推迟

到在当前时间点之后的某一个时间投递到 Consumer 进行消费

使用场景一：通过消息触发一些定时任务，比如在某一固定时间点向用户发送提醒消息

使用场景二：消息生产和消费有时间窗口要求，比如在天猫电商交易中超时未支付关闭订单的场景，在订单

创建时会发送一条延时消息。这条消息将会在30分钟以后投递给消费者，消费者收到此消息后需要判断

对应的订单是否已完成支付。 如支付未完成，则关闭订单。如已完成支付则忽略

3.你用的队列是否支持顺序消息，是怎么实现顺序消息的？

什么是顺序消息：

消息的生产和消费顺序一致

全局顺序：topic下面全部消息都要有序(少用)，性能要求不高，所有的消息严格按照 FIFO

原则进行消息发布和消费的场景，并行度成为消息系统的瓶颈, 吞吐量不够

使用场景：在证券处理中，以人⺠币兑换美元为例子，在价格相同的情况下，先出价者优先处

理，则可以通过全局顺序的方式按照FIFO的方式进行发布和消费

局部顺序：只要保证一组消息被顺序消费即可，性能要求高

使用场景：电商的订单创建，同一个订单相关的创建订单消息、订单支付消息、订单退款消息、

订单物流消息、订单交易成功消息 都会按照先后顺序来发布和消费

（阿⾥巴巴集团内部电商系统均使用局部顺序消息，既保证业务的顺序，同时又能保证业务的高
性能）

下面是用RocketMQ举例(⽤用kafka或rabbitmq类似)

一个topic下面有多个queue

顺序发布：对于指定的一个 Topic，客户端将按照一定的先后顺序发送消息

举例：订单的顺序流程是：创建、付款、物流、完成，订单号相同的消息会被先后发送到同一个队列列中，

根据MessageQueueSelector里面自定义策略，根据同个业务id放置到同个queue里面，如订单号取模

运算再放到selector中，同一个模的值都会投递到同一条queue

public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg)

{
//如果是订单号是字符串串，则进⾏行行hash,得到一个hash值

Long id = (Long) arg;

long index = id % mqs.size();

return mqs.get((int)index);

}
顺序消费：对于指定的一个 Topic，按照一定的先后顺序接收消息，即先发送的消息一定会先被客户端接收到。

举例：消费端要在保证消费同个topic⾥的同个队列，不应该用MessageListenerConcurrently，

应该使用MessageListenerOrderly，自带单线程消费消息，不能再Consumer端再使用多线程去消

费，消费端分配到的queue数量是固定的，集群消费会锁住当前正在消费的队列集合的消息，所以会保证顺序消费。

注意：

顺序消息暂不⽀支持广播模式

顺序消息不支持异步发送方式，否则将无法严格保证顺序

不能再Consumer端再使用多线程去消费

4.有没做消息的重复消费处理，是怎么做的

幂等性：一个请求，不管重复来多少次，结果是不会改变的。

RabbitMQ、RocketMQ、Kafka等任何队列不保证消息不重复，如果业务需要消息不重复消费，则需要消费端处理业务消息要保持幂等性

⽅式一：Redis的setNX() , 做消息id去重 java版本目前不支持设置过期时间

//Redis中操作，判断是否已经操作过 TODO

boolean flag = jedis.setNX(key);

if(flag){

//消费

}else{

//忽略略，重复消费

}

方式二：redis的Incr原子操作：key自增，大于0返回值大于0则说明消费过，(key可以是消息的md5取值, 或者如果消息id设计合理直接用id做key)

int num = jedis.incr(key);

if(num == 1){

//消费

}else{

//忽略略，重复消费

}

方式三：数据库去重表

设计一个去重表，某个字段使用Message的key做唯一索引，因为存在唯一索引，所以重复消费会失败

CREATE TABLE message_record ( id int(11) unsigned NOT NULL

AUTO_INCREMENT, key varchar(128) DEFAULT NULL, create_time datetime

DEFAULT NULL, PRIMARY KEY ( id ),

 UNIQUE KEY key ( key ) )
 
ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

5.消息队列如何保证消息的可靠性传输吗

消息可靠性传输，是非常重要，消息如果丢失，可能带来严重后果，一般从是个角度去分析

producer端：

不采用oneway发送，使用同步或者异步方式发送，做好重试，但是重试的Message key
必须唯一

投递的日志需要保存，关键字段，投递时间、投递状态、重试次数、请求体、响应体

broker端：

多主多从架构，需要多机房

同步双写、异步刷盘 (同步刷盘则可靠性更高，但是性能差点，根据业务选择)

机器断电重启：异步刷盘，消息丢失；同步刷盘消息不丢失

硬件故障：可能存在丢失，看队列架构

consumer端

消息队列一般都提供的ack机制，发送者为了保证消息肯定消费成功，只有消费者明确表

示消费成功，队列才会认为消息消费成功，中途断电、抛出异常等都不会认为成功—即都会重新投

递，每次在确保处理理完这个消息之后，在代码里调用ack，告诉消息队列列消费成功

消费端务必做好幂等性处理

消息消费务必保留日志，即消息的元数据和消息体，

6.线上故障了了，怎么处理

  消息堆积了10小时，有几千万条消息待处理，现在怎么办?
  
  修复consumer, 然后慢慢消费？也需要几小时才可以消费完成，新的消息怎么办？
  
  核心思想：紧急临时扩容，更快的速度去消费数据
  
  - 修复Consumer不消费问题，使其恢复正常消费，根据业务需要看是否要暂停
  
  - 临时topic队列列扩容，并提高消费者能力，但是如果增加Consumer数量，但是堆积的topic里面的
  
  message queue数量固定，过多的consumer不能分配到message queue
  
  - 编写临时理分发程序，从旧topic快速读取到临时新topic中，新topic的queue数量扩容多倍，然
  
  后再启动更多consumer进行在临时新的topic里消费
  
  - 直到堆积的消息处理完成，再还原到正常的机器数量
  
九.MySQL

1.Mysql事务的四大特性

事务的四大特性ACID

原子性Atomicity：
一个事务必须被事务不可分割的最小工作单元，整个操作要么全部成功，要么全部失败，一般就是通过

commit和rollback来控制

一致性Consistency:

数据库总能从一个一致性的状态转换到另一个一致性的状态，比如课堂下单支付成功后，开通视频

播放权限，只要有任何一方发生异常就不会成功提交事务

隔离性Isolation:

一个事务相对于另一个事务是隔离的，一个事务所做的修改是在最终提交以前，对其他事务是不可见的

持久性Durability:

一旦事务提交，则其所做的修改就会永久保存到数据库中。此时即使系统崩溃，修改的数据也不会丢失

2.解释下脏读、不可重复读、幻读的意思

脏读: 事务中的修改即使没有提交，其他事务也能看见，事务可以读到未提交的数据称为脏读

不可重复读: 同个事务前后多次读取，不能读到相同的数据内容，中间另一个事务也操作了了该同一数据

幻读：当某个事务在读取某个范围内的记录时，另外一个事务又在该范围内插入了新的记录，当之前的事务

再次读取该范围的记录时，发现两次不一样，产⽣生幻读

幻读和不可重复读的区别是：前者是一个范围，后者是本身，从总的结果来看, 两者都表现为两次读取的

结果不一致

常见的隔离级别由低到高有哪几种，mysql默认是哪种

事务的隔离级别越高，事务越安全，但是并发能力越差。

Read Uncommitted(未提交读，读取未提交内容)

事务中的修改即使没有提交，其他事务也能看见，事务可以读到为提交的数据称为脏读

也存在不可重复读、幻读问题

例子：
课堂运营小姐姐配置了一个课程活动，原价500元的课程，配置成50元，但是事务没提交。
你刚好看到这个课程那么便宜准备购买，但是⼩姐姐马上回滚了事务，重新配置并提交了事务，你
准备下单的时候发现价格变回了500元

Read Committed(提交读，读取提交内容)

一个事务开始后只能看见已经提交的事务所做的修改，在事务中执行两次同样的查询可能得到不一样的
结果，也叫做不可重复读(前后多次读取，不能读到相同的数据内容)，也存幻读问题

例子：

老王在课堂有1000积分，准备去兑换《课程》，查询数据库确实有1000积分

但是⽼老老王的⼥女女友同时也在别的地方登录，把1000积分兑换了了《专题课程》，且在

⽼王之前提交事务；当系统帮老王兑换《题课程》是发现积分预计没了，兑换失败。

⽼王事务A事先读取了数据，他女友事务B紧接更新了数据且提交了事务，事务A再次读取该数据时，数

据已经发生了改变

Repeatable Read(可重复读,mysql默认的事务隔离级别)

解决脏读、不可重复读的问题，存在幻读的问题，使用MMVC机制实现可重复读

例⼦
老王在课堂有1000积分，准备去兑换《课程》，查询数据库确实有1000积分
⽼王的女友同时也在别的地方登录先兑换了这个《课程》，⽼王的事务提交的时候发现存在
了，之前读取的没用了，像是幻觉

幻读问题：MySQL的InnoDB引擎通过MVCC⾃自动帮我们解决,即多版本并发控制

Serializable(可串串⾏行行化)

解决脏读、不可重复读、幻读，可保证事务安全，但强制所有事务串行执行，所以并发效率低

3.Mysql常见的存储引擎，新版Mysql默认是哪个

常见的有多类，InnoDB、MyISAM、MEMORY、MERGE、ARCHIVE、CSV等

一般比较常用的有InnoDB、MyISAM

MySQL 5.5以上的版本默认是InnoDB，5.5之前默认存储引擎是MyISAM

4.mysql的存储引擎 innodb和myisam有什么区别，应该怎么选择

区别项           Innodb                                                myisam
事务             ⽀持                                                  不支持
锁粒度       行锁，适合高并发                                       表锁，不适合高并发
是否默认          默认                                                 非默认
支持外键         支持外键                                              不支持
适合场景     读写均衡,写大于读场景，需要事务                   读多写少场景，不需要事务
全文索引     不支持，可以通过插件实现, 更多使用ElasticSearch        支持全文索引

5.mysql常用的功能索引有哪些

  分别在什么场景下使用
  
  创建语句是怎样的
  
  ![](https://i.imgur.com/6Rans00.png)
  
6.索引的使用场景和注意事项

中型公司或者业务发展好的公司，一天新增几百万数据量

业务核心数据存储在Mysql里面，针对业务创建合适的索引

打点数据、日志等存储在ElasticSearch或者MongoDB里面

你创建索引的时候主要考虑啥，使用索引的优缺点有哪些，使用应该注意些什么

索引好处：

快速定位到表的位置，减少服务器扫描的数据

有些索引存储了实际的值，特定情况下只要使用索引就能完成查询

索引缺点：

索引会浪费磁盘空间，不要创建非必要的索引

插⼊、更新、删除需要维护索引，带来额外的开销

索引过多，修改表的时候重构索引性能差

索引优化实践

前缀索引，特别是TEXT和BLOG类型的字段，只检索前面几个字符，提高检索速度

尽量使用数据量少的索引，索引值过长查询速度会受到影响

选择合适的索引列顺序

内容变动少，且查询频繁，可以建立多几个索引

内容变动频繁，谨慎创建索引

根据业务创建适合的索引类型，比如某个字段常用来做查询条件，则为这个字段建立索引提高查询速度

组合索引选择业务查询最相关的字段

十.数据库设计查询和上线

1.数据库查询的指令有多个，说下执行顺序 select、where、from、group by、having、order by

from 从哪个表查询

where 初步过滤条件

group by 过滤后进行分组[重点]

having 对分组后的数据进行二次过滤[重点]

select 查看哪些结果字段

order by 按照怎样的顺序进行排序返回[重点]

select video_id,count(id) num from chapter group by video_id having num >10 order by video_id desc

2.varchar(len) char(len) len存储的是字符还是字节

  MySQL中的varchar和char有什么区别，应该怎么选择
  
![](https://i.imgur.com/1PKCkP5.png)

3.MySQL中的datetime和timestamp有什么区别

![](https://i.imgur.com/RhG0kra.png)

为什么timestamp只能到2038年

MySQL的timestamp类型是4个字节，最⼤大值是2的31次⽅方减1，结果是2147483647，
转换成北北京时间就是2038-01-19 11:14:07
  



















































