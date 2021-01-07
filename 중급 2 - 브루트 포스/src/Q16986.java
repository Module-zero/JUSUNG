import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16986 {
    static int n, k;
    static int[][] arr = new int[10][10];
    static boolean[] check = new boolean[10];
    static int[][] player = new int[3][20];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 1; j <= n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < 20; i++) {
            player[1][i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine()," ");
        for (int i = 0; i < 20; i++) {
            player[2][i] = Integer.parseInt(st.nextToken());
        }

        if (go(0)) {
            System.out.print(1);
        } else {
            System.out.print(0);
        }
    }

    static boolean go(int index) {

        if (index == n) {
            return isWin();
        }

        for (int i = 1; i <= n; i++) {
            if (!check[i]) {
                check[i] = true;
                player[0][index] = i;
                if (go(index+1)) {
                    return true;
                }
                check[i] = false;
            }
        }
        return false;
    }

    static boolean isWin() {
        int[] score = new int[3];
        int[] pos = new int[3];
        int p1 = 0, p2 = 1;

        while ((score[0] != k && score[1] != k && score[2] != k) && pos[0] < n) {
            int[] result = fight(p1, p2, pos);
            int win = result[0], lose = result[1];
            score[win]++;
            pos[win]++;
            pos[lose]++;
            p1 = win;
            p2 = 3-(win+lose);
        }

        return (score[0] == k);
    }

    static int[] fight(int p1, int p2, int[] pos) {
        int[] result = {p1, p2};
        int m1 = player[p1][pos[p1]], m2 = player[p2][pos[p2]];
        if (arr[m1][m2] == 0) {
            result[0] = p2;
            result[1] = p1;
        } else if (arr[m1][m2] == 1) {
            if (p1 < p2) {
                result[0] = p2;
                result[1] = p1;
            }
        }
        return result;
    }
}
