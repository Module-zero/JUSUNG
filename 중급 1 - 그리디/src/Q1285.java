import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1285 {
    static int n;
    static char[][] coins;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        coins = new char[n][n];
        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < n; j++) {
                coins[i][j] = s.charAt(j);
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int state = 0; state < (1 << n); state++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                int nT = 0;
                for (int j = 0; j < n; j++) {
                    char ch = coins[j][i];
                    // i -> 뒤집은 행
                    if ((state & (1 << j)) != 0) {
                        ch = (ch == 'H' ? 'T' : 'H');
                    }
                    if (ch == 'T') {
                        nT++;
                    }
                }
                // 한 행에 대한 T의 개수 계산이 끝남
                sum += Math.min(nT, n-nT);
            }
            if (answer > sum) {
                answer = sum;
            }
        }

        System.out.print(answer);
    }
}
