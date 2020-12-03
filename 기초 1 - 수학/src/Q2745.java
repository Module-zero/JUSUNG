import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 접근법 : 기본적인 지수 연산
// 개선사항 : 없음
public class Q2745 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        StringBuilder n = new StringBuilder(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int sum = 0;
        int e = n.length() - 1;
        for (int i = 0; i < n.length(); i++) {
            int num = 0;
            if (n.charAt(i) < '0' || n.charAt(i) > '9') {
                num = (int)n.charAt(i) - 55;
            }
            else {
                num = n.charAt(i) - '0';
            }
            sum += num * Math.pow(B, e--);
        }
        if (sum <= 1000000000) {
            System.out.print(sum);
        }
    }
}
