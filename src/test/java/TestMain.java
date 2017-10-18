package src.main.java;

import static org.junit.Assert.assertEquals;
import java.io.*;
import java.nio.file.*;
import junit.framework.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestMain{
  protected Notebook n;
  protected NotebookManager nm;
  protected File testFile;


  @Before
  public void setUp(){
    n = new Notebook();
    testFile = new File("src"  + File.separator  + "test"  + File.separator  + "java"  + File.separator  + "notes" + File.separator + "growingBasil.txt");
    nm = new NotebookManager();
  }
  @After
  public void tearDown(){
    n = null;
    testFile = null;
    nm = null;
  }
  @Test
  public void testPassFiles(){
    assertEquals(true,Main.passFiles(n));
  }
  @Test
  public void testGetFilepath(){
    /** should return false with no user input */
    assertEquals(false,Main.getFilePath());
  }
  @Test
  public void testPrintMenu(){
    assertEquals(true,Main.printMenu(new File(".." + File.separator + "Notepad" + File.separator + "resources" + File.separator + "commands.txt")));
  }
  @Test
  public void testGetCommand(){
    assertEquals(Main.getCommand(n,nm),false);
  }
  @Test
  public void testCommandSwitch(){
    String[] testArray = new String[1];
    testArray[0] = "help";
    assertEquals(Main.commandSwitch(testArray,n,nm),true);
  }
}
