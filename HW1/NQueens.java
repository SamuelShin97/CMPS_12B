/*
 * This program reads in three ints, the dimension of the chess board,
 * the column number of the first queen, and the row number of the first queen. 
 * This program solves the NQueens problem where n amount of queens are placed on a
 * chess board and no two queens can attack each other.
 * Samuel Shin, sayshin@ucsc.edu 
 * Partner: Jessy Armstrong, jemarmst@ucsc.edu
 */

import java.util.*;
import java.io.*;
import java.nio.*;

public class NQueens
{

    public static boolean[][] board;
    public static Queen[] positions;

    public static void main (String[] args) throws FileNotFoundException
    {
	int n = Integer.parseInt(args[0]);
	int x = Integer.parseInt(args[1]) - 1; //takes in row and col of first queen and converts from standard (x,y) form from input to coding form
	int y = Integer.parseInt(args[2]) - 1;
	
	board = new boolean[n][n];
	positions = new Queen[n];
        Queen q1 = new Queen (x,y);
        positions[0] = q1;
        board[x][y] = true;
	boolean b = placeQueen(n,1);
	makeTextSolutions(b);
	

    }

    public static boolean placeable(Queen p, Queen[] q, int dim, int queens)//checks if can place Queen
    {
        int key = 0;
        
        for (int i = 0; i < q.length; i++)
        {
            if (q[i] != null)//if array is not empty
            {
                if (board[p.row][p.col] == false && p.isAttacking(q[i], dim) == false)//checks and sees if the space 
                //is occupied by another queen and if the space is not being attacked by another queen
                {
                    if (queens == i + 1 && key == 0)//makes sure it checks all queens in array and
                    //makes sure it doesn't just go off of the last queen in array
                    {
                        return true;
                    }
                }
                else
                {
                    key++;//somewhere in the array the queen was attacking a queen in the array
                }
            }
        }

        return false;
    }

    public static boolean placeQueen(int n, int count)
    {
        if (count == n)//base case
        {
            return true;
        }

        for (int i = 0; i < board.length; i++)//goes through the rows of the board
        {
            Queen nthQ = new Queen(i, count);
            if (placeable(nthQ, positions, n, count) == true && spaceLeft(n, count) >= n - count)//checks if the 
            //spot on the board is placeable and there enough spaces open for amount of queens left to place
            {
                positions[count] = nthQ; //add queen to array of queens on the board
                board[i][count] = true; //sets this spot to a "queen occupied" spot
                if (placeQueen(n, count + 1) == true)//recursive call
                {
                    return true;
                }
                board[i][count] = false; //if it fails then try again in a different spot
            }
        }
        return false; //there is no solution if program reaches this point
    }

    public static int spaceLeft(int a, int queensLeft)//checks if there are enough spaces for queens left to place
    {
        int openSpaces = 0;
        for (int i = 0; i < board.length; i++)
        {
            for (int j = 0; j < board.length; j++)
            {
                Queen q = new Queen(i, j);
                if (placeable(q, positions, a, queensLeft) == true)
                {
                    openSpaces++;//counts how many open spaces there are on the board
                }
            }
        }

        return openSpaces;
    }

    public static void makeTextSolutions(boolean b){
	String s = "";
	if(b==true){
		for(int i = 0; i < board.length;i++){
			s += ((positions[i].row +1) + "" + (positions[i].col+1) + " ");
		}
	}
	else
		s = "no solution";

	String fileName = "solutions.txt";
	try{
		PrintWriter outputStream = new PrintWriter(fileName);
		outputStream.println(s);
		outputStream.flush();
		outputStream.close();
		outputStream.println();
	}
	catch(FileNotFoundException e){
		e.printStackTrace();
	}



}
}
