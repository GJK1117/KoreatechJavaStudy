package 그래프;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11403_경로찾기 {
    static int N;
    static int A[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        A = new int[N][N];
        for(int i=0; i<N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<N; j++){
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        //플로이드-워셜 수행
        for(int k=0; k<N; k++){
            for(int i=0; i<N; i++){
                for(int j=0; j<N; j++){
                    if(A[i][k] == 1 && A[k][j] == 1)
                        A[i][j] = 1;
                }
            }
        }
        //배열 출력
        for(int i=0; i<N; i++){
            for(int j=0; j<N; j++){
                System.out.print(A[i][j]+" ");
            }
            System.out.println();
        }
    }
}
