FLAGS   = -std=c99 -Wall
SOURCES = Dictionary.c DictionaryClient.c
OBJECTS = Dictionary.o DictionaryClient.o
EXEBIN  = DictionaryClient
INFILE = input.txt
OUTFILE = output.txt

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)
check: 
	valgrind --leak-check=full $(EXEBIN) $(INFILE) $(OUTFILE)
clean: 
	rm -f $(EXEBIN) $(OBJECTS)
