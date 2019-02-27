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

private boolean inbounds(int row, int col) {
  if (row < 0 || col < 0 || row >= board.length || col >= board[row].length || board[row][col]!= 0) {
      return false; //check for all of these constrants
    }
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
  return optsolveH(startingRow, startingCol, 1); //call helper
}


private boolean solveH(int row ,int col, int level){
  if (level == board.length * board[0].length + 1) {
    return true; //check if all tiles visited
  }
  if (addKnight(row, col, level)) { //check if can add kngiht at this spot
    for (int i = 0; i < moves.length; i = i + 2) { //loop through moves
      if (solveH(row + moves[i], col + moves[i + 1],level + 1)){
          return true; //go through the moves and see if it works
        }
    }
    removeKnight(row, col); //if it doesnt work, remove the previous knight
  }
  return false; //if call doesnt work, return false
}

private boolean optsolveH(int row ,int col, int level) {
  if (level == board.length * board[0].length + 1) {
    return true; //check if all tiles visited
  }
  for (int i = 0; i < moves.length; i = i + 2) {
    int[] current = nextmove(row, col);
    board[current[0]][current[1]] = level;
    if (optsolveH(current[0], current[1], level+1)) {
      return true;
    }
  }
  return false;
}

private int possmoves(int r, int c) {
  int count = 0;
  for (int i = 0; i < moves.length; i = i + 2) {
    if (inbounds(r + moves[i], c + moves[i + 1])) {
      count ++;
    }
  }
  return count;
}

private int[] nextmove(int r, int c) {
  int[] whichone = new int[2];
  int lowest = board.length * board[0].length;
  for (int i = 0; i < moves.length; i = i + 2) { //loop through moves
    if (inbounds(r + moves[i], c + moves[i + 1])) {
    int temp = possmoves(r + moves[i], c + moves[i + 1]);
    if (temp < lowest) {
      lowest = temp;
      whichone[0] = r + moves[i];
      whichone[1] = c + moves[i + 1];
    }
    }
  }
  return whichone;
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
  int count = 0; //count var
  if (addKnight(row, col, level)) { //can you add knight here?
    if (level == board.length * board[0].length) { //if been to all tiles
      removeKnight(row, col); //if this is the last tile, return 1 as it is 1 sol
      return 1;
    }
    else { //if not last tile
      for (int i = 0; i < moves.length; i = i + 2) { //loop through the moves
        count += countH(row + moves[i], col + moves[i + 1], level + 1); //go though all the directions
      }
    }
    removeKnight(row, col); //if doesnt work, rmeove the last knight
  }
  return count; //return count, no need to return false
}

}
