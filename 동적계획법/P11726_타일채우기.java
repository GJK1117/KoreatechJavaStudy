import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P11726_타일채우기 {
    static long[][] D;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        long res = 0;
        int r = 0;
        D = new long[N+1][N+1];
        for(int i=0; i<=N; i++){
            D[i][1] = i;
            D[i][0] = 1;
            D[i][i] = 1;
        }
        //조합 배열 초기화
        for(int i=2; i<=N; i++){
            for(int j=1; j<i; j++){
                //점화식에 값을 넣을 때마다 10007과 나눈 나머지 값을 넣어줌
                D[i][j] = (D[i-1][j-1] + D[i-1][j]) % 10007;
            }
        }
        //N을 하나 더해주고, cnt를 하나 빼주며 경우의 수 더해줌
        while(N >= r){
            res += D[N--][r++];
        }
        System.out.println(res % 10007);
    }
}
