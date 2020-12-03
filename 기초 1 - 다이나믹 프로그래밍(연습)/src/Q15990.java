import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 해당 문제에서 내가 보완해야될 부분은 다음과 같다.
// 1. 나는 매 케이스 수마다 새로운 d배열을 생성하려고 했다. 그럴 필요없이 문제에서 주어지는 n의 한계값에 해당하는 d배열을 최초에 한번만 만들고
// 이후에 그 배열에서 매 케이스 수에 해당하는 정답을 찾아내면된다. 한계값을 이용하는 것을 두려워할 필요가 없다.
// 2. 문제에서는 방법의 수를 구한 뒤 해당 수만 1000000009로 나눈 값을 출력하면 된다고 말한다. 그러나 d배열을 채우는 과정에서도
// 1000000009로 나눈 값을 배열에 채워넣는다. 아마 수가 커지면 long의 최대값을 초과하게 되어서 그런것 같다. 이런것은 문제에 명시되어 있지 않기때문에
// 센스있게 알아서 해결해야 되는 것 같다. 그런데 솔직히 그 원리까지는 모르겠다.
// (왜 1000000009로 나눈 나머지를 배열에 채워넣어도 정답이 나오는 것인가?? 1000000009가 어떤 의미있는 수인가??)


public class Q15990 {

    public static void main(String[] args) throws IOException {

        int limit = 100000;
        int mod = 1000000009;
        long [][]d = new long[limit + 1][4];
        d[1][1] = d[2][2] = d[3][3] = d[3][1] = d[3][2] = 1;
        for (int i = 4; i <= limit; i++) {
            d[i][1] = (d[i - 1][2] + d[i - 1][3]) % mod;
            d[i][2] = (d[i - 2][1] + d[i - 2][3]) % mod;
            d[i][3] = (d[i - 3][1] + d[i - 3][2]) % mod;
        }

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        while (count-- > 0) {
            int n = Integer.parseInt(br.readLine());
            long tmp = d[n][1] + d[n][2] + d[n][3];
            long answer = tmp % mod;
            System.out.println(answer);
        }
    }
}
