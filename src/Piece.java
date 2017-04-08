import java.util.ArrayList;

public abstract class Piece {
    protected ArrayList<int[][]> orientations = new ArrayList<>();

    public ArrayList<int[][]> getOrientations(){
        return orientations;
    }
}
