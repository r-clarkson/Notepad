package classes;

import junit.framework.*;
import static org.junit.Assert.*;
import java.io.*;
import java.nio.file.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestNotebookManager{
  NotebookManager nm;
  Notebook nb;

  @Before
  public void SetUp(){
    nb = new Notebook();
    Main.passFiles(nb);
    nm = new NotebookManager();
  }
  @After
  public void TearDown(){
    nb = null;
    nm = null;
  }

  @Test
  /** should return true because -t is a valid option */
  public void testEditNote(){
    assertEquals(true,nm.editNote("-t"));
  }
  @Test
  /** should return false because file will return as null without user input */
  public void testGetNoteFile(){
    assertEquals(null,nm.getNoteFile());
  }
  @Test
  /** should return false because string will return as null without user input */
  public void testGetNoteMention(){
    assertEquals(null,nm.getNoteMention());
  }
  @Test
  /** should return true if directory exists */
  public void testListFiles(){
    assertEquals(true,nm.listFiles(new File("src"  + File.separator  + "test"  + File.separator  + "java"  + File.separator  + "notes" + File.separator)));
  }
}
