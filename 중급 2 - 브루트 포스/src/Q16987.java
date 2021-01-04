import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q16987 {
    static int n;
    static int[][] eggs = new int[8][2];
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs[i][0] = s;
            eggs[i][1] = w;
        }

        go(0);
        System.out.print(max);
    }

    static void go(int index) {

        if (index == n) {
            int cnt = 0;
            for (int i = 0; i < n; i++) {
                if (eggs[i][0] <= 0) {
                    cnt++;
                }
            }
            max = Math.max(max, cnt);
            return;
        }

        // 손에 든 계란이 깨진 경우
        if (eggs[index][0] <= 0) {
            go(index+1);
            return;
        }

        // 깰 계란이 있는지 여부
        boolean isBreak = false;

        // 다른 계란 중 하나를 깸
        for (int i = 0; i < n; i++) {
            if (i != index && eggs[i][0] > 0) {
                isBreak = true;
                dispose(index, i, "hit");
                go(index + 1);
                dispose(index, i, "restore");
            }
        }

        // 깰 계란이 없음
        if (!isBreak) {
            go(index+1);
        }
    }

    // 계란을 깨거나 복구
    static void dispose(int idx1, int idx2, String type) {
        int s1 = eggs[idx1][0];
        int w1 = eggs[idx1][1];
        int s2 = eggs[idx2][0];
        int w2 = eggs[idx2][1];
        eggs[idx1][0] = type.equals("hit") ? s1-w2 : s1+w2;
        eggs[idx2][0] = type.equals("hit") ? s2-w1 : s2+w1;
    }
}
