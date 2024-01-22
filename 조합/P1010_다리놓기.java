package 조합;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1010_다리놓기 {
    static int N, M, T;
    static int D[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        T = Integer.parseInt(st.nextToken());
        D = new int[30][30];
        //DP배열 초기화
        for(int i=0; i<30; i++){
            D[i][0] = 1;
            D[i][1] = i;
            D[i][i] = 1;
        }
        //점화식을 통해 조합 배열 초기화
        for(int i=2; i<30; i++){
            for(int j=1; j<i; j++){
                D[i][j] = D[i-1][j-1] + D[i-1][j];
            }
        }
        for(int i=0; i<T; i++){
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            System.out.println(D[M][N]);
        }
    }
}
