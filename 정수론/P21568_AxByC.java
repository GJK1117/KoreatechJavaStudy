import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P21568_AxByC {
    static Index parent = new Index(); //유클리드 호제법을 위한 객체 생성
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());   //A 입력
        int B = Integer.parseInt(st.nextToken());   //B 입력
        int C = Integer.parseInt(st.nextToken());   //C 입력

        //C가 A와 B의 최대공약수의 배수인지 확인
        if(C % gcd(A,B) == 0){
            Index res = wideEuclidean(A, B);
            //기본적으로 ax + by = gcd(a, b) 일때 만족하는 x, y가 존재한다.
            //이때, c가 gcd(a, b)의 k 배라고 가정하면, a와 b가 그대로이므로
            //a(kx') + b(ky') = k*gcd(a,b) = c 가 된다. 따라서, x = kx', y = ky' 이다.
            int k = C/gcd(A, B);
            System.out.println(k * res.x + " " + k * res.y);
        } else {
            System.out.println(-1); //배수가 아닌경우 만족하는 정수해 존재 X
        }
    }

    //최대공약수 구하기 : 유클리드 호제법
    static int gcd(int a, int b){
        if(a%b == 0) return b;
        else return gcd(b, a%b);
    }

    //확장 유클리드 호제법 사용
    static Index wideEuclidean(int a, int b){
        if(a%b==0) {
            return new Index(0, 1); //x=0, y=1 부터 시작해 역순으로 계산
        }
        Index past = wideEuclidean(b, a % b);   //이전 x, y 저장
        parent.x = past.y;          //현재 x = 이전 y
        parent.y = past.x - (a/b)*past.y;   //현재 y = 이전 x - 몫 * 이전 y
        return new Index(parent.x, parent.y);
    }

    //x, y를 저장할 클래스
    static class Index{
        long x;
        long y;
        public Index(){}
        public Index(long x, long y){
            this.x = x;
            this.y = y;
        }
    }
}
