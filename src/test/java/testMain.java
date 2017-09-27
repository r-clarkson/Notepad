package notepad.src.main.java.code;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.nio.file.*;
import junit.framework.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testMain{
  protected Notebook n;
  protected File testFile;
  
  
  @Before
  public void setUp(){
    n = new Notebook();
    testFile = new File("src"  + File.separator  + "test"  + File.separator  + "java"  + File.separator  + "notes" + File.separator + "growingBasil.txt");
  }
  @After
  public void tearDown(){
    n = null;
    testFile = null;
  }
}
  /** Test not working, needs to be rewritten
  public void testPassFiles(){
    assertEquals(true,Main.passFiles(testFile,n));
  }  
  **/

