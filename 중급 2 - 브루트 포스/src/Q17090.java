import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q17090 {
    static int n, m;
    static char[][] map = new char[500][500];
    static int[][] check = new int[500][500];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (go(i, j)) {
                    answer++;
                }
            }
        }
        System.out.print(answer);
    }

    static boolean go(int x, int y) {

        if (x < 0 || y < 0 || x >= n || y >= m) {
            return true;
        }

        if (check[x][y] == 2) {
            return true;
        }

        if (check[x][y] == 1) {
            return false;
        }

        check[x][y] = 1;
        char ch = map[x][y];
        if (ch == 'U') {
            if (go(x-1, y)) {
                check[x][y] = 2;
                return true;
            }
        } else if (ch == 'R') {
            if (go(x, y+1)) {
                check[x][y] = 2;
                return true;
            }
        } else if (ch == 'D') {
            if (go(x+1, y)) {
                check[x][y] = 2;
                return true;
            }
        } else {
            if (go(x, y-1)) {
                check[x][y] = 2;
                return true;
            }
        }
        return false;
    }
}
