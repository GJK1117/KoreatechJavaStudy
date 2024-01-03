package 정수론;

import java.io.*;
import java.util.StringTokenizer;

public class P1850_최대공약수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        /*
        입력받은 A와 B는 1의 개수이다.
        위 수들의 최대공약수는 A와 B의 최대공약수만큼 1을 나열한 것과 같다는 규칙을 바탕으로
        A와 B의 최대공약수를 구해주면 간단하게 해결할 수 있다.
         */
        long result = gcd(A, B);

        for(int i=0; i<result; i++){
            bw.write('1');
        }
        bw.flush();
        bw.close();
    }
    //유클리드 호제법을 사용한 최대공약수 재귀 함수
    public static long gcd(long A, long B){
        if(B == 0){
            return A;
        }
        else
            return gcd(B, A % B);
    }
}
