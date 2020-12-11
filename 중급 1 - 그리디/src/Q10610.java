import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10610 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int[] arr = new int[10];
        int check = 0;
        for (int i = 0; i < s.length(); i++) {
            check += s.charAt(i) - '0';
            arr[s.charAt(i) - '0']++;
        }

        if (arr[0] == 0 || check % 3 != 0) {
            System.out.print(-1);
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 9; i >= 0; i--) {
            while (arr[i]-- > 0) {
                sb.append(i);
            }
        }
        System.out.print(sb);
    }
}
