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

                swap(i, j, i, j+1);

                char[] line1 = new char[n];
                char[] line2 = new char[n];
                char[] line3 = new char[n];

                for (int k = 0; k < n; k++) {
                    line1[k] = candyBox[k][j];
                    line2[k] = candyBox[k][j+1];
                    line3[k] = candyBox[i][k];
                }

                a = maxOneLine(line1);
                b = maxOneLine(line2);
                c = maxOneLine(line3);

                if (max < Integer.max(Integer.max(a, b), c)) {
                    max = Integer.max(Integer.max(a, b), c);
                }

                // 배열을 원상복귀 시켜놓음(re-swap)
                swap(i, j, i, j+1);
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n-1; j++) {

                swap(j, i, j+1, i);

                char[] line1 = new char[n];
                char[] line2 = new char[n];
                char[] line3 = new char[n];

                for (int k = 0; k < n; k++) {
                    line1[k] = candyBox[j][k];
                    line2[k] = candyBox[j+1][k];
                    line3[k] = candyBox[k][i];
                }

                a = maxOneLine(line1);
                b = maxOneLine(line2);
                c = maxOneLine(line3);

                if (max < Integer.max(Integer.max(a, b), c)) {
                    max = Integer.max(Integer.max(a, b), c);
                }

                // 배열을 원상복귀 시켜놓음(re-swap)
                swap(j, i, j+1, i);
            }
        }

        System.out.println(max);
    }

    static void swap(int idx1, int idx2, int idx3, int idx4) {
        char tmp = candyBox[idx1][idx2];
        candyBox[idx1][idx2] = candyBox[idx3][idx4];
        candyBox[idx3][idx4] = tmp;
    }

    static int maxOneLine(char[] oneLine) {

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

