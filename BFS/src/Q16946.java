import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q16946 {
    static int n, m;
    static int[][] map;
    static int[][] visited;
    static int[][] answer;
    static int[] group = new int[1000000];
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new int[n][m];
        answer = new int[n][m];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j)-'0';
            }
        }

        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0 && visited[i][j] == 0) {
                    bfs(i, j, count++);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    answer[i][j] = 0;
                }
                else {
                    foo(i, j);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                sb.append(answer[i][j]);
            } sb.append("\n");
        }
        System.out.print(sb);
    }

    static void foo(int x, int y) {
        long count = 1;
        // boolean[] check = new boolean[1000000];
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                if (map[nx][ny] == 0) {
                    int num = visited[nx][ny];
                    if (!set.contains(num)) {
                        set.add(num);
                        count += group[num];
                    }
                    /*
                    if (!check[num]) {
                        check[num] = true;
                        count += group[num];
                    }
                     */
                }
            }
        }
        answer[x][y] = (int)(count % 10);
    }

    static void bfs(int x, int y, int count) {
        LinkedList<Pair> q = new LinkedList<>();
        visited[x][y] = count;
        q.add(new Pair(x, y));
        group[count]++;

        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m) {
                    if (map[nx][ny] != 1 && visited[nx][ny] == 0) {
                        visited[nx][ny] = count;
                        q.add(new Pair(nx, ny));
                        group[count]++;
                    }
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
