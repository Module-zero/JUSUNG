import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

// 재귀를 활용한 백트래킹

// 브루트포스로 풀어도 답은 나온다
// 그러나 정답 문자열을 만들어나가는 과정에서
// 부등호에 맞지 않는 수는 애초에 탈락시켜버림으로서
// 시간을 훨씬 줄일 수 있다
public class Q2529 {

    static int k;
    static char[] ieq;
    static boolean[] check;
    static ArrayList<String> list;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        k = Integer.parseInt(br.readLine());
        ieq = new char[k];
        check = new boolean[10];
        list = new ArrayList<>();

        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < k; i++) {
            ieq[i] = st.nextToken().charAt(0);
        }

        go(0, "");

        System.out.println(list.get(list.size()-1));
        System.out.println(list.get(0));
    }

    static void go(int index, String num) {

        if (index == k + 1) {
            list.add(num);
            return;
        }

        for (int i = 0; i <= 9; i++) {
            if (check[i] == true) {
                continue;
            }
            // index 가 0 일때는 부등호를 신경쓰지 않아도 됨
            if (index == 0) {
                check[i] = true;
                go(index + 1, num + Integer.toString(i));
                check[i] = false;
            }
            else {
                // index > 0 일 때부터는 부등호 검사를 하면서
                // 부등호가 맞지 않는 수는 아예 이어붙이지않음
                if (good(ieq[index-1], num.charAt(num.length()-1), Integer.toString(i).charAt(0))) {
                    check[i] = true;
                    go(index + 1, num + Integer.toString(i));
                    check[i] = false;
                }
            }
        }
    }

    static boolean good(char ieq, char a, char b) {
        if (ieq == '<') {
            if (a > b) {
               return false;
            }
        }
        else {
            if (a < b) {
                return false;
            }
        }
        return true;
    }
}
