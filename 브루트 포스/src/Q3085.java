import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// 접근법 :
// 브루트 포스

// 이유 :
// 브루트 포스로 접근할 경우 : O(n의 4제곱)
// n은 3~50 이므로 브루트 포스 가능
public class Q3085 {

    static char[][] candyBox;
    static char[] oneLine;
    static int n;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        candyBox = new char[n][n];

        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder(br.readLine());
            for (int j = 0; j < n; j++) {
                candyBox[i][j] = sb.charAt(j);
            }
        }

        int max = Integer.MIN_VALUE;
        int a, b, c;
        char tmp = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {

                // swap
                tmp = candyBox[i][j];
                candyBox[i][j] = candyBox[i][j+1];
                candyBox[i][j+1] = tmp;

                oneLine = new char[n];
                for (int k = 0; k < n; k++) {
                    oneLine[k] = candyBox[k][j];
                } a = func();

                oneLine = new char[n];
                for (int k = 0; k < n; k++) {
                    oneLine[k] = candyBox[k][j+1];
                } b = func();

                oneLine = new char[n];
                for (int k = 0; k < n; k++) {
                    oneLine[k] = candyBox[i][k];
                } c = func();

                if (max < Integer.max(Integer.max(a, b), c)) {
                    max = Integer.max(Integer.max(a, b), c);
                }

                // re-swap
                tmp = candyBox[i][j];
                candyBox[i][j] = candyBox[i][j+1];
                candyBox[i][j+1] = tmp;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {

                // swap
                tmp = candyBox[j][i];
                candyBox[j][i] = candyBox[j+1][i];
                candyBox[j+1][i] = tmp;

                oneLine = new char[n];
                for (int k = 0; k < n; k++) {
                    oneLine[k] = candyBox[j][k];
                } a = func();

                oneLine = new char[n];
                for (int k = 0; k < n; k++) {
                    oneLine[k] = candyBox[j+1][k];
                } b = func();

                oneLine = new char[n];
                for (int k = 0; k < n; k++) {
                    oneLine[k] = candyBox[k][i];
                } c = func();

                if (max < Integer.max(Integer.max(a, b), c)) {
                    max = Integer.max(Integer.max(a, b), c);
                }

                // re-swap
                tmp = candyBox[j][i];
                candyBox[j][i] = candyBox[j+1][i];
                candyBox[j+1][i] = tmp;
            }
        }

        System.out.println(max);
    }

    static int func() {
        int[] tmp = new int[n];
        int cur = 0;

        while (true) {
            int idx = cur;
            int count = 1;

            if (cur >= n-1) {
                tmp[idx] = count;
                break;
            }

            while (oneLine[cur] == oneLine[cur+1]) {
                count++;
                if (cur+1 >= n-1) {
                    break;
                }
                cur++;
            }
            tmp[idx] = count;
            cur++;
        }

        Arrays.sort(tmp);
        return tmp[tmp.length - 1];
    }
}

