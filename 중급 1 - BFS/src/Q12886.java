import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q12886 {
    static LinkedList<int[]> q = new LinkedList<>();
    static boolean[][] visited = new boolean[501][501];
    static boolean flag = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int[] start = new int[3];
        start[0] = Integer.parseInt(st.nextToken());
        start[1] = Integer.parseInt(st.nextToken());
        start[2] = Integer.parseInt(st.nextToken());

        if ((start[0] + start[1] + start[2]) % 3 != 0) {
           System.out.print(0);
           return;
        }

        Arrays.sort(start);
        q.add(start);
        visited[start[0]][start[1]] = true;
        while (!q.isEmpty()) {
            int[] p = q.poll();
            if (p[0] == p[1] && p[1] == p[2]) {
                flag = true;
                break;
            }

            for (int i = 0; i < 3; i++) {
                int idx1 = (i+2)%3;
                int idx2 = (i+1)%3;

                // 크기가 같으면 안됨
                if (p[idx1] == p[idx2]) {
                    continue;
                }

                int[] np = new int[3];
                np[i] = p[i];
                if (p[idx1] < p[idx2]) {
                    np[idx2] = p[idx2] - p[idx1];
                    np[idx1] = p[idx1] * 2;
                } else {
                    np[idx1] = p[idx1] - p[idx2];
                    np[idx2] = p[idx2] * 2;
                }

                Arrays.sort(np);
                if (!visited[np[0]][np[1]]) {
                    visited[np[0]][np[1]] = true;
                    q.add(np);
                }
            }
        }

        if (flag) {
            System.out.print(1);
        } else {
            System.out.print(0);
        }
    }
}
