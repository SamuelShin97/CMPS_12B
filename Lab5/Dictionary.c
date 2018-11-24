//-----------------------------------------------------------------------------
//   Dictionary.c
//   Implementation file for Dictionary ADT
//-----------------------------------------------------------------------------
#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// private types --------------------------------------------------------------

// NodeObj
typedef struct NodeObj{
   int item;
   struct NodeObj* next;
} NodeObj;

// Node
//see .h file for defn 

// newNode()
// constructor of the Node type
Node newNode(int x) {
   Node N = malloc(sizeof(NodeObj));
   assert(N!=NULL);
   N->item = x;
   N->next = NULL;
   return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node *pN){
   if( pN!=NULL && *pN!=NULL){
      free(*pN);
      *pN = NULL;
   }
}

// DictionaryObj
typedef struct DictionaryObj{
   Node top;
   int numItems;
} DictionaryObj;

// public functions -----------------------------------------------------------

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
   Dictionary D = malloc(sizeof(DictionaryObj));
   assert(D!=NULL);
   D->top = NULL;
   D->numItems = 0;
   return D;
}

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
   if( pD!=NULL && *pD!=NULL)
   {
	  Node N = (*pD)->top; 
	  Node temp;
      while(N != NULL) 
	  {
		temp = N->next;
	  	freeNode(&N);
		N = temp; 
	  }
	  freeNode(&N);
	  free(*pD);
      *pD = NULL;
   }
}

//-----------------------------------------------------------------------------
// definitions of ADT operations (only giving printLinkedList, you need to do the rest)
//-----------------------------------------------------------------------------

Node find(int number, Dictionary D)
{
	Node N;
	for(N=D->top; N!=NULL; N=N->next) 
	{
		if(N->item == number)
		{
			return N;
		}
	}
	return NULL;
}

void addNode(Node N, Dictionary D)
{
	Node currentNode;
	if(D->top == NULL)
	{
		D->top = N;
	}
	else
	{
		currentNode = D->top; 
		while(currentNode->next != NULL)
		{
			currentNode = currentNode->next; 
		}
		currentNode->next = N; 
	}
	D->numItems++;
}

void insert(int num, Dictionary D)
{
	Node N = newNode(num);
	Node currentNode;
	if(D->top == NULL)
	{
		D->top = N;
	}
	else
	{
		currentNode = D->top; 
		while(currentNode->next != NULL)
		{
			currentNode = currentNode->next; 
		}
		currentNode->next = N; 
	}
	D->numItems++;
}

void printList(Dictionary D)
{
	Node temp = D->top;
	
	while(temp != NULL)
	{
		printf("%d,", temp->item);
		temp = temp->next;
	}
	printf("\n");
}

void delete(int n, Dictionary D)
{
	Node N;
	Node prev;
	for(N=D->top; N!=NULL; N=N->next) 
	{
		if(N->item == n)
		{
			if(N == D->top)
			{
				D->top = N->next;
			}
			else
			{
				prev->next = N->next;
			}
			freeNode(&N);
			break;
		}
		prev = N;
	}
}

// printDictionary()
// prints a text representation of S to the file pointed to by out
// pre: none
void printDictionary(FILE* out, Dictionary D){
   Node N;
   if( D==NULL ){
      fprintf(stderr, 
              "Dictionary Error: calling printDictionary() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   for(N=D->top; N!=NULL; N=N->next)
   {
	   if(N->next != NULL)
	   {
	   		fprintf(out, "%d -> ", N->item);
	   }
	   else
	   {
	   		fprintf(out, "%d", N->item);
	   }
   }
   fprintf(out, "\n");
}
