import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q9663 {

    static int N;
    static int count = 0;

    // 배열의 인덱스가 체스판의 행, 원소가 해당 행에 있는 퀸의 위치
    static int[] check;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        check = new int[N];
        for (int i = 0; i < N; i++) {
            check[i] = -1;
        }

        go(0);
        System.out.print(count);
    }

    static void go(int index) {

        if (index == N) {
            count++;
            return;
        }

        for (int i = 0; i < N; i++) {
            // index 행의 i 번째 위치에 퀸을 놓을 수 있는지 체크
            if (possible(index, i) == false) { continue; }
            check[index] = i;
            go(index+1);
            check[index] = -1;
        }
    }

    static boolean possible(int index, int position) {

        // 0 ~ index - 1 행까지 각 행의 퀸의 위치에 따라
        // 현재행(index)의 퀸의 위치를 결정
        for (int i = 0; i < index; i++) {
            // 퀸과 같은 열에 있는 경우
            if (check[i] == position) { return false; }
            // 퀸의 오른쪽 아래 대각선에 있는 경우
            if (check[i]+(index-i) == position) { return false; }
            // 퀸의 왼쪽 아래 대각선에 있는 경우
            if (check[i]-(index-i) == position) { return false; }
        }

        return true;
    }
}