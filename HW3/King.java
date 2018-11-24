public class King extends ChessPiece
{
    public King(char bw, int c, int r)
    {
        super(bw, c, r);
    }
    public boolean isAttacking (ChessPiece p, int m)
    {
	if (checkColor() != p.checkColor())//if the two pieces are different colors
	{
        	if (Math.abs(row - p.row) == 1 && Math.abs(col - p.col) == 0)//if a piece is adjacent to the king up/down
      	  	{
            		return true;
        	}
        	else if (Math.abs(row - p.row) == 0 && Math.abs(col - p.col) == 1)//if a piece is adjacent to the king left/right
		{
			return true;
		}
	 
        	else if (Math.abs(row - p.row) == 1 && Math.abs(col - p.col) == 1)//if a piece is in any diagonal of king 
        	//that is one space away
        	{
          	  	return true;
        	}
        	else
       	 	{
        	    return false;
        	}
	}
	return false;
    }
    public boolean checkColor()//returns true if king is white or false if black
    {
	if (color > 96) //if the king is white
	{
		return true;
	}
	else
		return false; //the king is black
    }  
}
