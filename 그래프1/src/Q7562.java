import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// dfs 와 bfs 의 가장 큰 차이는?
// dfs 는 실제 경로를 찾을 때 유용하고, bfs 는 거리를 구할 때 유용함
public class Q7562 {

    static int T;
    static int L;
    static int[][] board;
    static int[][] visited;
    static int[] dx = {-2, -2, 2, 2, -1, 1, -1, 1};
    static int[] dy = {-1, 1, -1, 1, -2, -2, 2, 2};
    static LinkedList<Pair> queue = new LinkedList<>();
    static Pair start;
    static Pair end;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            L = Integer.parseInt(br.readLine());
            board = new int[L][L];
            visited = new int[L][L];
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            start = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            st = new StringTokenizer(br.readLine(), " ");
            end = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            bfs(start.getX(), start.getY());
            System.out.println(visited[end.getX()][end.getY()] - 1);
        }
    }

    static void bfs(int x, int y) {
        queue.add(new Pair(x, y));
        visited[x][y] = 1;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < dx.length; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                if (nx < 0 || ny < 0 || nx >= L || ny >= L) {
                    continue;
                }
                if (visited[nx][ny] == 0) {
                    queue.add(new Pair(nx, ny));
                    visited[nx][ny] = visited[p.getX()][p.getY()] + 1;
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
