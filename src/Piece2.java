public class Piece2 extends Piece{
    Piece2(){
        super();
        int[][] or1 = {{1, 1, 0},
                       {0, 1, 1}};

        int[][] or2 = {{0, 1},
                       {1, 1},
                       {1, 0}};
        orientations.add(or1);
        orientations.add(or2);
    }
}