import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 접근법 : A 진수를 B 진수로 바꿀 때 특별한 케이스가 아니면 10진수를 거치면 됨
// 개선사항 : 결과 출력 시 반드시 출력 형식을 지켜야함
// 끝에 공백이나 개행도 절대 붙이면 안됨. 틀렸다고 채점됨
public class Q11576 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int len = Integer.parseInt(br.readLine());
        int sum = 0;
        int e = len - 1;
        StringBuilder answer = new StringBuilder();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < len; i++) {
            sum += Math.pow(A, e--) * Integer.parseInt(st.nextToken());
        }

        while (sum >= B) {
            answer.insert(0, sum % B + " ");
            sum /= B;
        }
        answer.insert(0, sum + " ");
        answer.deleteCharAt(answer.length() - 1);
        System.out.print(answer);
    }
}
