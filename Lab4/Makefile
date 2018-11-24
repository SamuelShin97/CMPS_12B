
FLAGS   = -std=c99 -Wall
SOURCES = charType.c
OBJECTS = charType.o
EXEBIN  = charType
OUTFILE = out 

all: $(EXEBIN)

$(EXEBIN) : $(OBJECTS)
	gcc -o $(EXEBIN) $(OBJECTS)

$(OBJECTS) : $(SOURCES)
	gcc -c $(FLAGS) $(SOURCES)

check :
	valgrind --leak-check=full $(EXEBIN) $(SOURCES) $(OUTFILE)

clean :
	rm -f $(EXEBIN) $(OBJECTS)

