import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q14502 {
    static int n, m;
    static int[][] map = new int[8][8];
    static boolean[] visited = new boolean[64];
    static int[] order = new int[3];
    static ArrayList<Pair> virus = new ArrayList<>();
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    virus.add(new Pair(i, j));
                }
            }
        }

        go(0, 0);
        System.out.print(max);
    }


    static void go(int index, int start) {

        if (index == 3) {
            LinkedList<Pair> q = new LinkedList<>();
            boolean[][] check = new boolean[n][m];

            for (int i : order) {
                int x = i / m;
                int y = i - (m * x);
                // 이미 벽이 세워져있는 경우
                if (map[x][y] != 0) {
                    return;
                }
                // 벽을 세운 좌표를 이미 방문한 좌표로 표시
                check[x][y] = true;
            }

            for (Pair p : virus) {
                q.add(p);
                check[p.getX()][p.getY()] = true;
            }

            while (!q.isEmpty()) {
                Pair p = q.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = p.getX() + dx[i];
                    int ny = p.getY() + dy[i];
                    if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                        if (!check[nx][ny] && map[nx][ny] != 1) {
                            q.add(new Pair(nx, ny));
                            check[nx][ny] = true;
                        }
                    }
                }
            }

            int count = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == 0 && !check[i][j]) {
                        count++;
                    }
                }
            }

            if (max < count) {
                max = count;
            }

            return;
        }

        for (int i = start; i < n*m; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[index] = i;
                go(index+1, i+1);
                visited[i] = false;
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
