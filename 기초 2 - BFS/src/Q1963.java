import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q1963 {
    static int t;
    static boolean[] check = new boolean[10000];
    static int[] dx = {1, 10, 100, 1000, 10000};

    static void prime() {
        for (int i = 2; i <= 9999; i++) {
            if (!check[i]) {
                // 소수가 아닌 인덱스는 true 표시
                for (int j = i*2; j <= 9999; j += i) {
                    check[j] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        t = Integer.parseInt(br.readLine());
        // 에라토스테네스의 체 생성
        prime();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());

            LinkedList<Integer> q = new LinkedList<>();
            int[] visited = new int[10000];
            visited[n1] = 1;
            q.add(n1);
            while (!q.isEmpty()) {
                int p = q.poll();
                if (p == n2) {
                    System.out.println(visited[n2]-1);
                    break;
                }

                int sub = 0;
                for (int i = 1; i <= 4; i++) {
                    int val = p;
                    int t = p % dx[i];
                    val -= (t - sub);
                    sub = t;

                    int start = i == 4 ? 1 : 0;
                    for (int j = start; j < 10; j++) {
                        int num = val + dx[i-1]*j;
                        // num 이 소수면 큐에 삽입
                        if (!check[num] && visited[num] == 0) {
                            visited[num] = visited[p] + 1;
                            q.add(num);
                        }
                    }
                }
            }
        }
    }
}
