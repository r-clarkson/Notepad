package notepad.src.main.java.code;
import junit.framework.*;
import static org.junit.Assert.assertEquals;

import java.io.*;
import java.nio.file.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class testNotebook{
  File testFile;
  Notebook notebook;
  Note note;
  @Before
  public void setUp(){
    File testFile = new File("src"  + File.separator  + "test"  + File.separator  + "java"  + File.separator  + "notes" + File.separator + "growingBasil.txt");
    notebook = new Notebook();
    note = new Note(testFile);
  }
  @After  
  public void tearDown(){
    testFile = null;
    notebook = null;
    note = null;
  }
  @Test
  public void testPassToMap(){

    assertEquals(notebook.passToMap(note,note.getIdentifierLists().get(0)),true);
    
  }
}
