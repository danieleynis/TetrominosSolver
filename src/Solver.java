/*
Author: Daniel Eynis
E-mail: eynis@pdx.edu
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Solver {
    private int rows;
    private int cols;
    //https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html
    private ArrayList<Character> pieces = new ArrayList<>(); //Stores a list of the characters representing the pieces
    //allows piece characters to map to their respective class to allow retrieval of orientations
    private HashMap<Character, Piece> pieceHashMap = new HashMap<>();
    private char[][] board; //board is stored in 2D char array
    //pcid helps to keep track on what to label the piece that is currently being placed (a, b, c,...)
    private char pcid = 'a';

    Solver(){
        Scanner input = new Scanner(System.in);
        rows = input.nextInt();
        cols = input.nextInt();
        String pcs = input.next();

        //http://stackoverflow.com/questions/15331637/convert-string-to-arraylist-character-in-java
        for(char piece : pcs.toCharArray())
            pieces.add(piece);

        board = new char[rows][cols];

        //https://www.tutorialspoint.com/java/java_hashmap_class.htm
        pieceHashMap.put('I', new PieceI());
        pieceHashMap.put('5', new Piece5());
        pieceHashMap.put('2', new Piece2());
        pieceHashMap.put('T', new PieceT());
        pieceHashMap.put('L', new PieceL());
        pieceHashMap.put('O', new Piece0());
        pieceHashMap.put('P', new PieceP());
    }

    public void solveBoard(){
        if(!solveBoard(copyBoard(board), pieces))  //wrapper calls actual recursive function
            System.out.println('?'); //print '?' if no solution found
    }

    private boolean solveBoard(char[][] board, ArrayList<Character> pieces){
        int[] coord = getUpperLeftCoord(board); //get the upper left coordinate to know where to place next piece

        //if the coord is null that mean that all pieces on the board have been filled, solution is found!
        if (coord == null) {
            printBoard(board);
            return true;
        }

        //this for loop loops through the available pieces to place
        for (int i = 0; i < pieces.size(); ++i) {
            char pc = pieces.get(i);
            ArrayList<int[][]> pieceOrs = pieceHashMap.get(pc).getOrientations();
            pieces.remove(i);
            for (int[][] orient : pieceOrs) {  //loop through all possible orientations for that piece
                if (!canBePlaced(board, Arrays.copyOf(coord, coord.length), orient)) {
                    continue;  //skip to next orientation is the current one failed
                }
                char[][] boardCopy = copyBoard(board);
                placePiece(boardCopy, Arrays.copyOf(coord, coord.length), orient);
                ++pcid;
                boolean result = solveBoard(boardCopy, pieces);
                if (result)
                    return true;
                --pcid;
            }
            pieces.add(i, pc);  //add back the piece and then move to the next one in the list
        }
        return false;
    }

    private char[][] copyBoard(char[][] boardToCopy){
        char[][] copy = new char[boardToCopy.length][];
        for (int i = 0; i < boardToCopy.length; ++i) {
            //http://stackoverflow.com/questions/5785745/make-copy-of-array-java
            copy[i] = Arrays.copyOf(boardToCopy[i], boardToCopy[i].length);
        }
        return copy;
    }

    /*
    This function checks to see if a piece can be placed in the given location and orientation of the piece on the board
     */
    private boolean canBePlaced(char[][] board, int[] coord, int[][] orient){
        if (coord == null || board == null || orient == null)
            return false;

        //the offset is meant to be used to know where to overlay the piece array over the board array
        int offset = getOffset(orient[0]);
        int[] shape = {orient.length, orient[0].length};

        //check to make sure that the piece is within the bounds of the board being placed in the given location
        if (coord[1] - offset < 0 ||
                coord[1] + (shape[1] - offset) > board[0].length ||
                coord[0] + shape[0] > board.length)
            return false;

        coord[1] -= offset;

        //Check to see if the piece could be successfully placed in the given location
        for (int i = 0; i < shape[0]; ++i) {
            for (int j = 0; j < shape[1]; ++j) {
                if (board[coord[0]+i][coord[1]+j] != '\u0000' && orient[i][j] != 0) {
                    return false;
                }
            }
        }

        return true;
    }

    private void placePiece(char[][] board, int[] coord, int[][] orient){
        int offset = getOffset(orient[0]);
        int[] shape = {orient.length, orient[0].length};
        coord[1] -= offset;

        //Place the piece on the board by iterating over piece array and placing on the corresponding location on the
        //board using the given coordinate to place at after also adjusting for offset
        for (int i = 0; i < shape[0]; ++i) {
            for (int j = 0; j < shape[1]; ++j) {
                if (orient[i][j] == 1) {
                    board[coord[0] + i][coord[1] + j] = pcid;
                }
            }
        }
    }

    /*
    The offset is the first non 0 index location that is encountered on the piece array. The part of the piece that
    will always go first on the given coordinate will be the first part of the piece going left to right, top to bottom
     */
    private int getOffset(int[] arr){
        for (int i = 0; i < arr.length; ++i) {
            if (arr[i] != 0)
                return i;
        }
        return -1;
    }

    private void printBoard(char[][] board){
        for (char[] row : board) {
            for (char ch : row)
                System.out.print(ch);
            System.out.print('\n');
        }
    }

    /*
    Simple function to get the upper left coordinate by looping through the board left to right, top to bottom.
    Will return null if all parts of the board are occupied indicating a solution.
     */
    private int[] getUpperLeftCoord(char[][] board){
        for (int i = 0; i < board.length; ++i)
            for (int j = 0; j < board[i].length; ++j)
                if(board[i][j] == '\u0000')
                    return new int[] {i, j};
        return null;
    }
}
