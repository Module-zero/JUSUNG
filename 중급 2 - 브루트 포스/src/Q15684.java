import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q15684 {
    static int n, m, h;
    static int[][] map = new int[11][31];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            map[a][b] = b+1;
            map[a][b+1] = b;
        }

        go(0, 0, new Pair(1, 1));
    }

    static void go(int index, int cnt, Pair np) {
    }

    static class Pair {
        final private int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() { return x; }
        public int getY() { return y; }
    }

    static void printMap() {
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= h; j++) {
                System.out.print(map[i][j]+" ");
            } System.out.println();
        } System.out.println();
    }
}
