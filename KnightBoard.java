public class KnightBoard {

  private int[][]board;
  private int[][] moves = new int[][] { {-2,-1},{-2,1}, {-1,2}, {1,2}, {2,1}, {2,-1}, {1,-2}, {-1,-2}};

  /**@throws IllegalArgumentException when either parameter is negative. **/

public KnightBoard(int startingRows,int startingCols) {
  if (startingRows < 0 || startingCols < 0) {
    throw new IllegalArgumentException();
  }
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
        output += board[r][c] + " " ;
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

public boolean solve(int startingRow, int startingCol){
  for(int r = 0; r < board.length; r++) {
    for(int c = 0; c < board[0].length; c++) {
      if(board[r][c] != 0) {
        throw new IllegalStateException();
      }
    }
  }
  //CHECK IF THE BOARD CONTAINS NONZERO VALUES
  if (startingRow < 0 || startingCol < 0 || startingRow > board.length || startingCol > board[0].length) {
    throw new IllegalArgumentException();
  }
  //CHECK IF EITHER PARAMETER IS NEGATIVE OR OUT OF bounds
  return solveH(startingRow, startingCol, 1);
}

/**
@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative
 or out of bounds. **/
public int countSolutions(int startingRow, int startingCol){
  return 0;
}

//Suggestion:
private boolean solveH(int row ,int col, int level){
  if (level == board.length * board[0].length + 1) {
    return true;
  }
  //if all squares visited, print solution
    if (row < board.length && row >= 0 && col < board[row].length && col >= 0 && board[row][col] == 0){
      board[row][col] = level;
      if(solveH(row + 1 ,col + 2, level + 1) || solveH(row + 1, col - 2, level + 1) ||
          solveH(row - 1 ,col + 2, level + 1) || solveH(row - 1, col - 2, level + 1) ||
          solveH(row + 2 ,col + 1, level + 1) || solveH(row + 2, col - 1, level + 1) ||
          solveH(row - 2 ,col + 1, level + 1) || solveH(row - 2, col - 1, level + 1)){
          return true;
      }
      else {
        board[row][col] = 0;
        return false;
    }
  }
    return false;
  }
    //choose one of the 8 moves and recursively check if this leads to the solution
    // if move doesnt work, try an alternative move
    //if no paths work, return false, will cause to remove the previously added item in recursion
      //if false is returned by the inital call, the method will return false
}
