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
        //this string & its loop are used to skip through all the junk at the beginning until the scanner reaches the review section
        String reviewTime = ",\"review\": [";
        //the total number of reviews that exist in/should be parsed from the files
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
            //type is an element in the html, will generally be "Review" but could potentially be "Response"
            //html line looks like "@type": "Review",
            //t stands for type in the scanner name
            Scanner t = new Scanner(line).useDelimiter("\"@type\": \"|\",\\s*");
            t.next();
            output.write(t.next() + "\t");
            t.close();
            
            String line2 = scan.nextLine();
            //this should extract the year, month, and date from HTML format "datePublished": "2017-04-13 21:56:51+00:00",
            //d stands for date
            Scanner d = new Scanner(line2).useDelimiter("\"datePublished\": \"|\",|-");
            d.next();
            output.write(d.next() + "\t");
            output.append(d.next() + "\t");
            output.append(d.next().substring(0,2) + "\t");
            d.close();
         
            String line3 = scan.nextLine();
            //this extracts the review body
            //rb stands for review body
            Scanner rb = new Scanner(line3).useDelimiter("\"reviewBody\": \"|\",");
            rb.next();
            output.write(rb.next() + "\t");
            rb.close();
            
            //this skips line "author": {
            //author containes an @type and a name
            scan.nextLine();
         
            String line4 = scan.nextLine();
            //at stands for author type: generally extracts Person
            Scanner at = new Scanner(line4).useDelimiter("\"@type\": \"|\",");
            at.next();
            output.write(at.next() + "\t");
            at.close();
         
            String line5 = scan.nextLine();
            //name is an html element formatted as Cristin of Vallejo, CA
            //called author because it contains both author name and author location information
            Scanner author = new Scanner(line5).useDelimiter("\"name\":\"|\"| of |, ");
            author.next();
            output.write(author.next() + "\t");
            output.write(author.next() + "\t");
            output.write(author.next() + "\t");
            author.close();
         
            //skips unnecessary information
            scan.nextLine();
            scan.nextLine();
            scan.nextLine();
            scan.nextLine();
         
            String line6 = scan.nextLine();
            //extracts the 1-star to 5-star rating given to the company by the review author
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
