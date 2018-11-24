/*
 *HW3, this program reads in a file called input.txt and 
 *reads in two lines per chessboard. The program then stores the 
 *chesspieces in a linked list and looks for a query spot on 
 *the board and prints out the chesspiece if there is one at the query.
 *The program also checks if any two pieces attack each other
 *prints out the two pieces. Before any of this happens however, the
 *program checks the board for validity.
 *
 * Written by Samuel Shin (sayshin@ucsc.edu)
 */



import java.io.*;
import java.util.*;
public class ChessMoves
{
    public static Node head;
    static PrintWriter out;
    public ChessMoves()
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

    public static void delete(ChessPiece p)//deletes a certain chesspiece from the linked list
    {
	    Node prev = null;
	    Node current = head; 
	    if (p == current.piece)//if the piece to be deleted is at the head
	    {
		    head = head.next;
	    }
	    while (current != null && current.piece != p)//deletes if the piece is in the middle or end of list
	    {
		    prev = current;
		    current = current.next;
		    if (current.piece == p)
		    {
			    prev.next = current.next;
		    }
	    }

	    

    }
    
    public static ChessPiece wdetermineCheck(int size)//checks and sees if a white king is in check
    {
        Node current = head;
        Node current2;
        while (current != null)
        {
	    current2 = head;
            while (current2 != null)
            {
                if (current != current2 && current.piece.isAttacking(current2.piece, size) == true)//makes sure not comparing the same pieces and checks if a piece is attacking another
                {
			if (current2.piece.color == 'k')//makes sure the piece being attacked is a king
			{
				if (Math.abs(current.piece.col - current2.piece.col) == 0 && Math.abs(current.piece.row - current2.piece.row) == 1)//if the pieces are adjacent left/right
				{
					return current2.piece;//no need to check block
				}
			        else if (Math.abs(current.piece.col - current2.piece.col) == 1 && Math.abs(current.piece.row - current2.piece.row) == 0)//if the pieces are adjacent up/down
				{
					return current2.piece;//no need to check block
				}
				else if (Math.abs(current.piece.col - current2.piece.col) == 1 && Math.abs(current.piece.row - current2.piece.row) == 1)//if the pieces are in diagonal to each other by 1 space
				{
					return current2.piece;//no need to check block
				}				
				else if (checkBlock(current.piece, current2.piece.col, current2.piece.row) == true)//check block otherwise
				{
					return current2.piece;
				}
			}
                }
                current2 = current2.next;//increment to next node in the list
            }
            current = current.next;//increment to next node in list 
        }
	if (current == null)
	{
		return null;
	}
	return null;	
    }

    public static ChessPiece bdetermineCheck(int size)//checks and sees if a black king is in check and is the same exact code as the wdetermineCheck
    {
        Node current = head;
        Node current2;
        while (current != null)
        {
	    current2 = head;
            while (current2 != null)
            {
                if (current != current2 && current.piece.isAttacking(current2.piece, size) == true)
                {
			if (current2.piece.color == 'K')
			{
				if (Math.abs(current.piece.col - current2.piece.col) == 0 && Math.abs(current.piece.row - current2.piece.row) == 1)
				{
					return current2.piece;
				}
			        else if (Math.abs(current.piece.col - current2.piece.col) == 1 && Math.abs(current.piece.row - current2.piece.row) == 0)
				{
					return current2.piece;
				}
				else if (Math.abs(current.piece.col - current2.piece.col) == 1 && Math.abs(current.piece.row - current2.piece.row) == 1)
				{
					return current2.piece;
				}				
				else if (checkBlock(current.piece, current2.piece.col, current2.piece.row) == true)
				{
					return current2.piece;
				}
			}
                }
                current2 = current2.next;//increment to next node in the list
            }
            current = current.next;//increment to next node in list 
        }
	if (current == null)
	{
		return null;
	}
	return null;
    }

    public static boolean checkmate(ChessPiece p, int size)//determines if a king is in checkmate
    {
	    int c = p.col;
	    int r = p.row;
	    Node current = head;
	    ArrayList<ChessPiece> kingSpaces = new ArrayList<ChessPiece>();//stores the possible spaces where the king could move next as phony chess pieces
	    
	    if (r + 1 <= size)//if space above the king is in bounds
            {
		    if (find(c, r + 1) == null)//if the space is empty
		    {
			if (p.color == 'k')//if the king is white
			{
		    		ChessPiece phony1 = new ChessPiece('p', c, r + 1);//make a phony piece with respective lower case letter
		    		kingSpaces.add(phony1);
			}
			else 
			{
				ChessPiece phony1 = new ChessPiece('P', c, r + 1);
				kingSpaces.add(phony1);
			}
		    }
		    else if ((find(c, r + 1).checkColor() == true && p.checkColor() == false) || (find(c, r + 1).checkColor() == false && p.checkColor() == true))//if there's a piece in the space above the Kingand its of opposite color of the king, make a phony piece in that space since the king could capture that piece 
		    {
			if (p.color == 'k')
			{
				ChessPiece phony1 = new ChessPiece('p', c, r + 1);
				kingSpaces.add(phony1);
			}
			else 
			{
				ChessPiece phony1 = new ChessPiece('P', c, r + 1);
				kingSpaces.add(phony1);
			}
		    }
	    }//this same logic is applied for the other 7 spaces adjacent to the king
	    if (r - 1 >= 1)
            {
		    if (find(c, r - 1) == null)
		    {
			if (p.color == 'k')
			{
		    		ChessPiece phony2 = new ChessPiece('p', c, r - 1);
		    		kingSpaces.add(phony2);
			}
			else 
			{
				ChessPiece phony2 = new ChessPiece('P', c, r - 1);
				kingSpaces.add(phony2);
			}
		    }
		    else if ((find(c, r - 1).checkColor() == true && p.checkColor() == false) || (find(c, r - 1).checkColor() == false && p.checkColor() == true))
		    {
			if (p.color == 'k')
			{
				ChessPiece phony2 = new ChessPiece('p', c, r - 1);
				kingSpaces.add(phony2);
			}
			else 
			{
				ChessPiece phony2 = new ChessPiece('P', c, r - 1);
				kingSpaces.add(phony2);
			}
		    }		    	    
		
	    }
	    if (c + 1 <= size)
            {
		    if (find(c + 1, r) == null)
		    {
			if (p.color == 'k')
			{
		    		ChessPiece phony3 = new ChessPiece('p', c + 1, r);
		    		kingSpaces.add(phony3);
			}
			else 
			{
				ChessPiece phony3 = new ChessPiece('P', c + 1, r);
				kingSpaces.add(phony3);
			}
		    }
		    else if ((find(c + 1, r).checkColor() == true && p.checkColor() == false) || (find(c + 1, r).checkColor() == false && p.checkColor() == true))
		    {
			if (p.color == 'k')
			{
				ChessPiece phony3 = new ChessPiece('p', c + 1, r);
				kingSpaces.add(phony3);
			}
			else 
			{
				ChessPiece phony3 = new ChessPiece('P', c + 1, r);
				kingSpaces.add(phony3);
			}
		    }
		    
	    }
	    if (c - 1 >= 1)
            {
		    if (find(c - 1, r) == null)
		    {
			if (p.color == 'k')
			{
		    		ChessPiece phony4 = new ChessPiece('p', c - 1, r);
		    		kingSpaces.add(phony4);
			}
			else 
			{
				ChessPiece phony4 = new ChessPiece('P', c - 1, r);
				kingSpaces.add(phony4);
			}
		    }
		    else if ((find(c - 1, r).checkColor() == true && p.checkColor() == false) || (find(c - 1, r).checkColor() == false && p.checkColor() == true))
		    {
			if (p.color == 'k')
			{
				ChessPiece phony4 = new ChessPiece('p', c - 1, r);
				kingSpaces.add(phony4);
			}
			else 
			{
				ChessPiece phony4 = new ChessPiece('P', c - 1, r);
				kingSpaces.add(phony4);
			}
		    }
	    }
	    if (c + 1 <= size && r + 1 <= size)
            {
		    if (find(c + 1, r + 1) == null)
		    {
			if (p.color == 'k')
			{
		    		ChessPiece phony5 = new ChessPiece('p', c + 1, r + 1);
		    		kingSpaces.add(phony5);
			}
			else 
			{
				ChessPiece phony5 = new ChessPiece('P', c + 1, r + 1);
				kingSpaces.add(phony5);
			}
		    }
		    else if ((find(c + 1, r + 1).checkColor() == true && p.checkColor() == false) || (find(c + 1, r + 1).checkColor() == false && p.checkColor() == true))
		    {
			if (p.color == 'k')
			{
				ChessPiece phony5 = new ChessPiece('p', c + 1, r + 1);
				kingSpaces.add(phony5);
			}
			else 
			{
				ChessPiece phony5 = new ChessPiece('P', c + 1, r + 1);
				kingSpaces.add(phony5);
			}
		    }
	    }
	    if (c - 1 >= 1 && r - 1 >= 1)
            {
		    if (find(c - 1, r - 1) == null)
		    {
			if (p.color == 'k')
			{
		    		ChessPiece phony6 = new ChessPiece('p', c - 1, r - 1);
		    		kingSpaces.add(phony6);
			}
			else 
			{
				ChessPiece phony6 = new ChessPiece('P', c - 1, r - 1);
				kingSpaces.add(phony6);
			}
		    }
		    else if ((find(c - 1, r - 1).checkColor() == true && p.checkColor() == false) || (find(c - 1, r - 1).checkColor() == false && p.checkColor() == true))
		    {
			if (p.color == 'k')
			{
				ChessPiece phony6 = new ChessPiece('p', c - 1, r - 1);
				kingSpaces.add(phony6);
			}
			else 
			{
				ChessPiece phony6 = new ChessPiece('P', c - 1, r - 1);
				kingSpaces.add(phony6);
			}
		    }
		    
	    }
	    if (c - 1 >= 1 && r + 1 <= size)
            {
		    if (find(c - 1, r + 1) == null)
		    {
			if (p.color == 'k')
			{
		    		ChessPiece phony7 = new ChessPiece('p', c - 1, r + 1);
		    		kingSpaces.add(phony7);
			}
			else 
			{
				ChessPiece phony7 = new ChessPiece('P', c - 1, r + 1);
				kingSpaces.add(phony7);
			}
		    }
		    else if ((find(c - 1, r + 1).checkColor() == true && p.checkColor() == false) || (find(c - 1, r + 1).checkColor() == false && p.checkColor() == true))
		    {
			if (p.color == 'k')
			{
				ChessPiece phony7 = new ChessPiece('p', c - 1, r + 1);
				kingSpaces.add(phony7);
			}
			else 
			{
				ChessPiece phony7 = new ChessPiece('P', c - 1, r + 1);
				kingSpaces.add(phony7);
			}
		    }
		    
	    }
	    if (c + 1 <= size && r - 1 >= 1)
            {
		    if (find(c + 1, r - 1) == null)
		    {
			if (p.color == 'k')
			{
		    		ChessPiece phony8 = new ChessPiece('p', c + 1, r - 1);
		    		kingSpaces.add(phony8);
			}
			else 
			{
				ChessPiece phony8 = new ChessPiece('P', c + 1, r - 1);
				kingSpaces.add(phony8);
			}
		    }
		    else if ((find(c + 1, r - 1).checkColor() == true && p.checkColor() == false) || (find(c + 1, r - 1).checkColor() == false && p.checkColor() == true))
		    {
			if (p.color == 'k')
			{
				ChessPiece phony8 = new ChessPiece('p', c + 1, r - 1);
				kingSpaces.add(phony8);
			}
			else 
			{
				ChessPiece phony8 = new ChessPiece('P', c + 1, r - 1);
				kingSpaces.add(phony8);
			}
		    }
		    
	    }
	    Iterator<ChessPiece> itr = kingSpaces.iterator();
	  
	    while (itr.hasNext())	    
	    {
		ChessPiece fake = itr.next();//gets next phony piece to look at
		current = head;
	   	while (current != null)//compares all pieces in the linked list to the one phony piece to see if at least one piece in the linked list attacks the phony
		{
			if (current.piece.col != p.col || current.piece.row != p.row)
			{
				if (current.piece.isAttacking(fake, size) == true)
			   	{
			       		if (Math.abs(current.piece.col - fake.col) == 0 && Math.abs(current.piece.row - fake.row) == 1)
			       		{
				  		itr.remove();
						break;
			       		}
			       		else if (Math.abs(current.piece.col - fake.col) == 1 && Math.abs(current.piece.row - fake.row) == 0)
			      		{
				  		itr.remove();
						break;
			       		}
			       		else if (Math.abs(current.piece.col - fake.col) == 1 && Math.abs(current.piece.row - fake.row) == 1)
			       		{
			           		itr.remove();
						break;
			      		}
			      		else if(checkBlock(current.piece, fake.col, fake.row) == true) 
			       		{
				   		itr.remove();
						break;
			       		}
			   	}
			}
			
		current = current.next;
		}
		
	    }   
	  
	 if (kingSpaces.isEmpty() == true)//if king runs out of spaces to move to
	 {
		return true;//king is checkmated
	 }
	 else
	 {
	 	return false;
	 }
	 
    }

    public static boolean checkBlock(ChessPiece p, int c, int r)//checks the path way of a piece's move to make sure nothing is blocking its path. (Does not apply to kings and knights) Returns true if the path is open and false if something is blocking
    {
	if (p.color == 'q' || p.color == 'Q')//if the piece is a queen
        {
		if (p.col == c || p.row == r || Math.abs(c - p.col) == Math.abs(r - p.row))//makes sure the queen can move to the space it wants to move to
		{

			if (p.col == c)
			{
				if (r - p.row > 0)//if the space is above the queen
				{
					for (int i = p.row + 1; i < r; i++)//check all spaces in between the queen and said space
					{
						if (find(c, i) != null)//if the target space is not empty
						{
							return false; 
						}
					}
				}
				else
				{
					for (int i = p.row - 1; i > r; i--)//if the space is below the queen
					{
						if (find(c, i) != null)
						{
							return false; 
						}
					}
				}
			}
			if (p.row == r)
			{
				if (c - p.col > 0)//if the space is to the right of the queen
				{
					for (int i = p.col + 1; i < c; i++)
					{
						if (find(i, r) != null)
						{
							return false;
 						}
					}
				}
				else 
				{
					for (int i = p.col - 1; i > c; i--)//if the space is to the left of the queen
					{
						if (find(i, r) != null)
						{
							return false;
						}
					}
				}
			}
			if (Math.abs(c - p.col) == Math.abs(r - p.row))//if the space is in any diagonal of the queen
			{
				if (c - p.col > 0 && r - p.row > 0)//if the space is in the upper right diagonal
				{
					int cdiag = p.col;
					int rdiag = p.row;					
					for (int i = 0; i < c - p.col - 1; i++)//check all spaces in between the queen and said space
					{
						cdiag++;
						rdiag++;
						if (find(cdiag, rdiag) != null)
						{
							return false;
						}
					
					}
				}
				if (c - p.col < 0 && r - p.row < 0)//if the space is in the lower left diagonal
				{
					int cdiag = p.col;
					int rdiag = p.row;					
					for (int i = 0; i < p.col - c - 1; i++)
					{
						cdiag--;
						rdiag--;
						if (find(cdiag, rdiag) != null)
						{
							return false;
						}
					}
				}
				if (c - p.col < 0 && r - p.row > 0)//if the space is in the upper left diagonal
				{
					int cdiag = p.col;
					int rdiag = p.row;
					for (int i = 0; i < p.col - c - 1; i++)
					{
						cdiag--;
						rdiag++;
						if (find(cdiag, rdiag) != null)
						{
							return false;
						}
					}
				}
				if (c - p.col > 0 && r - p.row < 0)//if the space is in the lower right
				{
					int cdiag = p.col;
					int rdiag = p.row;
					for (int i = 0; i < c - p.col - 1; i++)
					{
						cdiag++;
						rdiag--;
						if (find(cdiag, rdiag) != null)
						{
							return false;
						}
					}
				}
			}
		}
		else //if code reaches here then the spot is invalid for a queen to move to ie: q 1 1 going to 2 3		
		{
			return false;
		}
	}
	if (p.color == 'r' || p.color == 'R')//if the piece is a rook
	{
		if (p.col == c || p.row == r)//makes sure the rook is able to move to the spot it wants to move to
		{
			if (p.col == c)//uses same logic as queen for checking if space is up, down, left, or right
			{
				if (r - p.row > 0)
				{
					for (int i = p.row + 1; i < r; i++)
					{
						if (find(c, i) != null)
						{
							return false; 
						}
					}
				}
				else
				{
					for (int i = p.row - 1; i > r; i--)
					{
						if (find(c, i) != null)
						{
							return false; 
						}
					}
				}
			}
			if (p.row == r)
			{
				if (c - p.col > 0)
				{
					for (int i = p.col + 1; i < c; i++)
					{
						if (find(i, r) != null)
						{
							return false;
 						}
					}
				}
				else 
				{
					for (int i = p.col - 1; i > c; i--)
					{
						if (find(i, r) != null)
						{
							return false;
						}
					}
				}
			}
		}
		else //if code reaches here then the spot is invalid for a rook to move to ie: r 1 1 going to 2 2
		{
			return false;
		}
		
	}
	if (p.color == 'b' || p.color == 'B')//if the piece is a bishop
	{
		if (Math.abs(c - p.col) == Math.abs(r - p.row))//makes sure the bishop can move to the spot it wants to
		{
			if (c - p.col > 0 && r - p.row > 0)//uses same logic as queen for checking if space is in upper right, lower left, lower right, or upper left diag
			{
				int cdiag = p.col;
				int rdiag = p.row;
				for (int i = 0; i < c - p.col - 1; i++)
				{
					cdiag++;
					rdiag++;
					if (find(cdiag, rdiag) != null)
					{
						return false;
					}
					
				}
			}
			if (c - p.col < 0 && r - p.row < 0)
			{
				int cdiag = p.col;
				int rdiag = p.row;				
				for (int i = 0; i < p.col - c - 1; i++)
				{
					cdiag--;
					rdiag--;
					if (find(cdiag, rdiag) != null)
					{
						return false;
					}
				}
			}
			if (c - p.col < 0 && r - p.row > 0)
			{
				int cdiag = p.col;
				int rdiag = p.row;
				for (int i = 0; i < p.col - c - 1; i++)
				{
					cdiag--;
					rdiag++;
					if (find(cdiag, rdiag) != null)
					{
						return false;
					}
				}
			}
			if (c - p.col > 0 && r - p.row < 0)
			{
				int cdiag = p.col;
				int rdiag = p.row;
				for (int i = 0; i < c - p.col - 1; i++)
				{
					cdiag++;
					rdiag--;
					if (find(cdiag, rdiag) != null)
					{
						return false;
					}
				}
			}
		}
		else //if code reaches here then the spot is invalid for a bishop to move to ie: b 1 1 going to 1 2
		{
			return false;
		}	
	}
	if (p.color == 'n' || p.color == 'N')//if piece is a knight
	{
		if ((Math.abs(c - p.col) == 1 && Math.abs(r - p.row) == 2) || (Math.abs(c - p.col) == 2 && Math.abs(r - p.row) == 1))//simply checks to make sure that the knight can move to target space
		{
			return true;
		}
		else //if code reaches here then the spot is invalid for a knight to move to ie: n 1 1 going to 2 2		
		{
			return false;
		}
	}
	if (p.color == 'k' || p.color == 'K')//if piece is a king
	{
		if (Math.abs(c - p.col) == 0 && Math.abs(r - p.row) == 1)//the if and else if statements simply check if the king can move to target spot
		{
			return true;
		}
		else if (Math.abs(c - p.col) == 1 && Math.abs(r - p.row) == 0)
		{
			return true;
		}
		else if (Math.abs(c - p.col) == 1 && Math.abs(r - p.row) == 1)
		{
			return true;
		}
		else //if code reaches here then the spot is invalid for a king to move to ie: k 1 1 going to 1 3		
		{
			return false;
		}
	}
	return true;
    }




    public static void main (String[] args) throws FileNotFoundException
    {
        out = new PrintWriter("analysis.txt");
        File file = new File("input.txt");
        Scanner scan = new Scanner(file);//read in a file called input.txt
        int lineNumber = 0;
        int boardSize = 0;
	int numofCheckmatedKings = 0;
	String output = "";
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
                lineNumber++;

            }

            else 
            {	
		if (wdetermineCheck(boardSize) != null)//if white is in check
		{
			ChessPiece wcheckedKing = wdetermineCheck(boardSize);
			if (checkmate(wcheckedKing, boardSize) == true)//check if white is in checkmate
			{
				output = "White checkmated";
				numofCheckmatedKings++;
			}
			else 
			{
				if (numofCheckmatedKings == 0)
				{
					output = "White in check ";
					out.flush();
				}
			}
		}
		else if (bdetermineCheck(boardSize) != null)//if black is in check
		{
			ChessPiece bcheckedKing = bdetermineCheck(boardSize);
			if (checkmate(bcheckedKing, boardSize) == true)//check if black is in checkmate
			{
				output = "Black checkmated";
				numofCheckmatedKings++;
			}
			else 
			{
				if (numofCheckmatedKings == 0)
				{
					output = "Black in check";
				}
			}
		}
		else
		{
			output = "All kings safe";
		}
		
                for (int i = 0; i < token.length; i += 4)
                {
                    int initCol = Integer.parseInt(token[i]);
                    int initRow = Integer.parseInt(token[i + 1]);//coordinates for the piece to move
                    int colPrime = Integer.parseInt(token[i + 2]);
                    int rowPrime = Integer.parseInt(token[i + 3]);//coordinates for the space for piece to move to
                    ChessPiece pieceToMove = find(initCol, initRow);

                    if (find(colPrime, rowPrime) == null)//if the target space is empty
                    {
                        if (checkBlock(pieceToMove, colPrime, rowPrime) == true)//check path way is clear
                        {
                            out.print("Valid ");
                            out.flush();
                            pieceToMove.col = colPrime;
                            pieceToMove.row = rowPrime;//update the position of the piece to the coordinates it moved to
                        }
                        else
                        {
                            out.print("Invalid ");
                            out.flush();
                            break;
                        }
                    }
                    else //if there's another piece in the target spot
                    {
                        ChessPiece target = find(colPrime, rowPrime);//get the piece at target spot
                        if (pieceToMove.isAttacking(target, boardSize) == true)//first check if pieceToMove can attack the target
                        {
                            if (Math.abs(target.col - pieceToMove.col) == 0 && Math.abs(target.row - pieceToMove.row) == 1)//if target is directly to the left or right of pieceToMove, no need tocheck pathway for blockage
                            {
                                out.print("Valid ");
                                out.flush();
                                pieceToMove.col = colPrime;
                                pieceToMove.row = rowPrime;
                                delete(target);
                            }
                            else if (Math.abs(target.col - pieceToMove.col) == 1 && Math.abs(target.row - pieceToMove.row) == 0)//if target is directly above or below of pieceToMove, no need to check pathway for blockage
                            {
                                out.print("Valid ");
                                out.flush();
                                pieceToMove.col = colPrime;
                                pieceToMove.row = rowPrime;
                                delete(target);
                            }
                            else if (Math.abs(target.col - pieceToMove.col) == 1 && Math.abs(target.row - pieceToMove.row) == 1)//if target is in a diag exactly one space away, no need to check pathway for blockage
                            {
                                out.print("Valid ");
                                out.flush();
                                pieceToMove.col = colPrime;
                                pieceToMove.row = rowPrime;
                                delete(target);
                            }
                            else if(checkBlock(pieceToMove, colPrime, rowPrime) == true)//otherwise check for blockage on the pathway to target piece
                            {
                                out.print("Valid ");
                                out.flush();
                                pieceToMove.col = colPrime;
                                pieceToMove.row = rowPrime;
                                delete(target);
                            }
                            else 
                            {
                                out.print("Invalid ");
                                out.flush();
                                break;

                            }

                        }
                        else if (pieceToMove.checkColor() == target.checkColor())//handles case if piece was able to "attack"/simply move to target piece but couldn't attack due to color similarities
                        {
                            out.print("Invalid ");
                            out.flush();
                            break;

                        }
                    }
                }
                out.println();
                out.flush();

		out.print(output);
		out.flush();
		
                out.println(); 
		out.flush(); 
		
		numofCheckmatedKings = 0;
                lineNumber++;
                head = null;
		output = "";
	    }
	}
        out.close();
    }
}
