import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AAAreader {
  public static void main(String[] args) {
    try {
      File path = new File("/Users/noellependergraft/Documents/Research/AAA/html");
      File[] files = path.listFiles();
      FileWriter output = new FileWriter("AAA.txt");
      
      for (File file:files) {
        Scanner scan = new Scanner(file);
        String line = scan.nextLine();
        
        while (!line.contains(",\"review\": [")) {
          if (scan.hasNextLine()) {
            line = scan.nextLine();  
          } else {
            break;
          }
        }
        
        while (scan.hasNextLine()) {
          line = scan.nextLine().trim();
          if (line.startsWith("\"datePublished\": \"")) {
            line = line.replace("\"datePublished\": \"","");
            //System.out.println(line.substring(0,line.lastIndexOf("\"")));
            output.write(line.substring(0,line.lastIndexOf("\"")) + "\t");
          } else if (line.startsWith("\"reviewBody\": \"")) {
            line = line.replace("\"reviewBody\": \"","");
            //System.out.println(line.substring(0,line.lastIndexOf("\"")));
            output.write(line.substring(0,line.lastIndexOf("\"")) + "\t");
          } else if (line.startsWith("\"author\":")) {
            line = scan.nextLine().trim();
            line = line.replace("\"@type\": \"","");
            //System.out.println(line.substring(0,line.lastIndexOf("\"")));
            output.write(line.substring(0,line.lastIndexOf("\"")) + "\t");
            line = scan.nextLine().trim();
            line = line.replace("\"name\":\"","");
            //System.out.println(line.substring(0,line.lastIndexOf("\"")));
            output.write(line.substring(0,line.lastIndexOf("\"")) + "\t");
          } else if (line.startsWith("\"ratingValue\": \"")) {
            line = line.replace("\"ratingValue\": \"","");
            //System.out.println(line.substring(0,line.lastIndexOf("\"")));
            output.write(line.substring(0,line.lastIndexOf("\"")) + "\n");
          }
        }
      }
    } catch (IOException e) {
    
    }
  }
}