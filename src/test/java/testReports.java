package classes;
import junit.framework.*;
import static org.junit.Assert.*;
import java.io.*;
import java.nio.file.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testReports{
  /**
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
    /** should return false as -c is not a command option
    assertEquals(report.generateReport("-c"),false);
  }
  @Test
  public void testPrintSpecificMention(){
    /** #hey is present in test note files
    assertEquals(report.printSpecificMention("#hey"),true);
  }
  @Test
  public void testPrintNotesWithOneOrMoreMentions(){
    /** returns true if map is not empty (something must have been printed if its got elements)
    assertNotEquals(notebook.getMaps().get(0).size(),0);
  }
  @Test
  /** this function tests getsentence() as well because it relies on it to return true
  public void testGetFileForSentences(){
    assertEquals(report.getFileForSentences("#hey"),true);
  }
  @Test
  public void testIterateLists(){
    /** makes sure LL value in hashmap is not size 0
    assertEquals(report.iterateLists(notebook.getMaps().get(0),"#hey"),true);
  }
  @Test
  public void testGetContinue(){
    /** should return false without input during testing
    assertEquals(report.getContinue(),false);
  }
  @Test
  public void testPrintInformation(){
    assertEquals(report.printInformation("#","-l"),true);
  }
**/
}
