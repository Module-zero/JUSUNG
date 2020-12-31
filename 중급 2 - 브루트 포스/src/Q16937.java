import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q16937 {
    static int H, W, N;
    static ArrayList<int[]> sticker = new ArrayList<>();
    static boolean[] check = new boolean[101];
    static int[] selected = new int[2];
    static int max = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        H = Integer.parseInt(st.nextToken());
        W = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int h = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            sticker.add(new int[]{h, w});
        }

        go(0, 0);
        System.out.print(max);
    }

    static void go(int index, int start) {

        if (index == 2) {
            int[] p1 = sticker.get(selected[0]);
            int[] p2 = sticker.get(selected[1]);
            int h1 = p1[0], w1 = p1[1];
            int h2 = p2[0], w2 = p2[1];

            // h1*w1 모양의 스티커가 붙여질 때
            if (H >= h1 && W >= w1) {
                int[][] d = {{H-h1, W}, {H, W-w1}};
                for (int i = 0; i < 2; i++) {
                    int dh = d[i][0];
                    int dw = d[i][1];
                    if ((dh >= h2 && dw >= w2) || (dh >= w2 && dw >= h2)) {
                        int area = p1[0]*p1[1] + p2[0]*p2[1];
                        max = Math.max(max, area);
                        return;
                    }
                }
            }

            // w1*h1 모양의 스티커가 붙여질 때
            if (H >= w1 && W >= h1) {
                int[][] d = {{H-w1, W}, {H, W-h1}};
                for (int i = 0; i < 2; i++) {
                    int dh = d[i][0];
                    int dw = d[i][1];
                    if ((dh >= h2 && dw >= w2) || (dh >= w2 && dw >= h2)) {
                        int area = p1[0]*p1[1] + p2[0]*p2[1];
                        max = Math.max(max, area);
                        return;
                    }
                }
            }
            return;
        }

        for (int i = start; i < N; i++) {
            if (!check[i]) {
                check[i] = true;
                selected[index] = i;
                go(index+1, i+1);
                check[i] = false;
            }
        }
    }
}