import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q16922 {
    static int n;
    static int cnt = 0;
    static boolean[] visited = new boolean[4];
    static boolean[] priceCheck = new boolean[1001];
    static int[] rome = {1, 5, 10, 50};
    static int[] order;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        order = new int[n];
        go(0, 0);
        System.out.print(cnt);
    }

    static void go(int index, int start) {

        if (index == n) {
            int price = 0;
            for (int idx : order) {
                price += rome[idx];
            }
            if (!priceCheck[price]) {
                priceCheck[price] = true;
                cnt++;
            }
            return;
        }

        for (int i = start; i < 4; i++) {
            visited[i] = true;
            order[index] = i;
            go(index+1, i);
            visited[i] = false;
        }
    }
}
