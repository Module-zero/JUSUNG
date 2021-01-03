import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q16987 {
    static int n;
    static ArrayList<int[]> eggs = new ArrayList<>();
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            eggs.add(new int[]{s, w});
        }

        go(0);
        System.out.print(max);
    }

    static void go(int index) {

        if (index == n) {
            int cnt = 0;
            for (int[] p : eggs) {
                if (p[0] <= 0) {
                    cnt++;
                }
            }
            max = Math.max(max, cnt);
            return;
        }

        // 손에 든 계란이 깨진 경우
        if (eggs.get(index)[0] <= 0) {
            go(index+1);
            return;
        }

        // 깰 계란이 있는지 여부
        boolean ok = false;

        // 다른 계란 중 하나를 깸
        for (int i = 0; i < n; i++) {
            if (i != index && eggs.get(i)[0] > 0) {
                ok = true;
                hit(index, i);
                go(index + 1);
                restore(index, i);
            }
        }

        // 깰 계란이 없음
        if (!ok) {
            go(index+1);
        }
    }

    static void hit(int idx1, int idx2) {
        int s1 = eggs.get(idx1)[0];
        int w1 = eggs.get(idx1)[1];
        int s2 = eggs.get(idx2)[0];
        int w2 = eggs.get(idx2)[1];
        eggs.set(idx1, new int[]{s1-w2, w1});
        eggs.set(idx2, new int[]{s2-w1, w2});
    }

    static void restore(int idx1, int idx2) {
        int s1 = eggs.get(idx1)[0];
        int w1 = eggs.get(idx1)[1];
        int s2 = eggs.get(idx2)[0];
        int w2 = eggs.get(idx2)[1];
        eggs.set(idx1, new int[]{s1+w2, w1});
        eggs.set(idx2, new int[]{s2+w1, w2});
    }
}
