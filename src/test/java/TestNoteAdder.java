package src.main.java;

import static org.junit.Assert.assertEquals;
import java.io.*;
import java.nio.file.*;
import junit.framework.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
//need to remove fakefile stuff
public class TestNoteAdder{
  NoteAdder na;
  File testFile;

  @Before
  public void SetUp(){
    na = new NoteAdder();
    testFile = new File("." + File.separator + "src"  + File.separator  + "test"  + File.separator  + "java"  + File.separator  + "notes" + File.separator + "testFile.txt");
  }
  @After
  public void TearDown(){
    na = null;
  }

  @Test
  /** would be hard to test in a unit test... should return false without user input */
  public void testAddDictatedNote(){
    assertEquals(false,na.addDictatedNote(testFile));
  }
  @Test
  public void testAddTypedNote(){
    assertEquals(false,na.addTypedNote(testFile));
  }
  @Test
  /** should return false without user input */
  public void testAppendToNote(){
    assertEquals(false,na.appendToNote(testFile));
  }

}
