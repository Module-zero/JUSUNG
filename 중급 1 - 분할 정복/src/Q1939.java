import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q1939 {
    static int n, m, f1, f2;
    static ArrayList<ArrayList<int[]>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine()," ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int v1 = Integer.parseInt(st.nextToken());
            int v2 = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            graph.get(v1).add(new int[]{v2, e});
            graph.get(v2).add(new int[]{v1, e});
        }
        st = new StringTokenizer(br.readLine(), " ");
        f1 = Integer.parseInt(st.nextToken());
        f2 = Integer.parseInt(st.nextToken());

        int answer = Integer.MIN_VALUE;
        int left = 1;
        int right = 1000000000;
        while (left <= right) {
            int mid = (left + right) / 2;
            if (bfs(f1, mid)) {
                left = mid + 1;
                answer = Math.max(answer, mid);
            } else {
                right = mid - 1;
            }
        }
        System.out.print(answer);
    }

    static boolean bfs(int start, int weight) {
        LinkedList<Integer> q = new LinkedList<>();
        boolean[] check = new boolean[n+1];
        check[start] = true;
        q.add(start);
        while (!q.isEmpty()) {
            Integer p = q.poll();
            if (p == f2) {
                return true;
            }
            ArrayList<int[]> l = graph.get(p);
            for (int[] a : l) {
                if (!check[a[0]] && a[1] >= weight) {
                    check[a[0]] = true;
                    q.add(a[0]);
                }
            }
        }
        return false;
    }
}
