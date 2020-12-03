import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

// (0,0)~(N-1,M-1)까지 최단거리 구하기
// dfs 로 풀 수는 있으나 시간초과
// -> dfs 는 하나의 루트를 끝까지 간 뒤, 돌아와서 다시 가는 등 불필요한 탐색이 많음
// bfs 는 단계별로 한차례만 진행해도 답을 구할 수 있음
public class Q2178 {

    static int N;
    static int M;
    static int[][] maze;
    static int[][] visited;
    static LinkedList<Pair> queue = new LinkedList<>();
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new int[N][M];
        visited = new int[N][M];
        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = s.charAt(j)-'0';
            }
        }

        bfs(0, 0);
        System.out.print(visited[N-1][M-1]);
    }

    static void bfs(int x, int y) {
        queue.add(new Pair(x, y));
        // 이동한 칸수를 방문배열에 표시
        visited[x][y] = 1;
        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = p.getX() + dx[i];
                int ny = p.getY() + dy[i];
                // 인접한 좌표가 범위안에 있는지 체크
                if (nx < 0 || ny < 0 || nx >= N || ny >= M) {
                    continue;
                }
                // 인접한 좌표가 길이 존재하고 방문한 적이 없는지 체크
                if (maze[nx][ny] == 1 && visited[nx][ny] == 0) {
                    queue.add(new Pair(nx, ny));
                    // 이동한 칸수를 하나 증가
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
