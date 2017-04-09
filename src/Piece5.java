/*
Author: Daniel Eynis
E-mail: eynis@pdx.edu
 */

public class Piece5 extends Piece{
    Piece5(){
        super();
        int[][] or1 = {{0, 1, 1},
                       {1, 1, 0}};

        int[][] or2 = {{1, 0},
                       {1, 1},
                       {0, 1}};
        orientations.add(or1);
        orientations.add(or2);
    }
}
