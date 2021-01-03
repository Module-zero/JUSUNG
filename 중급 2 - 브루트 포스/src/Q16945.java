import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16945 {
    static int[] arr = new int[9];
    static boolean[] visited = new boolean[11];
    static int[] order = new int[9];
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int idx = 0;
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                arr[idx++] = Integer.parseInt(st.nextToken());
            }
        }
        go(0, 0);
        System.out.print(min);
    }

    static void go(int index, int sum) {

        if (index == 9) {
            if (isMagicSquare(order)) {
                int cost = 0;
                for (int i = 0; i < 9; i++) {
                    if (arr[i] != order[i]) {
                       cost += Math.abs(arr[i]-order[i]);
                    }
                }
                min = Math.min(min, cost);
            }
            return;
        }

        for (int i = 1; i <= 9; i++) {
            if (!visited[i]) {
                visited[i] = true;
                order[index] = i;
                go(index+1, sum+i);
                visited[i] = false;
            }
        }
    }

    static boolean isMagicSquare(int[] order) {
        int commonSum = order[0]+order[1]+order[2];
        int rowSum2 = order[3]+order[4]+order[5];
        int rowSum3 = order[6]+order[7]+order[8];
        if (rowSum2 == commonSum && rowSum3 == commonSum) {
            int colSum1 = order[0]+order[3]+order[6];
            int colSum2 = order[1]+order[4]+order[7];
            int colSum3 = order[2]+order[5]+order[8];
            if (colSum1 == commonSum && colSum2 == commonSum && colSum3 == commonSum) {
                int digSum1 = order[0]+order[4]+order[8];
                int digSum2 = order[2]+order[4]+order[6];
                return digSum1 == commonSum && digSum2 == commonSum;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }
}
