import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 모든 좌표에서 깊이가 4인 경로를 구하는 문제

// DFS 사용. DFS 는 연결되어있는 경로를 깊이 우선으로 탐색함

// 입출력 과정을 항상 자세하게 테스트해볼것

public class Q14500 {

    static int N;
    static int M;
    static int[][] paper;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.valueOf(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                dfs(i, j, 0, 0);
                exception(i, j);
            }
        }

        System.out.print(max);
    }

    static void dfs(int x, int y, int depth, int sum) {

        if (depth == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                continue;
            }

            if (visited[nextX][nextY] == true) {
                continue;
            }

            visited[x][y] = true;
            dfs(nextX, nextY, depth + 1, sum + paper[x][y]);
            visited[x][y] = false;
        }
    }

    static void exception(int x, int y) {

        int sum = paper[x][y];
        int min = Integer.MAX_VALUE;
        int nWing = 4;
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            if (nWing == 2) {
                return;
            }

            if (nextX < 0 || nextY < 0 || nextX >= N || nextY >= M) {
                nWing--;
                continue;
            }

            sum += paper[nextX][nextY];
            min = Math.min(min, paper[nextX][nextY]);
        }

        if (nWing == 4) {
            sum -= min;
        }

        max = Math.max(max, sum);
    }
}
