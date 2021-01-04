import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16945 {
    static int[] arr = new int[9];
    static int[] order = new int[9];
    static boolean[] visited = new boolean[11];
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
        int[] rowSum = new int[3];
        int[] colSum = new int[3];
        int[] digSum = new int[2];
        for (int i = 0; i < 9; i++) {
            rowSum[i/3] += order[i];
            colSum[i%3] += order[i];
        }
        for (int i = 0; i < 3; i++) {
            digSum[0] += order[i*3 + i];
            digSum[1] += order[i*3 + 2-i];
        }

        int prevSum = rowSum[0];
        for (int i = 0; i < 3; i++) {
            if (rowSum[i] != prevSum || colSum[i] != prevSum) {
                return false;
            }
            if (i != 2) {
                if (digSum[i] != prevSum) {
                    return false;
                }
            }
        }
        return true;
    }
}
