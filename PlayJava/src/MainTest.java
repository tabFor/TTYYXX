import java.sql.SQLOutput;
import java.text.Collator;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import org.junit.Test;

/**
 * @author 杨
 * @version 1.1.1
 */
public class MainTest {

  public static void main(String[] args) {
    Son1 s = new Son1();
    Father f = new Father();
    SonSon ss = new SonSon();
    MainTest m = new MainTest();
    m.method();
  }

  @Test
  public void method() {
    Object o1 = (double) 1;
    System.out.println(o1);//

    Object o2 = 1;
    System.out.println(o2);//
  }

  @Test
  public void TestXin() {
    // 一个包含中国人名的字符串数组
    String[] names = {
        "张三", "王五", "李四", "赵六", "陈七"
    };

    // 创建一个中文排序比较器
    Collator collator = Collator.getInstance(Locale.CHINA);

    // 使用比较器对数组进行排序
    Arrays.sort(names, new Comparator<String>() {
      @Override
      public int compare(String name1, String name2) {
        return collator.compare(name1, name2);
      }

    });

    // 打印排序后的结果
    for (String name : names) {
      System.out.println(name);
    }
  }

  @Test
  public void Test2() {
    MyThread m1 = new MyThread();
    m1.setPriority(1);
    m1.start();
    try {
      Thread.sleep(10000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    MyThread2 m2 = new MyThread2();
    m2.setPriority(2);
    if (m1.isAlive()) {
      m2.start();
    }
  }
}

class MyThread extends Thread {

  @Override
  public void run() {
    Thread.currentThread().setName("A");
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();

    }
    for (int i = 0; i < 100; i++) {
      if (i % 2 == 0) {
        System.out.println(Thread.currentThread().getName() + ":" + i);

      }
      if (i == 40) {
        try {
          join();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }
    try {
      this.wait();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}

class MyThread2 extends Thread {

  @Override
  public void run() {
    for (int i = 0; i < 100; i++) {
      if (i % 2 != 0) {
        System.out.println(Thread.currentThread().getName() + ":" + i);
      }
    }
  }
}
//


class MyTest {

  public static void main(String[] args) {
    Runnable r = new MyTest2();
    Thread m1 = new Thread(r);
    Thread m2 = new Thread(r);
    Thread m3 = new Thread(r);
    m1.start();
    m2.start();
    m3.start();
  }
}

//通过实现Runnable接口，重写run方法，创建线程对象
class MyTest2 implements Runnable {

  int ticket = 100;
  final Object lock = new Object();

  @Override
  public void run() {
    while (true) {
      synchronized (lock) {
        if (ticket > 0) {//线程不安全，可能出现三个窗口出售同一张票的情况
          System.out.println("窗口" + Thread.currentThread().getName() + "售出门票:" + ticket--);
          Thread.yield();
          try {
            Thread.sleep(100);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        } else {
          break;
        }
      }
    }
  }
}

//