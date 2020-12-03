import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q4963 {

    static int w;
    static int h;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0, -1, -1, 1, 1};
    static int[] dy = {0, 0, 1, -1, -1, 1, -1, 1};
    static LinkedList<Pair> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            if (w == 0 && h == 0) {
                break;
            }
            map = new int[h][w];
            visited = new boolean[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            int nIsland = 0;
            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if (map[i][j] == 1 && visited[i][j] == false) {
                        bfs(i, j);
                        nIsland++;
                    }
                }
            }
            System.out.println(nIsland);
        }
    }

    static void bfs(int x, int y) {
        // 큐에 집어넣을 때 방문체크를 함
        queue.add(new Pair(x, y));
        visited[x][y] = true;
        // 큐가 빌때까지 반복
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 8; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                // 유효범위안에 있는지 체크
                if (nx < 0 || ny < 0 || nx >= h || ny >= w) {
                    continue;
                }
                if (map[nx][ny] == 1 && visited[nx][ny] == false) {
                    queue.add(new Pair(nx, ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    static class Pair {
        private int x;
        private int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() { return x; }
        public int getY() { return y; }
    }
}
