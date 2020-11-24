import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q2234 {

    static int n, m;
    static int[][] map;
    static int[][] visited;
    static int[] nRooms;
    static int max = Integer.MIN_VALUE;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        nRooms = new int[n*m + 1];
        visited = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < m; j++) {
               map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int count = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visited[i][j] == 0) {
                    bfs(i, j, count++);
                }
            }
        }

        // 방의 개수
        System.out.println(count-1);

        for (int i = 0; i < nRooms.length; i++) {
            if (nRooms[i] > max) {
                max = nRooms[i];
            }
        }

        // 가장 넓은 방의 넓이
        System.out.println(max);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                deleteWall(i, j);
            }
        }

        System.out.print(max);
    }

    static void deleteWall(int x, int y) {
        for (int i = 0; i < 4; i++) {
            // 해당 방향에는 벽이 있음
            if ((map[x][y] & (int)Math.pow(2, i)) != 0) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                    continue;
                }
                int idx1 = visited[x][y];
                int idx2 = visited[nx][ny];
                if (idx1 != idx2) {
                    if (max < nRooms[idx1] + nRooms[idx2]) {
                        max = nRooms[idx1] + nRooms[idx2];
                    }
                }
            }
        }
    }

    static void bfs(int x, int y, int count) {

        LinkedList<Pair> q = new LinkedList<>();
        visited[x][y] = count;
        nRooms[count]++;
        q.add(new Pair(x, y));

        while (!q.isEmpty()) {
            Pair p = q.poll();
            for (int i = 0; i < 4; i++) {
                // 해당 방향에는 벽이 있음
                if ((map[p.getX()][p.getY()] & (int)Math.pow(2, i)) != 0) {
                    continue;
                }
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (visited[nx][ny] != 0) {
                    continue;
                }
                visited[nx][ny] = count;
                nRooms[count]++;
                q.add(new Pair(nx, ny));
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
