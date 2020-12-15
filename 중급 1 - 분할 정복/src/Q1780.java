import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q1780 {
    static int n;
    static int[][] arr;
    static int[] count = new int[3];

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

        go(0, 0, n);

        for (int i = 0; i < 3; i++) {
            System.out.println(count[i]);
        }
    }

    static void go(int x, int y, int len) {
        if (check(x, y, len)) {
            count[arr[x][y]+1]++;
            return;
        }

        for (int i = x; i < x+len; i += len/3) {
            for (int j = y; j < y+len; j += len/3) {
                go(i, j, len/3);
            }
        }
    }

    // 종이가 모두 같은 숫자로 되어있는지 체크
    static boolean check(int x, int y, int len) {
        int num = arr[x][y];
        for (int i = x; i < x+len; i++) {
            for (int j = y; j < y+len; j++) {
                if (num != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
