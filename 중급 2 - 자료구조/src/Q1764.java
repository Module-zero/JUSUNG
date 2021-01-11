import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeMap;

public class Q1764 {
    static int n, m;
    static TreeMap<String, Integer> map = new TreeMap<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for (int i = 0; i < n; i++) {
            map.put(br.readLine(), 1);
        }
        for (int i = 0; i < m; i++) {
            String name = br.readLine();
            if (map.containsKey(name)) {
                map.put(name, 2);
            }
        }

        int cnt = 0;
        StringBuilder sb = new StringBuilder("\n");
        Set<String> keys = map.keySet();
        for (String key : keys) {
            int v = map.get(key);
            if (v == 2) {
                cnt++;
                sb.append(key).append("\n");
            }
        }
        sb.insert(0, cnt);
        System.out.print(sb);
    }
}
