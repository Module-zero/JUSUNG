import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q1697 {

    static int n, k;
    static LinkedList<Integer> q = new LinkedList<>();
    static int[] visited = new int[100001];

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        bfs(n);
    }

    static void bfs(int n) {

        visited[n] = 1;
        q.add(n);

        while (!q.isEmpty()) {
            int p = q.poll();
            if (p == k) {
                System.out.print(visited[k] - 1);
                break;
            }
            go(p, p - 1);
            go(p, p + 1);
            go(p, p * 2);
        }
    }

    static void go(int p, int np) {
        if (np >= 0 && np <= 100000) {
            if (visited[np] == 0) {
                visited[np] = visited[p] + 1;
                q.add(np);
            }
        }
    }
}
