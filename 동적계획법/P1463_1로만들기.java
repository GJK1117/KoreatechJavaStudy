package 동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1463_1로만들기 {
    static int N;
    static int D[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        D = new int[N+1];
        D[1] = 0;
        for(int i=2; i<N+1; i++){
            D[i] = D[i-1]+1;            //D[i]에 1을 빼는 연산을 하는 경우 대입
            if(i%2 == 0)
                D[i] = Math.min(D[i], D[i/2]+1);    //1을 뺀 경우의 수보다 2로 나누는게 효율적이면(숫자가 작으면)
            if(i%3 == 0)
                D[i] = Math.min(D[i], D[i/3]+1);    //다른 경우의 수보다 3으로 나누느게 효율적이면
        }
        System.out.println(D[N]);
    }
}
