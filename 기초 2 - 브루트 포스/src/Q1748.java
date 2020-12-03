import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 접근법 :
// 브루트 포스

// 이유 :
// 모든 경우의 수를 구해도 시간이 초과되지않음
public class Q1748 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();
        int n = str.length();
        int x = Integer.parseInt(str);

        int digit = 0;
        for (int i = 1; i < n; i++) {
            digit += i * 9 *Math.pow(10, i-1);
        }

        digit += (x - Math.pow(10, n-1) + 1) * n;
        System.out.print(digit);
    }
}
