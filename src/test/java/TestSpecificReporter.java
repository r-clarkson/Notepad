package src.main.java;

import static org.junit.Assert.*;
import java.io.*;
import java.util.*;
import java.nio.file.*;
import junit.framework.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestSpecificReporter{
  SpecificReporter sr;
  Notebook nb;

  @Before
  public void SetUp(){
    nb = new Notebook();
    Main.passFiles(nb);
    sr = new SpecificReporter("#hey",true,nb);
  }
  @After
  public void TearDown(){
    sr = null;
    nb = null;
  }

  @Test
  /** #hey is present in test note files, so it should return true if they are printed successfully */
  public void testPrintSpecificMention(){
    //assertEquals(report.printSpecificMention("#hey"),true);
  }

  @Test
  /** this function tests getsentence() as well because it relies on it to return true */
  public void testGetFileForSentences(){
    assertEquals(sr.getFileForSentences(),true);
  }
  @Test
  /** with elements in the test array this should return true as it is not null */
  public void testGetSentenceLines(){
    ArrayList<String> testArray = new ArrayList<String>();
    testArray.add("growingBasil.txt");
    assertEquals(true,sr.getSentenceLines(testArray));
  }
  @Test
  /** should return true if the pattern is found in the test sentence */
  public void testGetSentence(){
    String testSentence = "this is a #hey test";
    assertNotEquals(null,sr.getSentence(testSentence,"#hey"));
  }
  @Test
  /** should return true as the notebook is not null (there must be elements that exist, even if no matches are found)*/
  public void testPrintSpecificMentionNoteNames(){
    assertEquals(true,sr.printSpecificMentionNoteNames());
  }
}
