import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q9944 {
    static int n, m;
    static char[][] map;
    static boolean[][] visited;
    static int min;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cnt = 1;
        String input = null;
        while ((input = br.readLine()) != null) {
            StringTokenizer st = new StringTokenizer(input);
            n = Integer.parseInt(st.nextToken());
            m = Integer.parseInt(st.nextToken());
            map = new char[n][m];
            visited = new boolean[n][m];
            min = Integer.MAX_VALUE;
            for (int i = 0; i < n; i++) {
                String s = br.readLine();
                for (int j = 0; j < m; j++) {
                    map[i][j] = s.charAt(j);
                }
            }

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    if (map[i][j] == '.') {
                        dfs(0, i, j);
                    }
                }
            }

            if (min == Integer.MAX_VALUE) {
                min = -1;
            }

            System.out.println("Case "+(cnt++)+": "+min);
        }
    }

    static void dfs(int index, int x, int y) {

        visited[x][y] = true;

        // 모든 빈 칸을 방문하였는가?
        if (isFull()) {
            min = Math.min(min, index);
            return;
        }

        // x, y 에서 4방향으로 공을 굴림
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 공을 해당 방향 끝까지 굴림
            while (nx >= 0 && ny >= 0 && nx < n && ny < m && !visited[nx][ny] && map[nx][ny] != '*') {
                visited[nx][ny] = true;
                nx += dx[i];
                ny += dy[i];
            }
            nx -= dx[i];
            ny -= dy[i];

            // 해당 방향으로는 이동할 수 없음
            if (x == nx && y == ny) {
                continue;
            }

            dfs(index+1, nx, ny);

            // 탐색 처리 원상 복구
            while (nx != x || ny != y) {
                visited[nx][ny] = false;
                nx -= dx[i];
                ny -= dy[i];
            }
        }

        visited[x][y] = false;
    }

    static boolean isFull() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == '.' && !visited[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
