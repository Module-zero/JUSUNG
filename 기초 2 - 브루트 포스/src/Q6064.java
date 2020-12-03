import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// <M:N>이 카잉 달력의 마지막 해라고 하면 <x:y>는 몇 번째 해를 나타내는가
// -> 모든 경우를 전부 해보는 경우에는 시간초과가 나옴
// -> 따라서 x를 고정시켜놓고 적절한 y를 구하는 아이디어가 필요함
// 물론 이 과정 역시 브루트포스로 진행함
public class Q6064 {

    static int T, M, N, x, y, answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            M = Integer.parseInt(st.nextToken());
            N = Integer.parseInt(st.nextToken());
            x = Integer.parseInt(st.nextToken());
            y = Integer.parseInt(st.nextToken());
            answer = -1;

            int i = 0;
            while (true) {
                int v = M * (i++) + x;
                // M, N의 최소공배수 이후부터는 반복됨
                if (v > lcm(M, N)) {
                    break;
                }
                int tmpY = (v % N == 0) ? (N) : (v % N);
                if (tmpY == y) {
                    answer = v;
                    break;
                }
            }
            System.out.println(answer);
        }
    }

    static int lcm(int x, int y) {
       return (x * y) / gcd(x, y);
    }

    static int gcd(int x, int y) {
        if (y == 0) {
            return x;
        }
        return gcd(y, x % y);
    }
}
