import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1934_최소공배수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        int[] res = new int[T];

        for(int i=0; i<T; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int min = Math.min(a, b);   //큰 수, 작은 수 판별
            int max = Math.max(a, b);

            //최소공배수 * 최대공약수 = 두 수의 곱
            //최소공배수 = 두수의 곱 / 최대공약수
            res[i] = a*b/findGcd(max, min);     //반복문 풀이
            //res[i] = a*b/euclidean(max, min); //유클리드 호제법 풀이
        }
        for(int i=0; i<res.length; i++) System.out.println(res[i]);
    }

    //반복문으로 풀이
    public static int findGcd(int a, int b){
        int gcd = 0;
        for(int i=1; i<=b; i++){
            if(a % i==0 && b % i==0) gcd = i;   //두 수 모두 나누어지면 저장
        }
        return gcd; //최대 공약수 반환
    }

    //유클리드 호제법 사용
    public static int euclidean(int a, int b){
        if(a%b == 0){
            return b;   //나머지 0일때 반환
        }
        else return euclidean(b, a%b);  //작은수와 나머지 수를 매개변수로 다시 호출
    }
}
