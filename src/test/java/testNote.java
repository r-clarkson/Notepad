package notepad.src.main.java.code;
import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.io.*;

public class testNote{

  protected Note n;

  public void setUp(){
    n = new Note(new File("." + File.separator + "notes" + File.separator + "growingBasil.txt"));
  }

  public void tearDown(){
    n = null;
  }

  public void testName(){
    assertEquals("growingBasil.txt",n.getName());
  }


}
