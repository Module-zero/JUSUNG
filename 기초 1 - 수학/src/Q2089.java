import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 접근법 : 2진법과 똑같이 나머지 연산으로 처리하는 방식
// 개선사항 : 나머지 연산에 대한 개념이 확실히 정리되어있지 않아서 해결하지 못함
// 2진법은 0, 1 로만 표현되므로 2*n + 1 혹은 2*n + 0 의 형태로 나머지 연산을 진행해야함
// 따라서 -2진법은 -2*n + 1 혹은 -2*n + 0 의 형태로 나머지 연산을 진행해야함
public class Q2089 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder answer = new StringBuilder();
        int n = Integer.parseInt(br.readLine());

        if (n == 0) {
            System.out.print(0);
            return;
        }

        while (n != 1) {
            if (n % 2 != 0) {
                answer.insert(0, 1);
                n = (n-1)/(-2);
            }
            else {
                answer.insert(0, 0);
                n = n/(-2);
            }
        }
        answer.insert(0, 1);
        System.out.print(answer);
    }
}