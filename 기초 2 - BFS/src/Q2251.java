import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Q2251 {

    static int bottle[] = new int[3];
    static int A, B, C;
    static LinkedList<int[]> queue = new LinkedList<>();
    static int[][] visited = new int[201][201];
    static ArrayList<Integer> answers = new ArrayList<>();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        bottle[0] = A;
        bottle[1] = B;
        bottle[2] = C;

        queue.add(new int[]{0, 0, C});
        visited[0][0] = 1;
        while (!queue.isEmpty()) {
            int[] popB = queue.poll();
            if (popB[0] == 0) {
                answers.add(popB[2]);
            }
            for (int i = 0; i < popB.length; i++) {
                int b1 = (i + 1) % 3;
                int b2 = (i + 2) % 3;
                pour(i, b1, popB.clone());
                pour(i, b2, popB.clone());
            }
        }

        Collections.sort(answers);
        for (int i : answers) {
            System.out.print(i + " ");
        }
    }

    // 인덱스 b1의 물을 인덱스 b2로 부음
    static void pour(int b1, int b2, int[] popB) {

        // 컵에 물이 들어있지 않으면 물을 붓지 않음
        if (popB[b1] == 0) { return; }

        // 두 컵 모두 가득 차 있으면 진행 X
        if ((popB[b1] != bottle[b1]) || (popB[b2] != bottle[b2])) {
            popB[b2] += popB[b1];
            popB[b1] = 0;

            // 물이 넘친 경우 양 조절
            if (popB[b2] > bottle[b2]) {
                popB[b1] = popB[b2] - bottle[b2];
                popB[b2] = bottle[b2];
            }

            if (visited[popB[0]][popB[1]] != 1) {
                queue.add(popB);
                visited[popB[0]][popB[1]] = 1;
            }
        }
    }
}
