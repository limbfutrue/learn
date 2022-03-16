Handler面试总结

 Message.obtain()：obtain()内部维持了一个链表形式的 Meesage  对象缓存池，这样会节省重复实例化对象产生的开销成本。

Looper 是线程独立的且每个线程只能存在一个 Looper。

// Worker 线程 Looper 实例，用 ThreadLocal 保存的对象都是线程独立的
static final ThreadLocal<Looper> sThreadLocal = newThreadLocal<Looper>();

如果使用匿名内部类创建handler对象，可能会造成内存泄漏。当我们在子线程执行一项耗时操作时，用户退出程序，Activity 需要被销毁，而 Handler 还在持有 Activity 的引用导致无法回收，就会引发内存泄漏

解决方法分为两步
1. 生成内部类时把内部类声明为静态的。
2. 使用弱引用来持有外部类引用。



Message 缓存池

为什么要使用缓存次？

Message 功能十分单一且状态很少，它只是一个具体发送消息的载体，但是使用数量十分庞大，回收用过的Message 不仅可以有效的减少重复消耗系统资源且回收它的成本很低

谁负责回收 Message？

Looper在分发 Message 给宿主 Handler 之后，确定了 Message 已经完成了它的使命直接就会将它回收。

# Handler  面试题全解析

## 1.个 一个线程有几个 Looper个 ？几个 Handler ？

一个 Thread 只能有一个 Looper，一个 MessageQueen，可以有多个 Handler

## 2.Handler ？ 内存泄漏原因？  以及最佳解决方案？

泄露原因：
Handler 允许我们发送延时消息，如果在延时期间用户关闭了 Activity，
那么该 Activity 会泄露。 这个泄露是因为 Message 会持有 Handler，而
又因为 Java 的特性，内部类会持有外部类，使得 Activity 会被 Handler
持有，这样最终就导致 Activity 泄露。
解决方案
1. 最直接的思路就是避免使用非静态内部类。使用 Handler 的时候，放在一个新建的
文件中来继承 Handler 或者使用静态的内部类来替代。静态内部类不会隐含的持有
外部类的引用，因此这个 activity 也就不会出现内存泄漏问题。
2. 如果你需要在 Handler 内部调用外部 Activity 的方法，你可以让这个 Handler 持有这
个 Activity 的弱引用，这样便不会出现内存泄漏的问题了。
3. 另外，对于匿名类 Runnable，我们同样可以设置成静态的，因为静态内部类不会持
有外部类的引用。
4. 注意：如果使用 Handler 发送循环消息，最好是在 Activity 的 OnDestroy 方法中调用
mLeakHandler.removeCallbacksAndMessages(null);移除消息。（这不是解决内存泄漏
的方法）

## 3.为何主线程可以 new Handler ？如果想要在子线程中 new Handler  要做些什么准备？


每一个 handler 必须要对应一个 looper，主线程会自动创建 Looper 对象，不需要我们手动创建，所以主线程可以直接创建 handler。在 new handler 的时候没有传入指定的 looper 就会默认绑定当前创建 handler 的线程的 looper，如果没有 looper 就报错。因为在主线程中，Activity 内部包含一个 Looper 对象，它会自动管理Looper，处理子线程中发送过来的消息。而对于子线程而言，没有任何对象帮助我们维护Looper 对象，所以需要我们自己手动维护。所以要在子线程开启 Handler  要先创建 Looper ，并开启 Looper  循环如果在子线程中创建了一个 Handler，那么就必须做三个操作：

1. prepare()；
2. loop()；
3. quit()；

## 4.子线程中维护的 Looper ，消息队列无消息的时候的处理方是什么？有什么用？

在Looper中有quitSafely()和 quit()两个函数，这两个函数是调用的 MessageQueue 的 quit()。它会 remove 所有消息，释放了内存。

Looper 就结束了(跳出了死循环)，则达成了第二个作用： 释放线程。

## 5.个 既然可以存在多个 Handler往 MessageQueue中添加数据（发消息时各个Handler  可能处于不同线程），那它内部是如何确保线程安全的？

拿当前的 MessageQueue 对象作为锁对象，这样通过加锁就可以确保操作的原子性和可见性了

![1641988544302](C:\Users\dada\AppData\Roaming\Typora\typora-user-images\1641988544302.png)

## 6.用 我们使用 Message  时应该如何创建它？

创建的它的方式有两种：
一种是直接 new 一个 Message 对象；
另一种是通过调用 Message.obtain() 的方式去复用一个已经被回收的Message，当然日常使用者是推荐使用后者来拿到一个 Message，因为不断的去创建新对象的话，可能会导致垃圾回收区域中新生代被占满，从而触发 GC。

Message 中的 sPool 就是用来存放被回收的 Message，当我们调用 obtain 后，会先查看是否有可复用的对象，如果真的没有才会去创建一个新的 Message 对象。
主要的 Message 回收时机是：
1.在 MQ 中 remove Message 后；
2.单次 loop 结束后；
3.我们主动调用 Message 的 recycle 方法后

## 7.Looper  死循环为什么不会导致应用卡死？

Launch 桌面的图标第一次启动 Activity 时，会最终走到 ActivityThread 的 main 方法，在 main 方法里面创建 Looper 和 MessageQueue 处理主线程的消息，然后Looper.loop()方法进入死循环，我们的 Activity 的生命周期都是通过 Handler 机制处理的，包括 onCreate、onResume 等方法，下面是 loop 方法循环。主线程的主要方法就是消息循环，一旦退出消息循环，那么你的应用也就退出了，Looer.loop（）方法可能会引起主线程的阻塞，但只要它的消息循环没有被阻塞，能一直处理事件就不会产生 ANR 异常。

造成 ANR  的不是主线程阻塞， ，而是主线程的 Looper  消息处理过程发生了任务阻塞，无法响应手势操作，不能及时刷新 UI 。阻塞与程序无响应没有必然关系，虽然主线程在没有消息可处理的时候是阻塞的，但是只要保证有消息的时候能够立刻处理，程序是不会无响应的。

**总结： 应用卡死与这个 Looper  没有关系 ， 应用在没有消息需要处理的时候 ，它是在睡眠，释放线程；卡死是 ANR ，而 Looper 是睡眠。**



































