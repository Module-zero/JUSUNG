import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 연산자 수열의 경우의 수를 구하는 문제이지만
// 그렇게 풀어서는 시간초과로 답을 구할 없음
// 재귀를 활용하되 다른 방법을 사용해야함

// 재귀를 사용할 때 가능하면 배열같은 저장공간에 답을 저장하기 보다는
// 재귀를 진행하면서 정답을 조금씩 완성해나가는 방식이 메모리 절약에 도움이 됨

// 항상 순서도를 거의 완벽하게 그린 뒤 코딩하는 것이 좋음

public class Q15658 {

    static int N;
    static int[] A;
    static int max = Integer.MIN_VALUE;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        int plus = Integer.parseInt(st.nextToken());
        int sub = Integer.parseInt(st.nextToken());
        int mult = Integer.parseInt(st.nextToken());
        int div = Integer.parseInt(st.nextToken());

        go(1, A[0], plus, sub, mult, div);

        System.out.println(max);
        System.out.print(min);
    }

    static void go(int index, int sum, int plus, int sub, int mult, int div) {

        if (index == N) {
            if (max < sum) {
                max = sum;
            }
            if (min > sum) {
                min = sum;
            }
            return;
        }

        if (plus > 0) {
            go(index + 1, sum + A[index], plus-1, sub, mult, div);
        }
        if (sub > 0) {
            go(index + 1, sum - A[index], plus, sub-1, mult, div);
        }
        if (mult > 0) {
            go(index + 1, sum * A[index], plus, sub, mult-1, div);
        }
        if (div > 0) {
            go(index + 1, sum / A[index], plus, sub, mult, div-1);
        }
    }
}
