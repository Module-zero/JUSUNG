import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 가능한 모든 연산자의 조합을 구하는 문제

// 재귀를 사용한 브루트 포스
// 모든 조합의 경우의 수를 구하는 문제이므로

// 훨씬 빠른 방법이 존재함
// 재귀를 돌면서 조금씩 합을 구하는 방식이 있음
// 변수 이름을 훨씬 직관적으로 지어야함
public class Q14888 {

    static int N;
    static int[] A;

    // 각 연산자의 개수를 저장
    static int[] nOperators = new int[4];

    // 연산자들을 0,1,2,3으로 저장
    static ArrayList<Integer> operators = new ArrayList<>();

    // 연산자 조합의 하나의 케이스를 저장
    static int[] oneCase;
    static boolean[] check;

    // 정답 후보(최대값, 최소값)들을 저장
    static ArrayList<Integer> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        check = new boolean[N-1];
        oneCase = new int[N-1];

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            nOperators[i] = Integer.parseInt(st.nextToken());
            while (nOperators[i]-- > 0) {
                operators.add(i);
            }
        }

        go(0);

        Collections.sort(answers);
        System.out.println(answers.get(answers.size()-1));
        System.out.print(answers.get(0));
    }

    static void go(int index) {

        if (index == N-1) {
            // 도출된 하나의 연산자 조합 케이스를 가지고 연산 진행
            int val = calculate();
            answers.add(val);
            return;
        }

        for (int i = 0; i < N-1; i++) {
            if (check[i] == true) { continue; }
            check[i] = true;
            oneCase[index] = operators.get(i);
            go(index + 1);
            check[i] = false;
        }
    }

    static int calculate() {
        int[] B = A.clone();
        for (int i = 0; i < N-1; i++) {
            switch (oneCase[i]) {
                // '+'
                case 0:
                    B[i+1] += B[i];
                    break;
                // '-'
                case 1:
                    B[i+1] = B[i] - B[i+1];
                    break;
                // '*'
                case 2:
                    B[i+1] *= B[i];
                    break;
                // '/'
                case 3:
                    B[i+1] = B[i] / B[i+1];
                    break;
                default:
                    break;
            }
        }
        return B[N-1];
    }
}
