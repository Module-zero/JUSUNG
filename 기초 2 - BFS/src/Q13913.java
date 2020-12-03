import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q13913 {

    static int n, k;
    static LinkedList<Integer> q = new LinkedList<>();
    static int[] visited = new int[100001];
    static int[] parent =  new int[100001];
    static int depth;
    static int[] answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        bfs(n);
    }

    static void bfs(int n) {

        visited[n] = 1;
        parent[n] = -1;
        q.add(n);

        while (!q.isEmpty()) {
            int p = q.poll();
            if (p == k) {
                depth = visited[k] -1;
                System.out.println(depth);

                answer = new int[depth + 1];
                sort(k);
                StringBuilder sb = new StringBuilder();
                for (int i : answer) {
                    sb.append(i + " ");
                }
                System.out.print(sb);
                break;
            }
            go(p, p - 1);
            go(p, p + 1);
            go(p, p * 2);
        }
    }

    static void sort(int k) {
        if (k == -1) {
            return;
        }

        answer[depth--] = k;
        sort(parent[k]);
    }

    static void go(int p, int np) {
        if (np >= 0 && np <= 100000) {
            if (visited[np] == 0) {
                visited[np] = visited[p] + 1;
                parent[np] = p;
                q.add(np);
            }
        }
    }
}
