import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Q10872 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        System.out.println(fact(num));
    }

    public static int fact(int n) {
        if (n == 1 || n == 0) { return 1; }
        return n * fact(n-1);
    }
}
