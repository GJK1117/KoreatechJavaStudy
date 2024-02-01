package 동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P2193_이친수 {
    static int N;
    static long D[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        D = new long[N+1];
        D[1] = 1;
        for(int i=2; i<N+1; i++){
            //D[i-1]은 마지막 자리가 0일 때, D[i-2]는 마지막 자리가 1일 때
            D[i] = D[i-1] + D[i-2];
        }
        System.out.println(D[N]);
    }
}
