import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q17086 {

    static int N, M;
    static int[][] map = new int[50][50];
    static int[][] visited;
    static LinkedList<Pair> queue;
    static int[] dx = {1, -1, 0, 0, -1, 1, -1, 1};
    static int[] dy = {0, 0, 1, -1, -1, -1, 1, 1};
    static int max = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) {
                    bfs(i, j);
                }
            }
        }

        System.out.print(max);
    }

    static void bfs(int x, int y) {
        queue = new LinkedList<>();
        visited = new int[50][50];
        visited[x][y] = 1;
        queue.add(new Pair(x, y));
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (map[p.getX()][p.getY()] == 1) {
                int dist = visited[p.getX()][p.getY()] - 1;
                max = Math.max(max, dist);
                break;
            }
            for (int i = 0; i < dx.length; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (visited[nx][ny] == 0) {
                    visited[nx][ny] = visited[p.getX()][p.getY()] + 1;
                    queue.add(new Pair(nx, ny));
                }
            }
        }
    }

    static class Pair {
        private int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() { return x; }
        public int getY() { return y; }
    }
}
