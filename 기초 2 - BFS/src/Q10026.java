import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Q10026 {
    static int n;
    static char[][] map;
    static int[][] visited;
    static LinkedList<Pair> q = new LinkedList<>();
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new char[n][n];
        visited = new int[n][n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0) {
                    bfs(i, j, count++);
                }
            }
        }

        System.out.print(count-1 + " ");

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (map[i][j] == 'R' || map[i][j] == 'G') {
                   map[i][j] = 'K';
                }
            }
        }

        visited = new int[n][n];
        count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (visited[i][j] == 0) {
                    bfs(i, j, count++);
                }
            }
        }

        System.out.print(count-1);
    }

    static void bfs(int x, int y, int count) {
        visited[x][y] = count;
        q.add(new Pair(x, y, map[x][y]));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            char color = p.getColor();
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (visited[nx][ny] == 0 && map[nx][ny] == color) {
                        visited[nx][ny] = count;
                        q.add(new Pair(nx, ny, color));
                    }
                }
            }
        }
    }

    static class Pair {
        private int x, y;
        private char color;
        public Pair(int x, int y, char color) {
            this.x = x;
            this.y = y;
            this.color = color;
        }
        public int getX() { return x; }
        public int getY() { return y; }
        public char getColor() { return color; }
    }
}
