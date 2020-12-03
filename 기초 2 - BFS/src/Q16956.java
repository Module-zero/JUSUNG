import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// 최소를 구하는 문제가 아님
public class Q16956 {

    static int R;
    static int C;
    static char[][] map;
    static int[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static LinkedList<Pair> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new char[R][C];
        visited = new int[R][C];
        for (int i = 0; i < R; i++) {
            String s = br.readLine();
            for (int j = 0; j < C; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] != '.') {
                    // 늑대와 양이 인접해있는 경우 0 리턴 후 종료
                    if (check(i, j)) {
                        System.out.print(0);
                        return;
                    }
                }
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.') {
                    // 맵 상의 모든 '.' 을 'D' 로 변경
                    bfs(i, j);
                }
            }
        }

        // 굳이 bfs 쓸 필요없음
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == '.') {
                    map[i][j] = 'D';
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        sb.append(1 + "\n");
        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                sb.append(map[i][j]);
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }

    static void bfs(int x, int y) {
        queue.add(new Pair(x, y));
        map[x][y] = 'D';
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                    continue;
                }
                if (map[nx][ny] == '.') {
                    queue.add(new Pair(nx, ny));
                    map[nx][ny] = 'D';
                }
            }
        }
    }

    static boolean check(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || ny < 0 || nx >= R || ny >= C) {
                continue;
            }
            if (map[nx][ny] == '.') {
                continue;
            }
            else {
                if (map[x][y] == 'S' && map[nx][ny] == 'W') {
                    return true;
                }
                if (map[x][y] == 'W' && map[nx][ny] == 'S') {
                    return true;
                }
            }
        }
        return false;
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
