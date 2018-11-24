public class ChessPiece
{
        public int col;
        public int row;
        public char color;
        public ChessPiece (char bw, int c, int r)
        {
                color = bw;
                col = c;
                row = r;
        }

        public boolean isAttacking (ChessPiece p, int m)//method to be overidden
        {
                return false;
        }
	
	public boolean checkColor()//method to be overidden
	{
		return false;
	}
}





