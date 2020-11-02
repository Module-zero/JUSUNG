import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Q14888 {

    static int N;
    static int[] A;
    static int[] operators1 = new int[4];
    static ArrayList<Integer> operators2 = new ArrayList<>();
    static int[] operators3 = null;
    static boolean[] check = null;
    static boolean zeroDivideFlag = false;
    static ArrayList<Integer> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N];
        operators3 = new int[N-1];
        check = new boolean[N-1];
        StringTokenizer st = null;

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < 4; i++) {
            operators1[i] = Integer.parseInt(st.nextToken());
            switch (i) {
                case 0:
                    while (operators1[i]-- > 0) {
                        operators2.add(0);
                    }
                    break;
                case 1:
                    while (operators1[i]-- > 0) {
                        operators2.add(1);
                    }
                    break;
                case 2:
                    while (operators1[i]-- > 0) {
                        operators2.add(2);
                    }
                    break;
                case 3:
                    while (operators1[i]-- > 0) {
                        operators2.add(3);
                    }
                    break;
                default:
                    break;
            }
        }

        go(0);

        Collections.sort(answers);
        System.out.println(answers.get(answers.size()-1));
        System.out.print(answers.get(0));
    }

    static void go(int index) {

        if (index == N-1) {

            // 연산 진행
            int val = calculate();
            if (zeroDivideFlag == false) {
                answers.add(val);
            }
            else {
                zeroDivideFlag = false;
            }
            return;
        }

        for (int i = 0; i < N-1; i++) {
            if (check[i] == true) {
                continue;
            }
            check[i] = true;
            operators3[index] = operators2.get(i);
            go(index + 1);
            check[i] = false;
        }
    }

    static int calculate() {
        int[] B = A.clone();
        for (int i = 0; i < N-1; i++) {
            switch (operators3[i]) {
                case 0:
                    B[i+1] += B[i];
                    break;
                case 1:
                    B[i+1] = B[i] - B[i+1];
                    break;
                case 2:
                    B[i+1] *= B[i];
                    break;
                case 3:
                    if (B[i+1] != 0) {
                        B[i+1] = B[i] / B[i+1];
                    }
                    else {
                        zeroDivideFlag = true;
                    }
                    break;
                default:
                    break;
            }
        }
        return B[N-1];
    }
}
