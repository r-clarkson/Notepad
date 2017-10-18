package classes;

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
  }
  @After
  public void TearDown(){
    na = null;
  }

  @Test
  /** would be hard to test in a unit test... should return false without user input */
  public void testAddDictatedNote(){
    assertEquals(false,na.addDictatedNote(new File("fakefile")));
  }
  @Test
  public void testAddTypedNote(){
    assertEquals(false,na.addTypedNote(new File("fakefile")));
  }
  @Test
  /** should return false without user input */
  public void testAppendToNote(){
    assertEquals(false,na.appendToNote(new File("fakefile")));
  }

}
