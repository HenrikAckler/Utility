import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

//TODO: Document
//TODO: Convert to static usage
//TODO: error propogation
public class CSVParser{
   private File inputFile;
   private LinkedList<String> lines;

   public static void main(String[] args) {
      try {
         CSVParser myCsvParser = new CSVParser("test.csv");
         myCsvParser.readFile();
         myCsvParser.mergeLines();

         

      } catch (Exception e) {
         System.out.println("Broke. "+ e.getStackTrace());
      }
      
   }
   
   public CSVParser(String filePath) throws FileNotFoundException{
      inputFile = new File(filePath);
   }

   /**
    * Reads each line of file into a linked list.
    * @throws FileNotFoundException 
    * 
    */
   private void readFile() throws FileNotFoundException{
      Scanner lineScanner = new Scanner(inputFile);
      lines = new LinkedList<>();

      while(lineScanner.hasNextLine()){
         lines.add(lineScanner.nextLine());
      }

      lineScanner.close();

      
   }

   /**
    * Takes the output from readFile, and merges the n+1 line into the n line if the n line has odd quantity of quotes
    */
   private void mergeLines(){
      String add1;
      String add2;

      ListIterator<String> lineIterator = lines.listIterator(lines.size());

      while(lineIterator.hasPrevious()){
         add2 = lineIterator.previous();
         if(countQuotes(add2) % 2 != 0){
            add1 = lineIterator.previous();
            lineIterator.next();
            lineIterator.set(add1 + add2);
            lineIterator.next();
            lineIterator.remove();
         }
      }

      for (String line : lines) {
            System.out.println(line);
      }

   
   }

   /**
    * Needs to split lines into cells. Not sure how I want to do this yet. 
    * TODO: figure this out
    */
   private void splitLines(){
   
   }

   private int countQuotes(String line){
      int count = 0;
      for(char c : line.toCharArray()){
         count = c == '"' ? count+1 : count;
      }
      return count;
   }
}