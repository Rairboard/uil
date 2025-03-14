import java.io.*;
import java.util.*;

public class Kostya {
    public static void main(String[] args) throws IOException {
        Scanner scan = new Scanner(new File("kostya_student.dat"));
        int T = scan.nextInt();
        assert 1 <= T && T <= 50;

        int bigN = 0;
        for (int caseNum = 1; caseNum <= T; caseNum++) {
            int n = scan.nextInt();
            int k = scan.nextInt();
            
            assert 1 <= k && k <= n && n <= 1000;
            if (n >= 100)
                bigN++;

            System.out.printf("Case #%d: %.4f\n", caseNum, solve(n, k));
        }

        assert bigN <= 10;
    }

    private static double solve(int n, int k) {
        // probability of having k socks in bed
        double[] dp = new double[k + 1];
        dp[0] = 1.0;

        for (int i = 0; i < 2 * n; i++) {
            double[] ndp = new double[k + 1];

            for (int j = 0; j <= k; j++) {
                // probability you pair a sock already on the bed
                double sameProb = ((double) j) / (2 * n - i);

                if (j > 0) {
                    ndp[j - 1] += sameProb * dp[j];
                }
                if (j + 1 <= k) {
                    ndp[j + 1] += (1.0 - sameProb) * dp[j];
                }
            }

            dp = ndp;
        }

        double ans = 1.0;
        for (double x : dp) {
            ans -= x;
        }

        return ans;
    }
}
