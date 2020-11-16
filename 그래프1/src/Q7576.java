import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q7576 {

    static int M;
    static int N;
    static int[][] map;
    static int[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static LinkedList<Pair> queue = new LinkedList<>();
    static int answer = 1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    // 여러 시작좌표를 미리 큐에 넣어둠
                    queue.add(new Pair(i, j));
                    visited[i][j] = 1;
                }
            }
        }
        bfs();

        // 예외처리
        if (check()) {
            System.out.print(answer-1);
        }
        else {
            System.out.print(-1);
        }
    }

    static void bfs() {
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                if (map[nx][ny] == 0 && visited[nx][ny] == 0) {
                    queue.add(new Pair(nx, ny));
                    visited[nx][ny] = visited[p.getX()][p.getY()] + 1;
                    if (answer < visited[nx][ny]) {
                        answer = visited[nx][ny];
                    }
                }
            }
        }
    }

    static boolean check() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0 && visited[i][j] == 0) {
                    return false;
                }
            }
        }
        return true;
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
