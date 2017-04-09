/*
Author: Daniel Eynis
E-mail: eynis@pdx.edu
 */

import java.util.ArrayList;

public abstract class Piece {
    protected ArrayList<int[][]> orientations = new ArrayList<>();

    public ArrayList<int[][]> getOrientations(){
        return orientations;
    }
}
