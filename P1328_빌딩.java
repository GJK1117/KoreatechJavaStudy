import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1328_빌딩 {
    // 버퍼 입력 및 토큰 입력 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    // dp 배열 선언
    static long[][][] D;
    public static void main(String[] args) throws IOException{
        // N, R, L 입력 및 나머지 연산 변수 선언
        st=new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int L = Integer.parseInt(st.nextToken());
        int R = Integer.parseInt(st.nextToken());
        long mod = 1000000007;

        // dp 배열 초기화
        D = new long[N + 1][L + 1][R + 1];
        // 초기값 선언 빌딩이 1개일 때 L = 1, R = 1인 경우의 수는 1개
        D[1][1][1] = 1;
        // 빌딩이 2개일 때부터 점화식을 사용하여 경우의 수 탐색
        for(int n = 2; n <= N; n++){
            for(int l = 1; l <= L; l++){
                for(int r = 1; r <= R; r++){
                    // n - 1개의 빌딩, L = l - 1, R = r일 때 왼쪽에 가장 작은 빌딩 추가한 상황
                    long leftAdd = D[n - 1][l - 1][r] % mod;
                    // n - 1개의 빌딩, L = l, R = r - 1일 때 오른쪽에 가장 작은 빌딩 추가한 상황
                    long rightAdd = D[n - 1][l][r - 1] % mod;
                    // n - 1개의 빌딩, L = l, R = r일 때 양쪽을 끝을 제외한 중간 사이에 가장 작은 빌딩을 추가한 상황
                    long middleAdd = D[n - 1][l][r] * (n - 2) % mod;
                    // 3가지 상황의 경우의 수를 더해 dp 배열 계산
                    D[n][l][r] = (leftAdd + rightAdd + middleAdd) % mod;
                }
            }
        }
        // 결과 출력
        System.out.println(D[N][L][R]);
    }
}
