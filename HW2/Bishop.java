public class Bishop extends ChessPiece
{

    Bishop(char bw, int c, int r)
    {
        super(bw, c, r);
    }

    public boolean checkDiagonal (ChessPiece p, int n)
    {
        int sentinel = 1;
        int rdiag = row;
        int cdiag = col;

        while (sentinel == 1)//checks if bishop is attacking another piece in bottom right diag
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

        while (sentinel == 1)//checks if bishop is attacking another piece in upper left diag
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

        while (sentinel == 1)//checks if bishop is attacking another piece in bottom left diag
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

        while (sentinel == 1)//checks if bishop is attacking another piece in upper right diag
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

        if (checkDiagonal(p, m) == true)//if in any diagonal of bishop, bishop is attacking another
        {
            return true;
        }

        return false;

    }
}
