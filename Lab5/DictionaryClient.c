//-------------------------------------------------------------------------------------------------
//DictionaryClient.c 
//contains the main for function that uses Dictionary methods to read in a file and either 
//inserts, finds, deletes, or prints number values in an output file
//--------------------------------------------------------------------------------------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

int main(int argc, char* argv[]){
   FILE* in;  /* file handle for input */  
   FILE* out; /* file handle for output */
   char line[256]; /* char array to store words from input file */
   char command;
   char *ptrCommand = &command;
   int value;
   int* ptrValue = &value;
   Dictionary dict = newDictionary();
   Dictionary* ptrDict = &dict; 
   /* check command line for correct number of arguments */
   if( argc != 3 ){
      printf("Usage: %s <input file> <output file>\n", argv[0]);
      exit(EXIT_FAILURE);
   }

   /* open input file for reading */
   in = fopen(argv[1], "r");
   if( in==NULL ){
      printf("Unable to read from file %s\n", argv[1]);
      exit(EXIT_FAILURE);
   }

   /* open output file for writing */
   out = fopen(argv[2], "w");
   if( out==NULL ){
      printf("Unable to write to file %s\n", argv[2]);
      exit(EXIT_FAILURE);
   }
   
  /* read words from input file, print on separate lines to output file*/
   while(fgets(line, sizeof(line), in)){
      sscanf(line, "%c %d", ptrCommand, ptrValue);
      if (*ptrCommand == 'i')
      {
	 insert(value, dict);
	 fprintf(out, "%s %d", "inserted", value);
	 fprintf(out, "\n");
      }
      else if (*ptrCommand == 'p')
      {
         printDictionary(out, dict);
      }
      else if (*ptrCommand == 'f')
      {
	 if (find(value, dict) == NULL)
         {
	    fprintf(out, "%d %s", value, "not present");
	    fprintf(out, "\n");
	 }
         else 
         {
	    fprintf(out, "%d %s", value, "present");
            fprintf(out, "\n");
         }
      }
      else if (*ptrCommand == 'd')
      {
	 delete(value, dict);
	 fprintf(out, "%s %d", "deleted", value);
	 fprintf(out, "\n");
      }
   }

   /* close input and output files */
   fclose(in);
   fclose(out);
   freeDictionary(ptrDict);
   return(EXIT_SUCCESS);
 
}

