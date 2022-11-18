// The following Java program prints the names of the text files in the path
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
public class movingExtension {
   public static void main(String args[]) throws IOException {
      //Creating a File object for directory
      String Source = "D:\\COURSES\\BTECH\\PROJECT\\FileSegregation\\GitIgnore\\Unsegregated";
      File directoryPath = new File(Source);
      FilenameFilter textFilefilter = new FilenameFilter(){
         public boolean accept(File dir, String name) {
            String lowercaseName = name.toLowerCase();
            if (lowercaseName.endsWith(".docx")) {
               return true;
            } else {
               return false;
            }
         }
      };
      //List of all the text files
      String filesList[] = directoryPath.list(textFilefilter);
      System.out.println(filesList.length);
      // System.out.println("List of the text files in the specified directory:");
      
      for(String fileName : filesList) {
        Source = "D:\\COURSES\\BTECH\\PROJECT\\FileSegregation\\GitIgnore\\Unsegregated\\" + fileName;
        String des = "D:\\COURSES\\BTECH\\PROJECT\\FileSegregation\\GitIgnore\\SegregatedPDF\\" + fileName;
        System.out.println(des);
        File file1 = new File(Source);
        File file2 = new File(des);
        boolean bool1 = Source.contains(".docx");
        System.out.println(bool1);
        boolean bool = file1.renameTo(file2);
        System.out.println(bool);
	    if(bool1) {
            if(bool){
            System.out.println("Succesfully");
        }
	}
	        else if (bool){
		System.out.println("Not Succesfully");
} 
       /*  boolean bool = file1.renameTo(file2);
        System.out.println(bool);
        if(bool){
            System.out.println("Succesfully");
        } */
         System.out.println(fileName);
      }
   }
}