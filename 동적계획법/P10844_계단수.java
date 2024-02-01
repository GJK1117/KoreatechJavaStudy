import java.util.Scanner;

public class P10844_계단수 {
    public static void main(String[] args) {
        // scnner 정의 및 N 입력
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        // 나머지 연산 때 사용할 숫자
        long mod = 1000000000;
        // 각 단계(N이 최대 100)마다 숫자가 10개(0~9) 가능
        long[][] dp = new long[101][10];
        // N = 1 일 때, 0은 0, 나머지(1~9)는 1의 경우의 수가 있음
        dp[1][0] = 0;
        for(int i = 1; i < 10; i++) dp[1][i] = 1;

        for(int i = 2; i <= N; i++){
            // 0과 9는 연속되는 수가 각각 1, 8 밖에 될 수 없음
            dp[i][0] += dp[i - 1][1];
            dp[i][9] += dp[i - 1][8];
            // 연속된 수가 되려면 자기자신 +- 1가 될 수 있음
            for(int j = 1; j < 9; j++){
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j + 1])% mod;
            }
        }

        // N단계일 때 각 자리수의 경우의 수를 더하여 결과 출력
        long result = 0;
        for(int i = 0; i < 10; i++) result += dp[N][i] % mod;
        System.out.println(result);
    }
}
