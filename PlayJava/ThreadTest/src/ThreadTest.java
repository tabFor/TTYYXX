import java.util.concurrent.locks.ReentrantLock;

public class ThreadTest {

  public static void main(String[] args) {
    ReentrantLock r=new ReentrantLock();
    new Thread(new Runnable() {
      @Override
      public void run() {
        A.AddIn();
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
    new Thread(new Runnable() {
      @Override
      public void run() {
        B.AddIn();
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

}
class A{
  public static synchronized void AddIn() {
    System.out.println("AAAAddIn");
    B.AddOut();
  }
  public  static synchronized void AddOut() {
    System.out.println("AAAAddOut");
  }
}
class B{
  public static synchronized void AddIn() {
    System.out.println("BBBAddIn");
    A.AddOut();
  }
  public static synchronized void AddOut() {
    System.out.println("BBBAddOut");
  }
}