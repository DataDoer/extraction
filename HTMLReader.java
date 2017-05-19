import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class HTMLReader {
  
  public static void main(String[] args) {
    File path = new File("/Users/noellependergraft/Documents/Research/AAA/html");
    File[] files = path.listFiles();

    for (File file:files){
      try {
        FileWriter output = new FileWriter("AAA.csv");
        Scanner scan = new Scanner(file).useDelimiter("\\s+");
        String line = scan.nextLine();
        String reviewTime = ",\"review\": [";
        int reviewNumber = 198;
        
        while (!line.contains(reviewTime)) {
          if (scan.hasNextLine()) {
            line = scan.nextLine();  
          } else {
            break;
          }
        }
        
        while (scan.hasNextLine()) {  
          line = scan.nextLine();
          
          String type = "\"@type\": \"";
        
          if (line.contains(type)) {
            Scanner t = new Scanner(line).useDelimiter("\"@type\": \"|\",\\s*");
            t.next();
            output.write(t.next() + "\t");
            t.close();
            
            String line2 = scan.nextLine();
            Scanner d = new Scanner(line2).useDelimiter("\"datePublished\": \"|\",|-");
            d.next();
            output.write(d.next() + "\t");
         //   output.append(d.next() + "\t");
          //  output.append(d.next().substring(0,2) + "\t");
            d.close();
         
            String line3 = scan.nextLine();
            Scanner rb = new Scanner(line3).useDelimiter("\"reviewBody\": \"|\",");
            rb.next();
            output.write(rb.next() + "\t");
            scan.nextLine();
            rb.close();
         
            String line4 = scan.nextLine();
            Scanner at = new Scanner(line4).useDelimiter("\"@type\": \"|\",");
            at.next();
            output.write(at.next() + "\t");
            at.close();
         
            String line5 = scan.nextLine();
            Scanner author = new Scanner(line5).useDelimiter("\"name\":\"|\"| of |, ");
            author.next();
            output.write(author.next() + "\t");
            output.write(author.next() + "\t");
            output.write(author.next() + "\t");
            author.close();
         
            scan.nextLine();
            scan.nextLine();
            scan.nextLine();
            scan.nextLine();
         
            String line6 = scan.nextLine();
            Scanner rating = new Scanner(line6).useDelimiter("\"ratingValue\": \"|\",");
            rating.next();
            output.write(rating.next() + "\n");
            rating.close();
         
          }
        }
        output.close();
      } catch (IOException e) {
         System.out.println("File not found exception.");
      } 
    }
  }
}