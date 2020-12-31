import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16924 {
    static int n, m;
    static char[][] arr = new char[100][100];
    static boolean[][] check = new boolean[100][100];
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};
    static int cnt = 0;
    static StringBuilder answer = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = s.charAt(j);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '*') {
                    go(i, j);
                }
            }
        }

        if (isCorrespond()) {
            answer.insert(0, cnt + "\n");
            System.out.print(answer);
        } else {
            System.out.print(-1);
        }
    }

    static void go(int x, int y) {

        // 십자가의 길이
        int len = Integer.MAX_VALUE;
        for (int i = 0; i < 4; i++) {
            int tmp_len = 0;
            int nx = x + dx[i];
            int ny = y + dy[i];
            while (nx >= 0 && ny >= 0 && nx < n && ny < m && arr[nx][ny] == '*') {
                tmp_len++;
                nx += dx[i];
                ny += dy[i];
            }
            len = Math.min(len, tmp_len);
        }

        // 십자가를 만들 수 없음
        if (len == 0) {
            return;
        }

        check[x][y] = true;
        for (int i = 0; i < 4; i++) {
            int nx = x;
            int ny = y;
            for (int j = 0; j < len; j++) {
                nx += dx[i];
                ny += dy[i];
                check[nx][ny] = true;
            }
        }

        while (len-- > 0) {
            String s = (x+1)+" "+(y+1)+" "+(len+1)+"\n";
            answer.append(s);
            cnt++;
        }
    }

    static boolean isCorrespond() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == '*' && !check[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
