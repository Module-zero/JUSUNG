import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

// 접근법 :
// 다이나믹 프로그래밍을 활용
// 배열 D의 D[i]는 "A[i]로 끝나는 가장 긴 증가수열의 길이" 임. 해당 정보를 활용하여 점화식을 생성
// 다이나믹 프로그래밍에서 점화식을 생성할 때는 항상 이전에 저장된 정보들을 어떻게 활용할 지를 고민해야함

// 개선사항 :
// 1. 나는 습관적으로 중간변수를 활용하려는 습관이 있음. 그러나 어떤 상황에서는 중간변수를 활용하는 것이
// 더 복잡하고 실수를 유발하는 경우도 있음. 가능한 한 주어진 변수를 그대로 활용하여 연산하려는 습관을 가져보자
// 2. 인덱스 계산 시 거꾸로 루핑하는 경우가 의외로 많음. 이 역시 가능하면 정방향으로 가는 것이 좋을 수 있음
// 거꾸로 가지않고 정방향으로 가면서 스택을 활용하는 방법도 도입해 볼만함
public class Q11053 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int[] A = new int[len];
        for (int i = 0; i < len; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        int[] D = new int[len];
        for (int i = 0; i < len; i++) {
            D[i] = 1;
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i] && D[j] + 1 > D[i]) {
                    D[i] = D[j] + 1;
                }
            }
        }
        Arrays.sort(D);
        System.out.println(D[D.length - 1]);
    }
}
