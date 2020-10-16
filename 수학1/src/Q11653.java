import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 접근법 : 거의 브루트 포스
// 개선사항 : 없음
public class Q11653 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();

        for (int i = 2; i <= n; i++) {
            while (n % i == 0) {
                answer.append(i + "\n");
                n /= i;
                if (n == 1) {
                    break;
                }
            }
        }
        System.out.print(answer);
    }
}
