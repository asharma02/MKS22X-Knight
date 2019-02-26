public class KnightBoard {

  private int[][]board;
  private int[] moves = new int[]{1,2,1,-2,-1,2,-1,-2,2,1,2,-1,-2,1,-2,-1};

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

private boolean addKnight(int row, int col, int level){
  if (row < 0 || col < 0 || row >= board.length || col >= board[row].length || board[row][col]!= 0) {
      return false; //check for all of these constrants
    }
  board[row][col] = level; //if works, set the tile to the number
  return true;
 }


private boolean removeKnight(int row, int col){
  if (row < 0 || col < 0 || row >= board.length || col >= board[row].length || board[row][col] == 0) {
      return false;
    }
  board[row][col] = 0;
  return true; //remove it from tile
 }


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
  return solveH(startingRow, startingCol, 1); //call helper
}


private boolean solveH(int row ,int col, int level){
  if (level == board.length * board[0].length + 1) {
    return true; //all tiles visited
  }
  if (addKnight(row, col, level)) {
    for (int i = 0; i < 16; i = i + 2) {
      if (solveH(row + moves[i], col + moves[i + 1],level + 1)){
          return true;
        }
    }
    removeKnight(row, col);
  }
  return false;
}


/**
@throws IllegalStateException when the board contains non-zero values.
@throws IllegalArgumentException when either parameter is negative
 or out of bounds. **/
public int countSolutions(int startingRow, int startingCol){
  for(int r = 0; r < board.length; r++) {
    for(int c = 0; c < board[0].length; c++) {
      if(board[r][c] != 0) {
        throw new IllegalStateException();
      }
    }
  } //check if empty
  if (startingRow < 0 || startingCol < 0 || startingRow > board.length || startingCol > board[0].length) {
    throw new IllegalArgumentException();
  } //out of bounds
    return countH(startingRow,startingCol, 1); //call helper
}


//HELPER FOR countH
public int countH(int row, int col, int level) {
  int count = 0;
  if (addKnight(row, col, level)) {
    if (level == board.length * board[0].length) {
      removeKnight(row, col);
      return 1;
    }
    else {
      for (int i = 0; i < 16; i = i + 2) {
        count += countH(row + moves[i], col + moves[i + 1], level + 1);
      }
    
    removeKnight(row, col);
  }
  return count;
}

}
