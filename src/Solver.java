import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Solver {
    private int rows;
    private int cols;
    private ArrayList<Character> pieces = new ArrayList<>();
    private HashMap<Character, Piece> pieceHashMap = new HashMap<>();
    private char[][] board;

    Solver(){
        Scanner input = new Scanner(System.in);
        rows = input.nextInt();
        cols = input.nextInt();
        String pcs = input.next();

        //http://stackoverflow.com/questions/15331637/convert-string-to-arraylist-character-in-java
        for(char piece : pcs.toCharArray())
            pieces.add(piece);

        board = new char[rows][cols];

        pieceHashMap.put('I', new PieceI());
        pieceHashMap.put('5', new Piece5());
        pieceHashMap.put('2', new Piece2());
        pieceHashMap.put('T', new PieceT());
        pieceHashMap.put('L', new PieceL());
        pieceHashMap.put('O', new Piece0());
        pieceHashMap.put('P', new PieceP());
    }

    public void solveBoard(){
        solveBoard(board, pieces);
    }

    private boolean solveBoard(char[][] board, ArrayList<Character> pieces){
        int[] coord = getUpperLeftCoord(board);

        if (coord == null) {
            printBoard(board);
            return true;
        }

        if(pieces.isEmpty())
            return false;

        for (char pc : pieces) {
            ArrayList<int[][]> pieceOrs = pieceHashMap.get(pc).getOrientations();
            pieces.remove(pc);
            for (int[][] orient : pieceOrs) {
                if (!placePiece(board, Arrays.copyOf(coord, coord.length), orient)) {
                    continue;
                }
                boolean result = solveBoard(copyBoard(board), pieces);
                if (result)
                    return result;
            }
            pieces.add(pc);
        }
        return false;
    }

    private char[][] copyBoard(char[][] boardToCopy){
        char[][] copy = new char[boardToCopy.length][];
        for (int i = 0; i < boardToCopy.length; ++i) {
            copy[i] = Arrays.copyOf(boardToCopy[i], boardToCopy[i].length);
        }
        return copy;
    }

    private boolean placePiece(char[][] board, int[] coord, int[][] orient){
        if (coord == null || board == null || orient == null)
            return false;

        int offset = getOffset(orient[0]);
        int[] shape = {orient.length, orient[0].length};

        if ((coord[1] - offset) < 0 ||
                coord[1] + shape[1] > board[0].length ||
                coord.length-1 + shape[0] > coord.length)
            return false;

        coord[1] -= offset;

        boolean canBePlaced = true;
        for (int i = 0; i < shape[0]; ++i) {
            for (int j = 0; j < shape[1]; ++j) {
                if (board[coord[0]+i][coord[1]+j] != '\u0000' && orient[i][j] != 0) {
                    canBePlaced = false;
                }
            }
        }

        if (!canBePlaced)
            return false;

        for (int i = 0; i < shape[0]; ++i) {
            for (int j = 0; j < shape[1]; ++j) {
                if (board[coord[0] + i][coord[1] + j] == '\u0000') {
                    board[coord[0] + i][coord[1] + j] = 'a';
                }
            }
        }

        return true;
    }

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

    private int[] getUpperLeftCoord(char[][] board){
        for (int i = 0; i < board.length; ++i)
            for (int j = 0; j < board[i].length; ++j)
                if(board[i][j] == '0')
                    return new int[] {i, j};
        return null;
    }
}
