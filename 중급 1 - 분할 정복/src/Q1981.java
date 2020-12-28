import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q1981 {
    static int n;
    static int[][] arr;
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int answer = Integer.MAX_VALUE;
        int left = 0;
        int right = 200;
        while (left <= right) {
            int mid = (left+right)/2;

            // mid 라는 차이값을 가진 경로가 존재하는가
            if (possible(mid)) {
                answer = mid;
                right = mid-1;
            } else {
                left = mid+1;
            }
        }

        System.out.print(answer);
    }

    static boolean possible(int diff) {
        for (int i = 0; i + diff <= 200; i++) {
            if (bfs(i, i+diff)) {
                return true;
            }
        }
        return false;
    }

    static boolean bfs(int low, int high) {
        if (arr[0][0] < low || arr[0][0] > high) {
            return false;
        }

        boolean[][] check = new boolean[n][n];
        LinkedList<Pair> q = new LinkedList<>();

        check[0][0] = true;
        q.add(new Pair(0, 0));
        while (!q.isEmpty()) {
            Pair p = q.poll();
            int x = p.getX();
            int y = p.getY();
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < n && ny < n) {
                    if (!check[nx][ny]) {
                        if (arr[nx][ny] >= low && arr[nx][ny] <= high) {
                            check[nx][ny] = true;
                            q.add(new Pair(nx, ny));
                        }
                    }
                }
            }
        }
        return check[n-1][n-1];
    }

    static class Pair {
        private final int x, y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
        public int getX() { return x; }
        public int getY() { return y; }
    }
}
