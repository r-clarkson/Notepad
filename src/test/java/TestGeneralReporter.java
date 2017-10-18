package src.main.java;

import static org.junit.Assert.assertEquals;
import java.io.*;
import java.nio.file.*;
import junit.framework.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestGeneralReporter{
  ReportManager rm;
  File testFile;
  Notebook nb;
  GeneralReporter gr;

  @Before
  public void setUp(){
    nb = new Notebook();
    testFile = new File("src"  + File.separator  + "test"  + File.separator  + "java"  + File.separator  + "notes" + File.separator + "growingBasil.txt");
    Main.passFiles(nb);
    gr = new GeneralReporter(nb.getMaps().get(0),true,true);
  }
  @After
  public void tearDown(){
    nb = null;
    testFile = null;
    gr = null;
  }

  @Test
  public void testPrintNotesWithOneOrMoreMentions(){
    /** returns true if map is not empty (something must have been printed if its got elements) */
    assertEquals(gr.printNotesWithOneOrMoreMentions(),true);
  }

  @Test
  public void testIterateLists(){
    /** makes sure LL value in hashmap is not size 0 */
    assertEquals(gr.iterateLists("#hey"),true);
  }

}
