import java.io.File;

public class Note{

  String name;

  public Note(File note){

    name = note.getName();

  }

  public String getName(){
    return name;
  }
}
