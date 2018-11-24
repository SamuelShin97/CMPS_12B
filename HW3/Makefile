JAVASRC    = ChessMoves.java Node.java ChessPiece.java King.java Queen.java Bishop.java Rook.java Knight.java
SOURCES    = README Makefile $(JAVASRC)
MAINCLASS  = ChessMoves
CLASSES    = ChessMoves.class Node.class ChessPiece.class King.class Queen.class Bishop.class Rook.class Knight.class
JARFILE    = Chessmoves.jar

all: $(JARFILE)

$(JARFILE): $(CLASSES)
	echo Main-class: $(MAINCLASS) > Manifest
	jar cvfm $(JARFILE) Manifest $(CLASSES)
	rm Manifest

$(CLASSES): $(JAVASRC)
	javac -Xlint $(JAVASRC)

clean:
	rm $(CLASSES) $(JARFILE)



