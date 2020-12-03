import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 비트마스크를 활용

// 정수로 집합을 표현할 수 있는가를 묻는 문제
// 비트마스크 기법을 사용하면 정수로 집합을 표현할 수 있음
// 왜 집합을 표현할 때 배열이 아닌 비트마스크를 사용해야 하는지는 문제를 풀면서 정리

// 출력 시 스트링빌더 사용 권장

public class Q11723 {

    static int set = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());
        StringTokenizer st = null;
        while (M-- > 0) {
            st = new StringTokenizer(br.readLine(), " ");
            String operator = st.nextToken();
            int operand = 0;
            if (st.hasMoreTokens()) {
                operand = Integer.parseInt(st.nextToken());
            }
            switch (operator) {
                case "add" :
                    add(operand);
                    break;
                case "remove" :
                    remove(operand);
                    break;
                case "check" :
                    if (check(operand)) {
                        sb.append(1 + "\n");
                    }
                    else {
                        sb.append(0 + "\n");
                    }
                    break;
                case "toggle" :
                    toggle(operand);
                    break;
                case "all" :
                    set = (1<<21) - 1;
                    remove(0);
                    break;
                case "empty" :
                    set = 0;
                    break;
                default:
                    break;
            }
        }

        System.out.print(sb);
    }

    static boolean check(int num) {
        if ((set & (1<<num)) != 0) {
            return true;
        }
        else {
            return false;
        }
    }

    static void add(int num) {
        if (check(num)) {
            return;
        }
        set |= (1<<num);
    }

    static void remove(int num) {
        if (!check(num)) {
            return;
        }
        set &= ~(1<<num);
    }

    static void toggle(int num) {
        set ^= (1<<num);
    }
}
