import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1080 {
    static int n, m;
    static int[][] arr1, arr2;
    static boolean[][] check;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr1 = new int[n][m];
        arr2 = new int[n][m];
        check = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                arr1[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                arr2[i][j] = s.charAt(j) - '0';
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                check[i][j] = (arr1[i][j] == arr2[i][j] ? false : true);
            }
        }

        // 3*3 보다 작은 경우 연산은 불가능
        if (n <= 2 || m <= 2) {
            if (checkAnswer()) {
                System.out.print(0);
            } else {
                System.out.print(-1);
            }
            return;
        }

        int count = 0;
        for (int i = 0; i <= n-3; i++) {
            for (int j = 0; j <= m-3; j++) {
                // 연산 진행
                if (check[i][j] == true) {
                    count++;
                    cal(i, j);
                }
            }
        }

        if (checkAnswer()) {
            System.out.print(count);
        } else {
            System.out.print(-1);
        }
    }

    static boolean checkAnswer() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (check[i][j] == true) {
                    return false;
                }
            }
        }
        return true;
    }

    static void cal(int x, int y) {
        for (int i = x; i < x+3; i++) {
            for (int j = y; j < y+3; j++) {
                check[i][j] = !check[i][j];
            }
        }
    }
}
