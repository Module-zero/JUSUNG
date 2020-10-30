import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 재귀를 사용한 브루트 포스

// 순차적으로 경우의 수를 하나하나 따져보는 경우라면 재귀를 활용해서
// 모든 경우를 찾는것이 유리함

public class Q9095 {

    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        while (t-- > 0) {
            answer = 0;
            int n = Integer.parseInt(br.readLine());
            go(0, n);
            System.out.println(answer);
        }
    }

    static void go(int sum, int n) {
        if (sum > n) {
            return;
        }
        if (sum == n) {
            answer++;
            return;
        }

        for (int i = 1; i <= 3; i++) {
            go(sum+i , n);
        }
    }
}
