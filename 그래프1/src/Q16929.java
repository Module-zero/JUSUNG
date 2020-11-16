import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// bfs 문제를 해결할 때는
// 방문배열을 잘 활용하면 좋음

public class Q16929 {

    static int N;
    static int M;
    static char[][] board;
    static int[][] visited;
    static LinkedList<Pair> queue = new LinkedList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new char[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (visited[i][j] == 0) {
                    if (bfs(i, j)) {
                        System.out.print("Yes");
                        return;
                    }
                }
            }
        }
        System.out.print("No");
    }

    static boolean bfs(int x, int y) {
        queue.add(new Pair(x, y));
        visited[x][y] = 1;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                // 범위 체크
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                // 게임판상에서 같은 색인 경우
                if (board[nx][ny] == board[p.getX()][p.getY()]) {
                    // 방문하지 않은 좌표이면 방문
                    if (visited[nx][ny] == 0) {
                        queue.add(new Pair(nx, ny));
                        // 다른 곳에서 방문한지 아닌지를 구분하기 위해 +1을 해서 저장
                        visited[nx][ny] = visited[p.getX()][p.getY()] + 1;
                        continue;
                    }
                    // 이미 다른 곳에서 방문한 좌표를 또 만날 경우 사이클 존재
                    if (visited[p.getX()][p.getY()] + 1 == visited[nx][ny]) {
                        return true;
                    }
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
