import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 접근법 :
// 일반적인 DP 문제와 동일하게 접근함

// 개선사항 :
// 배열 원소의 범위가 int 형을 벗어날 수 있으므로 가능하면 long 형으로 선언해주는 것이 좋음
// 출력 양식을 명확히 지켜주는 것이 좋음
public class Q15988 {

    static long[] D;
    static int MAX = 1000000;
    static int divisor = 1000000009;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        D = new long[MAX + 1];
        D[0] = 0;
        D[1] = 1;
        D[2] = 2;
        D[3] = 4;

        for (int i = 4; i <= MAX; i++) {
           D[i] = (D[i-3] + D[i-2] + D[i-1]) % divisor;
        }

        for (int i = 0; i < T; i++) {
            int n = Integer.parseInt(br.readLine());
            System.out.println(D[n]);
        }
    }
}
