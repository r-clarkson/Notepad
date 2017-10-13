package notepad.src.main.java.code;
import junit.framework.*;
import static org.junit.Assert.*;
import java.io.*;
import java.nio.file.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testReports{
  Notebook notebook;
  Reports report;
  @Before
  public void setUp(){
    notebook = new Notebook();
    Main.passFiles(notebook);
    report = new Reports(notebook);
  }
  @After
  public void tearDown(){
    notebook = null;
  }
  @Test
  public void testGenerateReport(){
    /** should return false as -c is not a command option */
    assertEquals(report.generateReport("-c"),false);
  }
  @Test
  public void testPrintSpecificMention(){
    /** #hey is present in test note files */
    assertEquals(report.printSpecificMention("#hey"),true);
  }
  @Test
  public void testPrintNotesWithOneOrMoreMentions(){
    /** returns true if map is not empty (something must have been printed if its got elements) */
    assertNotEquals(notebook.getMaps().get(0).size(),0);
  }
  @Test
  public void testIterateLists(){
    /** makes sure LL value in hashmap is not size 0 */
    assertNotEquals(notebook.getMaps().get(0).get(0).size(),0);
  }
  @Test
  public void testGetContinue(){
    /** should return false without input during testing */
    assertEquals(report.getContinue(),false);
  }
}
