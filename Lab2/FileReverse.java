//------------------------------------------------------------------------------// FileReverse.java 
//Prints out tokens backwards
//------------------------------------------------------------------------------

import java.io.*;
import java.util.Scanner;

class FileReverse
{  
   public static void main(String[] args) throws IOException{

      int lineNumber = 0;

      // check number of command line arguments is at least 2
      if(args.length < 2){
         System.out.println("Usage: FileCopy <input file> <output file>");
         System.exit(1);
      }

      // open files
      Scanner in = new Scanner(new File(args[0]));
      PrintWriter out = new PrintWriter(new FileWriter(args[1]));
      
      // read lines from in, extract and print tokens from each line
      while( in.hasNextLine())
      {	      
         

         // trim leading and trailing spaces, then add one trailing space so 
         // split works on blank lines
         String line = in.nextLine().trim() + " "; 

         // split line around white space 
         String[] token = line.split("\\s+");
	 
         // print out tokens in reverse      
         int n = token.length;
	 for (int a = 0; a < n; a++)
	 {
		out.println(stringReverse(token[a]));
       
	 }
	 
    
      }
      in.close();
      out.close();
  }
  public static String stringReverse (String s)
  {
	String reverse = "";
	for (int i = s.length() - 1; i >= 0; i--)
	{
		char ch = s.charAt(i);
		reverse += s.valueOf(ch);
		
        }

	return reverse;
  }    

}
