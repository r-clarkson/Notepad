public class testMain{
  protected Notebook n;

  public void setUp(){
    Main m = new Main();
    n = new Notebook();
    File testFile = new File("src"  + File.separator  + "test"  + File.separator  + "java"  + File.separator  + "notes" + File.separator + "growingBasil.txt");
  }

  public void tearDown(){
    n = null;
    testFile = null;
  }

  public void testPassFiles(){
    assertEquals(true,m.passFiles(testFile,n));
  }  
}
