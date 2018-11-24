public class Rook extends ChessPiece
{

    public Rook(char bw, int c, int r)
    {
        super (bw, c, r);
    }

    public boolean isAttacking (ChessPiece p, int m)
    {
	if (checkColor() != p.checkColor())//if the two pieces are different colors
	{
        	if (row == p.row || col == p.col)//if in same row or col, the rook is attacking another piece
        	{
        		return true;
        	}
	}
        return false;
    }

    public boolean checkColor()//returns true if rook is white or false if black
    {
	if (color > 96) //if the rook is white
	{
		return true;
	}
	else
		return false; //the rook is black
    } 
}
