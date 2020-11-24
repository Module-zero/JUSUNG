import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;

public class Q2151 {

    static int N;
    static char[][] map;
    static int[][][] visited;
    static ArrayList<Pair> twoDoors;
    static LinkedList<Pair> queue;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new char[N][N];
        visited = new int[N][N][4];
        twoDoors = new ArrayList<>();
        queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = s.charAt(j);
                if (map[i][j] == '#') {
                    // 양쪽 문의 좌표를 저장
                    twoDoors.add(new Pair(i, j, -1));
                }
            }
        }

        bfs();
    }

    // 0 : 아래
    // 1 : 위
    // 2 : 오른쪽
    // 3 : 왼쪽
    static void bfs() {

        for (int i = 0; i < 4; i++) {
            twoDoors.get(0).setDir(i);
            queue.add(twoDoors.get(0));
        }

        while (!queue.isEmpty()) {
            Pair p = queue.poll();

            if (map[p.getX()][p.getY()] == '!') {

            }
        }
    }

    static class Pair {
        private int x;
        private int y;
        private int dir;
        public Pair(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getDir() { return dir; }
        public void setDir(int dir) { this.dir = dir; }
    }
}
