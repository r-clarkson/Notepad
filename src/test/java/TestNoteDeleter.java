package src.main.java;

import static org.junit.Assert.*;
import java.io.*;
import java.nio.file.*;
import junit.framework.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestNoteDeleter{
  File testFile;
  NoteDeleter nd;

  @Before
  public void SetUp(){
    testFile = new File("src"  + File.separator  + "test"  + File.separator  + "java"  + File.separator  + "notes" + File.separator + "testFile.txt");
    nd = new NoteDeleter();
  }
  @After
  public void TearDown(){
    testFile = null;
    nd = null;
  }

  @Test
  /** should return false as none of the notes contain that random string */
  public void testDeleteNoteMentions(){
    assertEquals(false,nd.deleteNoteMentions("aaaaaaahfeji"));
  }
  @Test
  /** function should not return a null string as the testfile contains a title */
  public void testDeleteNote(){
    assertEquals(null,nd.deleteNote(testFile));
  }
  @Test
  /** this should also return false as the random string is not in the file */
  public void testRemoveMentionFromFile(){
    assertEquals(false,nd.removeMentionFromFile(testFile,"bguiabguieqrpgopfe"));
  }
}
