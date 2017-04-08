public class PieceI extends Piece {
    PieceI(){
        super();
        int[][] or1 = {{1},
                       {1},
                       {1},
                       {1}};
        int[][] or2 = {{1, 1, 1, 1}};
        orientations.add(or1);
        orientations.add(or2);
    }
}