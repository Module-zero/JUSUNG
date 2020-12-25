import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q2022 {
    static double x, y, c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        x = Double.parseDouble(st.nextToken());
        y = Double.parseDouble(st.nextToken());
        c = Double.parseDouble(st.nextToken());

        double left = 0;
        double right = Math.min(x, y);
        double mid = -1;
        while (right - left > 0.000001) {
            mid = (left + right) / 2.0;
            double h1 = Math.sqrt(x*x - mid*mid);
            double h2 = Math.sqrt(y*y - mid*mid);
            double h = (h1*h2)/(h1+h2);
            if (h < c) {
                right = mid;
            } else {
                left = mid;
            }
        }
        System.out.printf("%.3f", mid);
    }
}
