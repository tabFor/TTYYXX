public class Son1 extends Father{
Son1(){

}
  /**
   *
   * @return
   */
  @Override
  public String show(boolean t) {
    return super.show(t)+" Son";
  }
}
