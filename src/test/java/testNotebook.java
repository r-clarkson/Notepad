public testNotebook{
  File testFile;
  Notebook notebook;
  Note note;

  public void setUp(){
    File testFile = new File("src"  + File.separator  + "test"  + File.separator  + "java"  + File.separator  + "notes" + File.separator + "growingBasil.txt");
    notebook = new Notebook();
    note = new Note(testFile);
  }

  public void tearDown(){
    testFile = null;
    notebook = null;
    note = null;
  }

  public void testPassToMap(){

    assertEquals(notebook.passToMap(n,n.getIdentifierLists().get(0)),true);
    
  }
}
