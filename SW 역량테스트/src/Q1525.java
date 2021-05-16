import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Q1525 {
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 수의 위치 상태를 하나의 정수로 표현한다.
        // 이 때 0 이 맨 앞에 오면 올바르지 않은 수가 되므로 0 대신 9 를 사용해야함.
        int startNum = 0;
        for (int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < 3; j++) {
                int x = Integer.parseInt(st.nextToken());
                if (x == 0) {
                    x = 9;
                }
                startNum = startNum * 10 + x;
            }
        }

        // 2. 표현된 정수가 bfs 과정을 거쳐서 123456789 로 바뀔 수 있는지를 알아보면됨
        Queue<Integer> q = new LinkedList<>();
        Map<Integer, Integer> visited = new HashMap<>();
        q.add(startNum);
        visited.put(startNum, 0);
        while (!q.isEmpty()) {
            int nowNum = q.poll();

            // 3. 123456789 로 바뀌는 경우 정답 출력
            if (nowNum == 123456789) {
                System.out.print(visited.get(nowNum));
                return;
            }

            String nowStr = Integer.toString(nowNum);
            int idx = nowStr.indexOf('9');
            int x = idx / 3;
            int y = idx % 3;

            for (int i = 0 ; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx >= 0 && ny >= 0 && nx < 3 && ny < 3) {
                    int swapIdx = nx * 3 + ny;
                    StringBuilder newStr = new StringBuilder(nowStr);
                    newStr.setCharAt(idx, nowStr.charAt(swapIdx));
                    newStr.setCharAt(swapIdx, '9');
                    int newNum = Integer.parseInt(newStr.toString());

                    if (!visited.containsKey(newNum)) {
                        q.add(newNum);
                        visited.put(newNum, visited.get(nowNum) + 1);
                    }
                }
            }
        }

        // 4. 123456789 로 바뀌지 않는 경우 -1 출력
        System.out.print(-1);
    }
}
