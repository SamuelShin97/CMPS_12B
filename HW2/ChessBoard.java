/*
 *HW2, this program reads in a file called input.txt and 
 *reads in two lines per chessboard. The program then stores the 
 *chesspieces in a linked list and looks for a query spot on 
 *the board and prints out the chesspiece if there is one at the query.
 *The program also checks if any two pieces attack each other
 *prints out the two pieces. Before any of this happens however, the
 *program checks the board for validity.
 *
 * Written by Samuel Shin (sayshin@ucsc.edu) and Jessy Armstrong (jemarmst@ucsc.edu)
 */



import java.io.*;
import java.util.*;
public class ChessBoard
{
    public static Node head;
    static PrintWriter out;
    public ChessBoard()
    {
        head = null;
    }

    public static void insert(char bw, int c, int r)//inserts nodes with chesspiece objects with the
    //most recent one that was inserted at the head
    {
        if (bw == 'k' || bw == 'K')
        {
	    King king = new King(bw, c, r);
            Node latest = new Node(king);
            latest.next = head;
            head = latest;
        }
        if (bw == 'q' || bw == 'Q')
        {
            Queen queen = new Queen(bw, c, r);
	    Node latest = new Node(queen);
            latest.next = head;
            head = latest;
        }
        if (bw == 'b' || bw == 'B')
        {
            Bishop bishop = new Bishop(bw, c, r);
	    Node latest = new Node(bishop);
            latest.next = head;
            head = latest;
        }
        if (bw == 'n' || bw == 'N')
        {
            Knight knight = new Knight(bw, c, r);
	    Node latest = new Node(knight);
            latest.next = head;
            head = latest;
        }
        if (bw == 'r' || bw == 'R')
        {
            Rook rook = new Rook(bw, c, r);
	    Node latest = new Node(rook);
            latest.next = head;
            head = latest;
        }
    }

    public static ChessPiece find (int c, int r)//finds and returns a chesspiece at the given query spot
    {
        Node current = head;
        while (current != null)
        {
            if (current.piece.col == c && current.piece.row == r)
            {
                return current.piece;
            }
	    current = current.next;
        }
        if (current == null)
        {
            return null;
        }
	return null;
        
    }

    public static void checkAttack(int size)//checks and sees if a chesspiece is attacking another one in the linked list
    {
        Node current = head;
        Node current2;
	Outer:
        while (current != null)
        {
	    current2 = head;
            while (current2 != null)
            {
                if (current != current2 && current.piece.isAttacking(current2.piece, size) == true)//makes sure not comparing the same node itsself and checks if piece is attacking another
                {
                    out.print(current.piece.color + " " + current.piece.col + " " + current.piece.row + " ");
                    out.print(current2.piece.color + " " + current2.piece.col + " " + current2.piece.row);
		    out.flush();
                    break Outer;
                }
                current2 = current2.next;//increment to next node in the list
            }
            current = current.next;//increment to next node in list 
        }
        if (current == null)
        {
            out.print("-");
	    out.flush();
        }
    }

    public static boolean verify()//checks to make sure the board is indeed valid
    {
        Node current = head;
	Node current2;
        int whiteKing = 0;
        int blackKing = 0;
        int sameSpot = 0;
        while(current != null)
        {
            if (current.piece.color == 'k')//checks for white kings
            {
                whiteKing++;
            }
            if (current.piece.color == 'K')//checks for black kings
            {
                blackKing++;
            }
	    current2 = current.next;
            while (current2 != null)
            {
                if (current.piece.row == current2.piece.row && current.piece.col == current2.piece.col)//checks if two pieces occupy the same spot
                {
                    sameSpot++;
                }
                current2 = current2.next;
            }
            current = current.next;

        }
        if (whiteKing == 1 && blackKing == 1 && sameSpot == 0)//checks for correct conditions to be a valid board
        {
            return true;
        }
        return false;
    }

    public static void main (String[] args) throws FileNotFoundException
    {
	out = new PrintWriter("analysis.txt");
        File file = new File("input.txt");
        Scanner scan = new Scanner(file);//read in a file called input.txt
	int lineNumber = 0;
	int boardSize = 0;
        while (scan.hasNextLine())
        {
	    String line = scan.nextLine().trim();//gets one line to look at
	    String[] token = line.split("\\s+");//put the line into an array without spaces
	    				
	    
            if (lineNumber % 2 == 0)//if an even line or a line that contains the board size and chesspieces
            {
                boardSize = Integer.parseInt(token[0]);//get the boardsize
		
                for (int i = 1; i < token.length; i += 3)
                {
                    	char type = token[i].charAt(0);//get what kind of chesspiece it is/color
                    	int col = Integer.parseInt(token[i + 1]);//gets the coordinates of where the piece is on the board
                    	int row = Integer.parseInt(token[i + 2]);
                    	insert(type, col, row);//insert the piece in the linked list
                }
		
                if (verify() == false)//checks board to see if it is invalid
                {
                    out.println("Invalid");
		    out.flush();
		    head = null;
		    scan.nextLine();
		    
                }
		
                else
                {
		    lineNumber++;//if board is valid increment lineNumber to move onto else statement below
                }
		
            }

            else //if an odd line or a line that contains the query
            {
                int checkCol = Integer.parseInt(token[0]);//reads in the query
               	int checkRow = Integer.parseInt(token[1]);
                ChessPiece query = find(checkCol, checkRow);//finds if there is a piece at query
		if (query == null)
		{
			out.print("-" + " ");
			out.flush();
		}
		else
		{
                	out.print(query.color + " ");
			out.flush();
		}
		checkAttack(boardSize);
		out.println();
		out.flush();
		lineNumber++;
                head = null;
            }
            
        }

	out.close();
    }
}
