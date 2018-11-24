public class Knight extends ChessPiece
{
    
    public Knight(char bw, int c, int r)
    {
        super (bw, c, r);
    }

    public boolean isAttacking (ChessPiece p, int m)
    {
	if (checkColor() != p.checkColor())//if the two pieces are different colors
	{
        	if (row - 2 > 0 && col - 1 > 0) //if attacking at above 2 and left 1
        	{
            		if (row - 2 == p.row && col - 1 == p.col)
            		{
            		    return true;
           		}
        	}
        
        	if (row - 2 > 0 && col + 1 <= m)//if attacking at above 2 and right 1
        	{
            		if (row - 2 == p.row && col + 1 == p.col)
            		{
                		return true;
            		}
        	}
        
        	if (row - 1 > 0 && col - 2 > 0 )
        	{
            		if (row - 1 == p.row && col - 2 == p.col)//if attacking above 1 and left 2
            		{
                		return true;
            		}
        	}
        
        	if (row - 1 > 0 && col + 2 <= m)
        	{
            		if (row - 1 == p.row && col + 2 == p.col)//if attacking above 1 and right 2
            		{
                		return true;
            		}
        	}
        
        	if (row + 2 <= m  && col - 1 > 0)
        	{
            		if (row + 2 == p.row && col - 1 == p.col)//if attacking below 2 and left 1
            		{
                	return true;
            		}
        	}
        
        	if (row + 2 <= m  && col + 1 <= m)
        	{
            		if (row + 2 == p.row && col + 1 == p.col)//if attacking below 2 and right 1
            		{
                		return true;
            		}
        	}
        
        	if (row + 1 <= m  && col - 2 > 0)
        	{
            		if (row + 1 == p.row && col - 2 == p.col)//if attacking below 1 and left 2
            		{
                		return true;
            		}
        	}
        
        	if (row + 1 <= m  && col + 2 <= m)
        	{
            		if (row + 1 == p.row && col + 2 == p.col)//if attacking below 1 and right 2
            		{
                		return true;
            		}
        	}
        }
        return false;
    }

   public boolean checkColor()//returns true if knight is white or false if black
   {
	if (color > 96) //if the knight is white
	{
		return true;
	}
	else
		return false; //the knight is black
   } 
}
