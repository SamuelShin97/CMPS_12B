public class Rook extends ChessPiece
{

    public Rook(char bw, int c, int r)
    {
        super (bw, c, r);
    }

    public boolean isAttacking (ChessPiece p, int m)
    {
        if (row == p.row || col == p.col)//if in same row or col, the rook is attacking another piece
        {
            return true;
        }
        return false;
    }
}
