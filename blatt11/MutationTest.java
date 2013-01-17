public class MutationTest implements Runnable
{
  
  private double x;
  private int n;
  private int test;

  public static void main(String[] args)
  {
    
    
    double x = Double.parseDouble(args[0]);
    int n = Integer.parseInt(args[1]);
    
    MutationTest[] mts = new MutationTest[7];
    Thread[] threads = new Thread[7];
    for (int test = 0; test < 7; test++)
    {
      mts[test] = new MutationTest(x, n, test);
      threads[test] = new Thread(mts[test]);
      threads[test].run();
    }
    
  }
  
  public MutationTest(double x, int n, int test)
  {
    this.x = x;
    this.n = n;
    this.test = test;
  }
  
  public void run()
  {
    double e;
    switch (this.test)
    {
      case 0:
        e = Pow2.pow2(x, n);
        System.out.println("Test " + test + ": " + e);
        break;
      case 1:
        e = Pow2M1.pow2(x, n);
        System.out.println("Test " + test + ": " + e);
        break;
      case 2:
        e = Pow2M2.pow2(x, n);
        System.out.println("Test " + test + ": " + e);
        break;
      case 3:
        e = Pow2M3.pow2(x, n);
        System.out.println("Test " + test + ": " + e);
        break;
      case 4:
        e = Pow2M4.pow2(x, n);
        System.out.println("Test " + test + ": " + e);
        break;
      case 5:
        e = Pow2M5.pow2(x, n);
        System.out.println("Test " + test + ": " + e);
        break;
      case 6:
        e = Pow2M6.pow2(x, n);
        System.out.println("Test " + test + ": " + e);
        break;
    }
  }
}