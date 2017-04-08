public class PieceT extends Piece{
    PieceT(){
        super();
        int[][] or1 = {{0, 1, 0},
                       {1, 1, 1}};

        int[][] or2 = {{1, 1, 1},
                       {0, 1, 0}};

        int[][] or3 = {{1, 0},
                       {1, 1},
                       {1, 0}};

        int[][] or4 = {{0, 1},
                       {1, 1},
                       {0, 1}};

        orientations.add(or1);
        orientations.add(or2);
        orientations.add(or3);
        orientations.add(or4);
    }
}