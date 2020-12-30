import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Q17089 {
    static int n, m;
    static ArrayList<ArrayList<Integer>> graph = new ArrayList<>();
    static boolean[] visited = new boolean[4001];
    static int[] friends = new int[3];
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }

        for (int i = 1; i <= n; i++) {
            dfs(0, i, i);
        }

        if (min == Integer.MAX_VALUE) {
            System.out.print(-1);
        } else {
            System.out.print(min);
        }
    }

    static void dfs(int depth, int x, int start) {

        if (depth == 3) {
            // 깊이가 3 인 사이클 존재
            if (x == start) {
                int cnt = 0;
                for (int f : friends) {
                    cnt += graph.get(f).size();
                }
                min = Math.min(min, cnt-6);
            }
            return;
        }

        visited[x] = true;
        friends[depth] = x;
        ArrayList<Integer> l = graph.get(x);
        for (Integer i : l) {
            if (!visited[i] || (depth == 2 && i == start)) {
                dfs(depth + 1, i, start);
                visited[i] = false;
            }
        }
    }
}
