import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 접근법 :
// 11053번 문제를 응용
// 우선 '가장 긴 길이를 가진 부분수열의 마지막 숫자' 에 대한 정보를 구한 뒤,
// 해당 정보를 통해 수열을 거꾸로 거슬러 올라가면서 부분 수열을 하나씩 완성해나감
// 이 때 거슬러 올라가기 위한 정보로서 '부분 수열에서 임의의 숫자의 바로 전에 위치한 숫자' 를 활용하였음

// 개선사항 :
// 수열을 구하는 과정에서 지저분한 방식으로 구하려고 했음
// 그러나 재귀를 활용하면 깔끔하게 구해진다
// '가장 긴 길이를 가진 부분수열의 마지막 숫자' 를 활용하여 해당 부분수열을 구해야하기 때문에
// 거꾸로 거슬러 올라가는 과정이 진행된다. 이러한 경우에는 스택 또는 재귀를 사용하는 것이 깔끔한 것 같다
public class Q14002 {

    public static StringBuilder answer = new StringBuilder();
    public static int[] A;
    public static int[] D;
    public static int[] V;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int len = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        A = new int[len];
        for (int i = 0; i < len; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        D = new int[len];
        V = new int[len];
        for (int i = 0; i < len; i++) {
            D[i] = 1;
            V[i] = -1;
            for (int j = 0; j < i; j++) {
                if (A[j] < A[i] && D[j] + 1 > D[i]) {
                    D[i] = D[j] + 1;
                    V[i] = j;
                }
            }
        }

        int maxIdx = 0;
        for (int i = 1; i < D.length; i++) {
            if (D[maxIdx] < D[i]) {
                maxIdx = i;
            }
        }

        System.out.println(D[maxIdx]);
        go(maxIdx);

        answer.deleteCharAt(answer.length() - 1);
        System.out.print(answer);
    }

    public static void go(int p) {
        if (p == -1) {
            return;
        }
        go(V[p]);
        answer.append(A[p] + " ");
    }
}
