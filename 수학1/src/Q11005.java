import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 접근법 : 나머지 연산을 활용
// 개선사항 : 없음
public class Q11005 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int N = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        while (N >= B) {
            int res = N % B;
            if (res >= 10) {
                answer.insert(0, (char)(res + 55));
            }
            else {
                answer.insert(0, res);
            }
            N /= B;
        }
        if (N >= 10) {
            answer.insert(0, (char)(N + 55));
        }
        else {
            answer.insert(0, N);
        }
        System.out.print(answer);
    }
}
