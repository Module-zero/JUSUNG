import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 접근법 : 에라토스테네스 체를 생성하여 해결
// 체를 생성할 때 반드시 max 값의 크기로 딱 한번만 생성해야함
// 또한 걸러내는 과정에서 브레이크 포인트를 지정하는 원리를 이해해야함
// 체를 생성하면 인덱스를 활용하여 더 빠른 연산을 수행할 수 있음
// 개선사항 : 없음
public class Q17103 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = Integer.parseInt(br.readLine());

        // 에라토스테네스 체 생성
        int max = 1000000;
        boolean[]check = new boolean[max + 1];
        for (int i = 2; i * i <= max; i++) {
            if (check[i] == false) {
                for (int j = i * 2; j <= max; j += i) {
                    check[j] = true;
                }
            }
        }

        for (int i = 0; i < caseNum; i++) {
            int count = 0;
            int n = Integer.parseInt(br.readLine());
            for (int j = 2; j <= n / 2; j++) {
                if (!check[j] && !check[n - j]) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
}
