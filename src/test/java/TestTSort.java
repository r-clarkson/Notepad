package src.main.java;

import static org.junit.Assert.assertEquals;
import java.io.*;
import java.nio.file.*;
import junit.framework.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestTSort{
  TSort ts;
  Notebook nb;

  @Before
  public void SetUp(){
    nb = new Notebook();
    Main.passFiles(nb);
    ts = new TSort(nb);
  }
  @After
  public void TearDown(){
    nb = null;
    ts = null;
  }

  @Test
  /** should return true if sort completes
  * this also in a way tests findIncomingVertices and deleteOutgoingVertices
  */
  public void testTSort(){
    assertEquals(true,ts.tSort());
  }
  @Test
  /** function should return false when run alone */
  public void testFindIncomingVertices(){
    assertEquals(false,ts.findIncomingVertices());
  }
  @Test
  /** function should return false when run alone */
  public void testDeleteOutgoingVertices(){
    assertEquals(false,ts.deleteOutgoingVertices());
  }
}
