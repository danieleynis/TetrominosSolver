/*
Author: Daniel Eynis
E-mail: eynis@pdx.edu
 */

import java.util.ArrayList;

/*
This is the abstract base class that all the pieces use
 */
public abstract class Piece {
    //Allows to store piece orientations
    protected ArrayList<int[][]> orientations = new ArrayList<>();

    //Returns the list of all piece orientations
    public ArrayList<int[][]> getOrientations(){
        return orientations;
    }
}
