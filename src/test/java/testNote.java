package notepad.src.main.java.code;

import static org.junit.Assert.assertEquals;

import java.io.*;
import java.util.*;

public class testNote {

  protected Note n;
  /** creates test variables/objects */
  public void setUp() {
    n =  new Note(testFile);
    File testFile = new File("src"  + File.separator  + "test"  + File.separator  + "java"  + File.separator  + "notes" + File.separator + "growingBasil.txt"));
    Pattern testPattern = Pattern.compile("#[-a-zA-Z0-9_]+");
  }

  public void tearDown() {
    n = null;
    testFile = null;
    testPattern = null;
  }

  public void testName() {
    assertEquals("growingBasil.txt",n.getName());
  }
  /** tests that the first character of a LL item is a # after parsing note for #s */
  public void testParseNote(){

    LinkedList<String> testList = n.parseNote(testFile,testPattern);
    assertEquals(testList.get(0).charAt(0),'#');

  }

  public void testGetIdentifierLists(){
    assertEquals(n.getIdentifierLists().size(),1);
  }

}
