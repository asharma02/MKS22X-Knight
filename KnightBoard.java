public class KnightBoard {
  private int[][]board;

  /**@throws IllegalArgumentException when either parameter is negative. **/

public KnightBoard(int startingRows,int startingCols) {
  board = new int[startingRows][startingCols];
  for (int r = 0 ; r < startingRows ; r++) {
    for (int c = 0 ; c < startingCols; c++) {
      board[r][c] = 0 ;
    }                                               //INITIALIZES BOARD WITH ALL 0S
  }
}


public String toString() {
  String output = "" ;
  for (int r = 0 ; r < board.length ; r++) {
    for (int c = 0 ; c < board[0].length ; c++) {
      if (board[r][c] == 0) {
        output += "_ " ;
      }
      else {
        output += "k " ;
      }
    }
    output += "\n" ;
  }
  return output;
}

/**blank boards display 0's as underscores
you get a blank board if you never called solve or
when there is no solution**/

/**
@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative
 or out of bounds. **/

public boolean solve(int startingRow, int startingCol){}

/**
@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative
 or out of bounds. **/
public int countSolutions(int startingRow, int startingCol){}

//Suggestion:
private boolean solveH(int row ,int col, int level){}
// level is the # of the knight

}
