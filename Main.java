import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] ss = reader.readLine().split(" ");
        int n = Integer.parseInt(ss[0]), m = Integer.parseInt(ss[1]), k = Integer.parseInt(ss[2]);
        int[][] diff = new int[n + 10][m + 10];
        for (int i = 1; i <= n; i++) {
            ss = reader.readLine().split(" ");
            for (int j = 1; j <= m; j++) {
                insert(diff, i, j, i, j, Integer.parseInt(ss[j - 1]));
            }
        }
        while (k-- > 0) {
            ss = reader.readLine().split(" ");
            int x1 = Integer.parseInt(ss[0]), y1 = Integer.parseInt(ss[1]);
            int x2 = Integer.parseInt(ss[2]), y2 = Integer.parseInt(ss[3]);
            int c = Integer.parseInt(ss[4]);
            insert(diff, x1, y1, x2, y2, c);
        }
        for (int i = 1; i <= n; i++) {
            StringBuffer sb = new StringBuffer();
            for (int j = 1; j <= m; j++) {
                diff[i][j] = diff[i - 1][j] + diff[i][j - 1] - diff[i - 1][j - 1] + diff[i][j];
                sb.append(diff[i][j]).append(" ");
            }
            System.out.println(sb.toString());
        }

    }
    static void insert(int[][] diff, int x1, int y1, int x2, int y2, int c) {
        diff[x1][y1] += c;
        diff[x2 + 1][y1] -= c;
        diff[x1][y2 + 1] -= c;
        diff[x2 + 1][y2 + 1] += c;
    }
}
