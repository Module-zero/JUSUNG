import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 재귀를 활용한 브루트 포스

// n개의 숫자중에서 m개의 숫자를 순서없이 뽑는 경우
// 결국 중복없이 오름차순으로 뽑는 경우와 같으므로 재귀를 활용하여 해결가능함
public class Q6603 {

    static int[] answers;
    static int[] nums;
    static int k;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            k = Integer.parseInt(st.nextToken());
            if (k == 0) {
                break;
            }
            answers = new int[6];
            nums = new int[k];
            for (int i = 0; i < k; i++) {
                nums[i] = Integer.parseInt(st.nextToken());
            }

            go(0, 0);
            System.out.println();
        }
    }

    static void go(int index, int start) {

        if (index == 6) {
            for (int i = 0; i < 6; i++) {
                System.out.print(answers[i] + " ");
            } System.out.println();
            return;
        }

        for (int i = start; i < k; i++) {
            answers[index] = nums[i];
            go(index+1, i+1);
        }
    }
}
