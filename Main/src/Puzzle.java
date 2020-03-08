import java.util.Arrays;
import java.util.Random;


public class Puzzle {
    Random rand;
    int[][] board;
    int w, h;

    Location[] dirs = {new Location(-1, 0), new Location(0, 1), new Location(1, 0), new Location(0, -1)};

    public Puzzle(int height, int width) {
        rand = new Random();
        h = height;
        w = width;
        board = new int[height][width];
        for (int i = 0; i < board.length; i++)
            for (int j = 0; j < board[0].length; j++)
                board[i][j] = i * board[0].length + j;
    }

    void shuffle(int n) {
        for (int c = 0; c < n; c++) {
            Location zero = find(0);
            int dir = rand.nextInt(4);
            swap(zero, zero.add(dirs[dir]));

            print();
        }
    }

    Location find(int x) {
        for (int i = 0; i < h; i++)
            for (int j = 0; j < w; j++)
                if (board[i][j] == x)
                    return new Location(i, j);
        return null;
    }

    boolean swap (Location l1, Location l2){
        if(l1.i < 0 && l1.j < 0 || l2.i < 0 || l2.j < 0)
            return false;
        if(l1.i >= h && l1.j >= w || l2.i >= h || l2.j >= w)
            return false;

        int temp = get(l1);
        set(l1, get(l2));
        set(l2, temp);

        return true;
    }

    int get(Location l){
        return board[l.i][l.j];
    }

    void set(Location l, int x){
        board[l.i][l.j] = x;
    }

    boolean isSolved() {
        return false;
    }


    void print() {
        for (int[] i : board)
            System.out.println(Arrays.toString(i));

        System.out.println("_______________");
    }

    public static void main(String[] args) {
        Puzzle p = new Puzzle(2, 3);
        p.print();
        p.shuffle(5);
    }

    class Location {
        int i, j;

        public Location(int i, int j) {
            this.i = i;
            this.j = j;
        }

        Location add(Location l){
            return new Location(i + l.i, j + l.j);
        }
    }
}
