/*
Author: Daniel Eynis
E-mail: eynis@pdx.edu
 */

public class PieceP extends Piece{
    PieceP(){
        super();
        int[][] or1 = {{1, 1, 1},
                       {0, 0, 1}};

        int[][] or2 = {{1, 0, 0},
                       {1, 1, 1}};

        int[][] or3 = {{1, 1},
                       {1, 0},
                       {1, 0}};

        int[][] or4 = {{0, 1},
                       {0, 1},
                       {1, 1}};
        orientations.add(or1);
        orientations.add(or2);
        orientations.add(or3);
        orientations.add(or4);
    }
}