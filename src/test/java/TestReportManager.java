package src.main.java;
import junit.framework.*;
import static org.junit.Assert.*;
import java.io.*;
import java.nio.file.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TestReportManager{
  Notebook notebook;
  ReportManager report;

  @Before
  public void setUp(){
    notebook = new Notebook();
    Main.passFiles(notebook);
    report = new ReportManager(notebook);
  }
  @After
  public void tearDown(){
    notebook = null;
  }

  @Test
  /** should return false as -c is not a command option */
  public void testGenerateReport(){
    assertEquals(report.generateReport("-c"),false);
  }
  @Test
  /** should return true as the parameters passed are valid arguments the user could pass */
  public void testPrintInformation(){
    assertEquals(report.printInformation("#","-l"),true);
  }
}
