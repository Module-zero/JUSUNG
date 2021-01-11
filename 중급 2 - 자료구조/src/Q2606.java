import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2606 {
    static int n, m;
    static int[] parent = new int[101];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        for (int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        for (int i = 0; i < m; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            union(x, y);
        }

        int cnt = 0;
        int root = find(1);
        for (int i = 2; i <= n; i++) {
            if (find(i) == root) {
                cnt++;
            }
        }

        System.out.print(cnt);
    }

    // k 의 루트를 리턴
    static int find(int k) {

        if (k == parent[k]) {
            return k;
        }

        int p = find(parent[k]);
        parent[k] = p;
        return p;
    }

    static void union(int x, int y) {
        x = find(x);
        y = find(y);
        parent[y] = x;
    }
}
