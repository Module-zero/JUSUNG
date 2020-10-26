import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

// 접근법 :
// 브루트 포스

// 이유 :
// n = 9, O(n*n) 으로 해결 가능
public class Q2309 {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = new int[9];
        ArrayList<Integer> answer = new ArrayList<>();
        int plusAll = 0;
        int n1 = 0;
        int n2 = 0;

        for (int i = 0; i < 9; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            plusAll += arr[i];
        }

        for (int i = 0; i < 9; i++) {
            for (int j = i + 1; j < 9; j++) {
                int sum = plusAll - (arr[i] + arr[j]);
                if (sum == 100) {
                    n1 = i;
                    n2 = j;
                }
            }
        }

        for (int i = 0; i < 9; i++) {
            if (i != n1 && i != n2) {
                answer.add(arr[i]);
            }
        }

        Collections.sort(answer);
        for (Integer n : answer) {
            System.out.println(n);
        }
    }
}
