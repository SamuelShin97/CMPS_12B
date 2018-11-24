/*
 *Creates a Queen object that checks if it can attack other queens
 */

public class Queen extends ChessPiece
{
    public Queen (int r, int c) //instantiates row and col from chesspiece
    {
        super(r, c);

    }

    public boolean checkDiagonal (ChessPiece p, int n)
    {
        int sentinel = 1;
        int rdiag = row;
        int cdiag = col;

        while (sentinel == 1)//checks if queen is attacking another queen in upper right diag
        {

            if (rdiag > n || cdiag > n)//makes sure it checks in bounds of the board
            {
                sentinel = -1;
                continue;
            }

            if (rdiag == p.row && cdiag == p.col)
            {
                return true;
            }
            rdiag++;
            cdiag++;

        }

        sentinel = 1;
        rdiag = row;
        cdiag = col;

        while (sentinel == 1)//checks if queen is attacking another queen in bottom left diag
        {

            if (rdiag < 0 || cdiag < 0)
            {
                sentinel = -1;
                continue;
            }

            if (rdiag == p.row && cdiag == p.col)
            {
                return true;
            }
            rdiag--;
            cdiag--;

        }

        sentinel = 1;
        rdiag = row;
        cdiag = col;

        while (sentinel == 1)//checks if queen is attacking another queen in upper left diag
        {

            if (rdiag > n || cdiag < 0)
            {
                sentinel = -1;
                continue;
            }

            if (rdiag == p.row && cdiag == p.col)
            {
                return true;
            }
            rdiag++;
            cdiag--;

        }

        sentinel = 1;
        rdiag = row;
        cdiag = col;

        while (sentinel == 1)//checks if queen is attacking another queen in bottom right diag
        {

            if (rdiag < 0 || cdiag > n)
            {
                sentinel = -1;
                continue;
            }

            if (rdiag == p.row && cdiag == p.col)
            {
                return true;
            }
            rdiag--;
            cdiag++;

        }

        return false;
    }

    public boolean isAttacking (ChessPiece p, int m)
    {
        if (row == p.row || col == p.col)//if in same row or col, the queen is attacking another
        {
            return true;
        }

        if (checkDiagonal(p, m) == true)//if in any diagonal of queen, queen is attacking another
        {
            return true;
        }

        return false;

    }
}
