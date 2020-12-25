import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Q11664 {
    static int ax, ay, az, bx, by, bz, cx, cy, cz;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        ax = Integer.parseInt(st.nextToken());
        ay = Integer.parseInt(st.nextToken());
        az = Integer.parseInt(st.nextToken());
        bx = Integer.parseInt(st.nextToken());
        by = Integer.parseInt(st.nextToken());
        bz = Integer.parseInt(st.nextToken());
        cx = Integer.parseInt(st.nextToken());
        cy = Integer.parseInt(st.nextToken());
        cz = Integer.parseInt(st.nextToken());

        double lx = ax, ly = ay, lz = az, rx = bx, ry = by, rz = bz;
        double min = Double.MAX_VALUE;

        while (true) {
            double mx = (lx+rx)/2;
            double my = (ly+ry)/2;
            double mz = (lz+rz)/2;
            double d1 = getDist(lx, ly, lz, cx, cy, cz);
            double d2 = getDist(rx, ry, rz, cx, cy, cz);
            double d3 = getDist(mx, my, mz, cx, cy, cz);

            if (Math.abs(d3 - min) <= 0.000000001) {
                break;
            }
            min = Math.min(min, d3);

            if (d1 > d2) {
                lx = mx;
                ly = my;
                lz = mz;
            } else {
                rx = mx;
                ry = my;
                rz = mz;
            }
        }

        System.out.printf("%.10f", min);
    }

    static double getDist(double x1, double y1, double z1, double x2, double y2, double z2) {
        return Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1)+(z2-z1)*(z2-z1));
    }
}
