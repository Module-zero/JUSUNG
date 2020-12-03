import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q1600 {

    static int K, W, H;
    static int[][] map = new int[200][200];
    static int [][][] visited = new int[200][200][31];
    static LinkedList<Triple> queue = new LinkedList<>();
    static int[] dx = {1, -1, 0, 0, -2, -2, -1, 1, 2, 2, 1, -1};
    static int[] dy = {0, 0, 1, -1, -1, 1, 2, 2, 1, -1, -2, -2};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        K = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        // 가로길이
        W = Integer.parseInt(st.nextToken());
        // 세로길이
        H = Integer.parseInt(st.nextToken());
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                for (int k = 0; k <= K; k++) {
                    visited[i][j][k] = -1;
                }
            }
        }

        for (int i = 0; i < H; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < W; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfs(0, 0);

        int min = Integer.MAX_VALUE;
        for (int i = 0; i <= K; i++) {
            if (visited[H-1][W-1][i] != -1) {
                min = Math.min(min, visited[H-1][W-1][i]);
            }
        }
        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        }
        else {
            System.out.print(min);
        }
    }

    static void bfs(int x, int y) {
        visited[x][y][0] = 0;
        queue.add(new Triple(x, y,0));

        while (!queue.isEmpty()) {
            Triple t = queue.poll();
            for (int i = 0; i < 12; i++) {
                int nx = t.getX() + dx[i];
                int ny = t.getY() + dy[i];
                int nc = t.getC();

                // 범위 밖인 경우
                if (nx < 0 || ny < 0 || nx >= H || ny >= W) {
                    continue;
                }

                if (i >= 4) {
                    nc++;
                }

                if (map[nx][ny] != 1) {
                    if (nc <= K) {
                        if (visited[nx][ny][nc] == -1) {
                            visited[nx][ny][nc] = visited[t.getX()][t.getY()][t.getC()] + 1;
                            queue.add(new Triple(nx, ny, nc));
                        }
                    }
                }
            }
        }
    }

    static class Triple {
        private int x, y, c;
        public Triple(int x, int y, int c) {
            this.x = x;
            this.y = y;
            this.c = c;
        }
        public int getX() { return x; }
        public int getY() { return y; }
        public int getC() { return c; }
    }
}
