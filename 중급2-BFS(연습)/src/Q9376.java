import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q9376 {

    static int T;
    static int h;
    static int w;
    static char[][] map;
    static int[][] visited1;
    static int[][] visited2;
    static int[][] visited3;
    static int[][] sum;
    static ArrayList<Pair> prisoners;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());
            map = new char[h+2][w+2];
            visited1 = new int[h+2][w+2];
            visited2 = new int[h+2][w+2];
            visited3 = new int[h+2][w+2];
            sum = new int[h+2][w+2];
            prisoners = new ArrayList<>();

            for (int i = 1; i <= h; i++) {
                String s = br.readLine();
                for (int j = 1; j <= w; j++) {
                    map[i][j] = s.charAt(j-1);
                    if (map[i][j] == '$') {
                        // 두 죄수의 좌표를 저장
                        prisoners.add(new Pair(i, j));
                    }
                }
            }

            for (int i = 0;  i < h+2; i++) {
                for (int j = 0; j < w+2; j++) {
                    visited1[i][j] = -1;
                    visited2[i][j] = -1;
                    visited3[i][j] = -1;
                }
            }

            bfs(prisoners.get(0).getX(), prisoners.get(0).getY(), visited1);
            bfs(prisoners.get(1).getX(), prisoners.get(1).getY(), visited2);
            bfs(0, 0, visited3);

            int min = visited1[0][0] + visited2[0][0] + visited3[0][0];
            for (int i = 0; i < h+2; i++) {
                for (int j = 0; j < w+2; j++) {
                    if (map[i][j] == '#') {
                        int sum = visited1[i][j] + visited2[i][j] + visited3[i][j];
                        sum -= 2;
                        min = Math.min(min, sum);
                    }
                }
            }

            System.out.println(min);
        }
    }

    static void bfs(int x, int y, int[][] visited) {
        Deque<Pair> queue = new ArrayDeque<>();
        queue.add(new Pair(x, y));
        visited[x][y] = 0;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx < 0 || ny < 0 || nx >= h+2 || ny >= w+2) {
                    continue;
                }
                if (visited[nx][ny] == -1) {
                    if (map[nx][ny] != '*') {
                        // queue.add(new Pair(nx, ny));
                        if (map[nx][ny] == '#') {
                            queue.addLast(new Pair(nx, ny));
                            visited[nx][ny] = visited[p.getX()][p.getY()] + 1;
                        }
                        else {
                            queue.addFirst(new Pair(nx, ny));
                            visited[nx][ny] = visited[p.getX()][p.getY()];
                        }
                    }
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
