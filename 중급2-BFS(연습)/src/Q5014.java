import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q5014 {

    static int F, S, G, U, D;
    static int[] dx = new int[2];
    static LinkedList<Integer> queue = new LinkedList<>();
    static int[] visited;
    static int answer = -1;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        F = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        G = Integer.parseInt(st.nextToken());
        U = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        dx[0] = U;
        dx[1] = -D;
        visited = new int[F+1];
        bfs(S);
        if (answer == -1) {
            System.out.print("use the stairs");
        }
        else {
            System.out.print(answer);
        }
    }

    static void bfs(int x) {
        queue.add(x);
        visited[x] = 1;
        while (!queue.isEmpty()) {
            x = queue.poll();
            if (x == G) {
                answer = visited[x] - 1;
                return;
            }
            for (int i = 0; i < dx.length; i++) {
                int nx = x + dx[i];
                if (nx <= 0 || nx > F) {
                    continue;
                }
                if (visited[nx] == 0) {
                    queue.add(nx);
                    visited[nx] = visited[x] + 1;
                }
            }
        }
    }
}
