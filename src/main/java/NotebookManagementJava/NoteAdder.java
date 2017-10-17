package classes;

import java.util.*;
import java.util.logging.Logger;
import java.io.*;
import java.nio.file.*;
import edu.cmu.sphinx.api.Configuration;
import edu.cmu.sphinx.api.LiveSpeechRecognizer;
import edu.cmu.sphinx.api.SpeechResult;

public class NoteAdder{
  Scanner scan;

  public NoteAdder(){
    scan = null;
  }

  /**
  * Lets the user add what they type to the program - am now realizing this is basically the same as append to note...
  *
  **/

  public void addTypedNote( File filename){
    String body=null;
    try{
      PrintWriter writer = new PrintWriter(filename);
      System.out.println("Enter the body of your note. When you are finished please type EOF: \n ");
      do {
        body = scan.next();
        writer.write(body);
      }while(!(scan.next().equals("EOF")));
      writer.close();
      System.out.println("File written properly!");
    } catch (IOException e) {
      System.out.println("File did not write properly");
    }
  }

  /**
  * Adds text or new identifiers to an already existing file
  * Uses Java's File class to write to the text file
  **/

  public void appendToNote(File filename){
    System.out.println("Please type what you would like to add to " + filename.getName() + ". Skip a line and press enter to submit changes");
    String text = scan.nextLine();
    while (!text.equals("")){
      try{
        Files.write(Paths.get(filename.getPath()), text.getBytes(), StandardOpenOption.APPEND);
      }
      catch (IOException e){
        System.out.println("Error.");
      }
      text = scan.nextLine();
    }
    System.out.println(filename.getName() + " edited.");
  }

  /**
  * Using Sphinx4 API, record from user's microphone and then add recording to a text file
  * See inside for more details
  */

  public void addDictatedNote(File filename){

    /** set up a configuration for english, and set recording variable to true */
    Configuration configuration = new Configuration();
    boolean record = true;
    configuration.setAcousticModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us");
    configuration.setDictionaryPath("resource:/edu/cmu/sphinx/models/en-us/cmudict-en-us.dict");
    configuration.setLanguageModelPath("resource:/edu/cmu/sphinx/models/en-us/en-us.lm.bin");

    try {

      /** clear the screen and give user instructions */
      System.out.print("\033[H\033[2J");
      System.out.flush();
      System.out.println("Dependencies loaded. Dictate your note and then press enter to stop");

      /* somehwat messy fix to keep log messages from sphinx4 appearing in the console https://stackoverflow.com/questions/35560969/disable-console-mess-in-cmusphinx4 */
      Logger cmRootLogger = Logger.getLogger("default.config");
      cmRootLogger.setLevel(java.util.logging.Level.OFF);
      String conFile = System.getProperty("java.util.logging.config.file");
      if (conFile == null) {
        System.setProperty("java.util.logging.config.file", "ignoreAllSphinx4LoggingOutput");
      }

      ArrayList<SpeechResult> results = new ArrayList<SpeechResult>();

      /** initialize recognizer which uses the microphone to start speech recognition */
      LiveSpeechRecognizer recognizer = new LiveSpeechRecognizer(configuration);
      recognizer.startRecognition(true);

      /** result from recognizer is taken and the hypothesis of the word is written to the file */
      SpeechResult result = recognizer.getResult();
      results.add(result);

      /** recording is stopped when user presses enter */
      scan = new Scanner(System.in);
      if (scan.nextLine()==""){
        recognizer.stopRecognition();
      }
      /** predicted results are written to file */
      for (int i = 0; i < results.size(); i++){
        Files.write(Paths.get(filename.getPath()), results.get(i).getHypothesis().getBytes(), StandardOpenOption.APPEND);
      }
    }
    catch (IOException e){
      System.out.println("Error.");
    }
  }
}
