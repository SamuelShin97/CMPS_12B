//-----------------------------------------------------------------------------
// Dictionary.h
// Header file for the Dictionary ADT
//-----------------------------------------------------------------------------

#ifndef Lab5_Dictionary_H_INCLUDE_
#define Lab5_Dictionary_H_INCLUDE_

#include<stdio.h>

// Dictionary
// Exported reference type
typedef struct DictionaryObj* Dictionary;

// Node
// Exported reference type 
typedef struct NodeObj* Node;

//constructor for node
Node newNode(int x);
	
// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void);

// freeDictionary()
// destructor for the Dictionary type
void freeDictionary(Dictionary* pD);

//-----------------------------------------------------------------------------
// prototypes of ADT operations deleted to save space, see webpage
//-----------------------------------------------------------------------------

void addNode(Node N, Dictionary D);
	
void printList(Dictionary D);


// printDictionary()
// prints a text representation of D to the file pointed to by out
// pre: none
void printDictionary(FILE* out, Dictionary D);

// insert()
// insert number into Dictionary
// pre: none
void insert(int number, Dictionary D);

// find()
// find pointer to node containing number (read next code snippet for details), return //      null if none exists
// pre: none
Node find(int number, Dictionary D);

// delete()
// delete number from Dictionary
// pre: none
void delete(int n, Dictionary D);

#endif
