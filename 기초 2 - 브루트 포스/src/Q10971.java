import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 재귀를 활용한 브루트 포스를 사용함

// 재귀의 특성에 대한 감을 잡는 것이 좋다
// 만약 재귀함수를 호출하기 전에 함수 내부에 영향을 주는 어떤 동작을 취한다면
// 해당 동작은 재귀호출되는 모든 함수들에 영향을 준다

// 주어진 N의 범위가 2~10 이고 최악의 경우는 O(N*N!) 이므로
// 모든 경우의 수를 구해도 문제가 없음
public class Q10971 {

    static int n;
    static int min = Integer.MAX_VALUE;
    static int[][] cities;
    static int[] route;
    static boolean[] check;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        cities = new int[n][n];
        route = new int[n];
        check = new boolean[n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < n; j++) {
                cities[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        go(0);

        System.out.print(min);
    }

    static void go(int index) {

        if (index == n) {
            int cost = 0;

            for (int i = 0; i < n-1; i++) {

                // i에서 i+1로 가는 비용
                int oneRouteCost = cities[route[i]][route[i+1]];

                // 길이 없을 경우 cost 계산을 하지 않음
                if (oneRouteCost == 0) {
                    return;
                }

                // i에서 i+1로 길이 존재할 경우 cost 에 추가
                cost += oneRouteCost;
            }

            // n-1 도시에서 처음 출발한 도시로 길이 존재할 경우 cost 에 추가
            if (cities[route[n-1]][route[0]] != 0) {
                cost += cities[route[n-1]][route[0]];
            }
            else {
               return;
            }

            if (min > cost) {
                min = cost;
            }

            return;
        }

        for (int i = 0; i < n; i++) {

            // 0 1 2 3
            // = 3 0 1 2
            // = 2 3 0 1
            // = 1 2 3 0
            // 순환하는 경우이므로 모두 같은 케이스임
            // 따라서 0 에서 시작하는 경우만 구해도 답을 구할 수 있음
            if (index == 0 && i == 1) {
                return;
            }

            if (check[i] == true) {
                continue;
            }
            route[index] = i;
            check[i] = true;
            go(index+1);
            check[i] = false;
        }
    }
}
