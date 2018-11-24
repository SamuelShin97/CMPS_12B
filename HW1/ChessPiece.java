
public class ChessPiece
{
	int row;
	int col;

	public ChessPiece (int r, int c)
	{
		row = r;
		col = c;
	}

	public boolean isAttacking (ChessPiece p)
	{
		return false;
	}
}
