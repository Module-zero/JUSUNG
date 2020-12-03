import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q1676 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        int sum = 0;

        for (int i = 1; i <= num; i++) {
            if (i % 5 == 0) {
                sum++;
                if (i % 25 == 0) {
                    sum++;
                    if (i % 125 == 0) {
                        sum++;
                    }
                }
            }
        }
        System.out.println(sum);
    }
}
