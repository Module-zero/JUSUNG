import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 최적화 잘됐음
public class Q1373 {
    
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder binaryNum = new StringBuilder(br.readLine());
        while (binaryNum.length() % 3 != 0) {
           binaryNum.insert(0, '0');
        }

        int[] nums = {4, 2, 1};
        int value = 0;
        for (int i = 0; i < binaryNum.length(); i++) {
            int bin = binaryNum.charAt(i) - '0';
            int idx = i % 3;
            value += bin * nums[idx];
            if (idx == 2) {
                System.out.print(value);
                value = 0;
            }
        }
    }
}
