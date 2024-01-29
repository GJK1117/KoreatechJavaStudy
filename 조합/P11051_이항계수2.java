import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11051_이항계수2 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static long[][] cal;
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        cal = new long[N+1][N+1];
        // iC0 = 1, iCi = 1, iC1 = i
        for(int i = 0; i <= N; i++){
            cal[i][1] = i;
            cal[i][0] = 1;
            cal[i][i] = 1;
        }

        // iCj = (i-1)C(j-1) + (i-1)Cj
        for(int i = 2; i <= N; i++){
            for(int j = 1; j < i; j++){
                cal[i][j] = (cal[i - 1][j] + cal[i - 1][j - 1])%10007;
            }
        }

        System.out.println(cal[N][K]);
    }
}
