import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 주어진 N 번채널로 이동하기위해 버튼을 최소 몇 번 눌러야하는가
// -> 사용가능한 버튼을 조합한 수 중 N 번과 가장 가까운 수를 찾아야함
// -> 즉, 사용가능한 버튼을 조합한 모든 경우를 찾아야함 ==> '브루트 포스'

// 브루트 포스라 하여 재귀에 집착함. 꼭 그럴 필요는 없음
// 수 하나하나에 접근하는 경우 % 연산이 용이함

public class Q1107 {

    static int N;   // 이동하려는 채널
    static int M;   // 고장난 버튼의 개수
    static boolean[] nums = new boolean[10];
    static int answer;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        if (M != 0) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for (int i = 0; i < M; i++) {
                int brokenNum = Integer.parseInt(st.nextToken());
                nums[brokenNum] = true;
            }
        }

        // 100 번에서 +, - 버튼으로만 이동할 수 있는 경우를 기본값으로 설정
        answer = Math.abs(100 - N);

        for (int i = 0; i < 1000000; i++) {
            if (check(i)) {
                int press = String.valueOf(i).length() + Math.abs(i - N);
                if (answer > press) {
                    answer = press;
                }
            }
        }
        System.out.print(answer);
    }

    // c 가 사용가능한 버튼으로 조합할 수 있는 수인지를 검사
    static boolean check(int c) {

        // c 가 0 인 경우는 예외처리
        if (c == 0) {
            if (nums[0]) {
                return false;
            }
            else {
                return true;
            }
        }

        while (c > 0) {
            if (nums[c % 10]) {
                return false;
            }
            else {
                c /= 10;
            }
        }
        return true;
    }
}
