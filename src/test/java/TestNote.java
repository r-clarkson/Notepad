package src.main.java;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import java.util.regex.*;
import junit.framework.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestNote {

  protected Note n;
  protected File testFile;
  protected Pattern testPattern;
  /** creates test variables/objects */
  @Before
  public void setUp() {
    testFile = new File("src"  + File.separator  + "test"  + File.separator  + "java"  + File.separator  + "notes" + File.separator + "growingBasil.txt");
    testPattern = Pattern.compile("#[-a-zA-Z0-9_]+");
    n =  new Note(testFile);
  }
  @After
  public void tearDown() {
    n = null;
    testFile = null;
    testPattern = null;
  }
  @Test
  public void testName() {
    assertEquals("growingBasil.txt",n.getName());
  }
  /** tests that the first character of a LL item is a # after parsing note for #s */
  @Test
  public void testParseNote(){
    LinkedList<String> testList = n.parseNote(testFile,testPattern);
    assertNotEquals(testList.get(0),null);
    assertEquals(testList.get(0).charAt(0),'#');
  }
  @Test
  public void testGetIdentifierLists(){
    assertNotEquals(n.getIdentifierLists().get(0),null);
    assertEquals(n.getIdentifierLists().size(),5);
  }

}
