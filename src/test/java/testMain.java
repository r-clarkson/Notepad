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
  @Test
  public void testPassFiles(){
    assertEquals(true,Main.passFiles(n));
  }
  @Test
  public void testGetFilepath(){
    //should return false without any user input
    assertEquals(false,Main.getFilePath());
  }
  @Test
  public void testPrintMenu(){
    assertEquals(true,Main.printMenu(new File(".." + File.separator + "Notepad" + File.separator + "resources" + File.separator + "commands.txt")));
  }
}
