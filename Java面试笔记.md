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

有序性: 程序执行的顺序按照代码的先后顺序执行，因为处理器器可能会对指令进行重排序
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



































