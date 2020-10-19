import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 접근법 :
// 시간내에 해결하지 못했다. 이유는 적합한 D 배열을 정의하지 못해서였다.
// D 배열을 정의하지 못한 이유는 '반드시 존재하는 경우' 를 찾아내지 못해서였다.
// 우리가 가로로 2칸이므로 반드시 존재하는 경우의 수는
// 1. 2칸 모두 사자가 없는 경우
// 2. 왼쪽칸에만 사자가 있는 경우
// 3. 오른쪽칸에만 사자가 있는 경우
// 로 나눠 볼수 있다. 이것만 찾아냈다면 D 배열을 정의할 수 있었을 겻이다

// 개선사항 :
// DP 문제를 해결할 때는 '반드시 존재하는 경우' 를 명확히 찾아내야한다
public class Q1309 {

    static long[][] D;
    static int divisor = 9901;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        D = new long[n+1][3];
        D[1][0] = D[1][1] = D[1][2] = 1;

        for (int i = 2; i <= n; i++) {
            D[i][0] = (D[i-1][0] + D[i-1][1] + D[i-1][2]) % divisor;
            D[i][1] = (D[i-1][0] + D[i-1][2]) % divisor;
            D[i][2] = (D[i-1][0] + D[i-1][1]) % divisor;
        }

        System.out.print((D[n][0] + D[n][1] + D[n][2]) % divisor);
    }
}
