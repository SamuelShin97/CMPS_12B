/*
 * This program takes in two command line arguments giving the input and output
 * file names respectively, then classifies the characters on each line of 
 * the input file into the categories: alphabetic characters, numeric character,
 * punctuation, and white space.
 */
#define _GNU_SOURCE
#include<stdio.h>
#include<ctype.h>
#include<stdlib.h>
#include<string.h>

void extract_chars(char* s, char* a, char* d, char* p, char* w);
void count_chars(char*s, int* a, int* d, int* p, int* w);


int main(int argc, char* argv[]){
   FILE* in;  /* file handle for input */  
   FILE* out; /* file handle for output */
   char line[256]; /* char array to store words from input file */
   int alpha = 0;
   int digit = 0;
   int punct = 0;
   int white = 0;
   int* ptr1;
   int* ptr2;
   int* ptr3;
   int* ptr4;
   char bet[256];
   char num[256];
   char punctu[256];
   char space[256];
   char* ptrs;
   char* ptra;
   char* ptrd;
   char* ptrp;
   char* ptrw;
   int lineNum = 1;
   

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
  
   ptrs = line;
   ptra = bet;
   ptrd = num; 
   ptrp = punctu;
   ptrw = space;
   ptr1 = &alpha;
   ptr2 = &digit;
   ptr3 = &punct;
   ptr4 = &white;
   ptra = malloc(256*sizeof(char));
   ptrd = malloc(256*sizeof(char));
   ptrp = malloc(256*sizeof(char));
   ptrw = malloc(256*sizeof(char));
   

   
   while (fgets (line, sizeof(line), in)) {
	int size = strlen(line);
	*ptr1 = 0;
	*ptr2 = 0;
	*ptr3 = 0;
	*ptr4 = 0;
	for (int i = 0; i < size; i++)
	{
		if (isalpha((int)line[i]))
		{
		       	(*ptr1)++;
		}
		if (isdigit((int)line[i]))
		{
		       	(*ptr2)++;	
		}	 
		if (ispunct((int)line[i]))
	   	{
		       	(*ptr3)++;
		}
		if (isspace((int)line[i]))
		{
			(*ptr4)++;
		}

		
	}
	memset(bet, 0, 256);
	memset(num, 0, 256);
	memset(punctu, 0, 256);
	memset(space, 0, 256);
	extract_chars(line, bet, num, punctu, space);
       	fprintf(out, "%s %i %s", "line", lineNum, "contains: \n");
	lineNum++;
       	fprintf(out, "%i %s",*ptr1, "alphabetic character");
	if (*ptr1 == 1)
	{
		fprintf(out, "%s", ":");
	}
	else 
 	{
		fprintf(out, "%s", "s:");
	}
	for (int i = 0; i < size; i++)
	{
		fprintf(out, "%c", bet[i]);
	}
	fprintf(out, "\n");

       	fprintf(out, "%i %s",*ptr2, "numeric character");
	if (*ptr2 == 1)
	{
		fprintf(out, "%s", ":");
	}
	else 
 	{
		fprintf(out, "%s", "s:");
	}
	for (int i = 0; i < size; i++)
	{
		fprintf(out, "%c", num[i]);
	}
	fprintf(out, "\n");

       	fprintf(out, "%i %s",*ptr3, "punctuation character");
	if (*ptr3 == 1)
	{
		fprintf(out, "%s", ":");
	}
	else 
 	{
		fprintf(out, "%s", "s:");
	}
	for (int i = 0; i < size; i++)
	{
		fprintf(out, "%c", punctu[i]);
	}
	fprintf(out, "\n");

       	fprintf(out, "%i %s",*ptr4, "whitespace character");
	if (*ptr4 == 1)
	{
		fprintf(out, "%s", ":");
	}
	else 
 	{
		fprintf(out, "%s", "s:");
	}
	for (int i = 0; i < size; i++)
	{
		fprintf(out, "%c", space[i]);
	}
	fprintf(out, "\n");
	
	
	
	
	

  }
   /* close input and output files */
   fclose(in);
   fclose(out);
   free(ptrs);
   ptrs = NULL;
   free(ptra);
   ptra = NULL;
   free(ptrd);
   ptrd = NULL;
   free(ptrp);
   ptrp = NULL;
   free(ptrw);
   ptrw = NULL;
   return(EXIT_SUCCESS);
   
}
    
    
	   
   void extract_chars(char*str, char* alp, char* dig, char* pun, char* whi)
   {
	int size = strlen(str);
	for(int i = 0; i < size; i++)
	{
       	    	if (isalpha((int)str[i])){
                	alp[i] = str[i];
            	}
            	if (isdigit((int)str[i])){
                	dig[i] = str[i];
            	}
            	if (ispunct((int)str[i])){
                	pun[i] = str[i];
            	}
            	if (isspace((int)str[i])){
                	whi[i] = str[i];
            	}
		
        }
  }

