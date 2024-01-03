import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1456_거의소수 {
    public static boolean[] prime;  //소수 판별 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //입력하는 수의 값이 최대 10의 14승 이므로 int 자료형을 사용할 수 없다(long 자료형 사용) -> 런타임에러
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());
        List<Integer> list = new ArrayList<>();
        long res = 0;

        findPrime(Math.sqrt(B));        //소수를 구해준다.

        for(int i = 0; i < prime.length; i++) {
            //소수 == false인 인덱스
            if(!prime[i]) {
                list.add(i);    //소수인 값들을 list에 넣어준다.
            }
        }

        //거의 소수를 구한다.
        for (Integer n : list) {
            long num = n;

            //n을 계속 곱해주며 A와 B 사이인 값들을 카운트 해준다.
            while(true){
                //자료형을 신경써주어야한다.
                //만약 10^7일 경우 10^14를 지나고 10^21 까지 간다.
                //(num * n > B) 같이 조건을 설정해주면 long 자료형의 크기를 넘어가 오버플로우가 난다.
                //따라서 이항 하여 아래와 같이 조건을 써주어야 오버플로우가 나지 않고 올바르게 값이 나온다.
                if(n > (B/num)) break;    //num*n > B 와 같다

                num *= n;           //n 제곱

                if(num>=A && num <= B) res++;   //A와 B 사이이면 결과값 +1
            }
        }
        System.out.println(res);
    }

    //에라토스테네스의 체 사용 시간복잡도: O(Nlog(logN))
    public static void findPrime(double num) {
        prime = new boolean[(int) (num+1)]; //루트B 까지의 소수 배열 생성
        Arrays.fill(prime, false);      //false로 초기화(소수로 가정)
        prime[0] = prime[1] = true;         //0과 1은 소수가 아니므로 true로 초기화

        //루트B 까지만 반복, 안의 반복문에서 초기값이 i*i이므로 루트B까지만 반복
        for(int i = 2; i<=Math.sqrt(num); i++) {
            //현재 수의 배수를 모두 소수가 아닌 수로 판단하여 true로 변경
            if(!prime[i]){
                for(int j=i+i; j<=num; j+=i)
                    prime[j] = true;
            }
        }
    }

    /*
    //일반적으로 구하는 소수 로직 시간복잡도: O(Nsprt(N))
    public static boolean findPrime(long num){
        for(long i=2; i<=Math.sqrt(num); i++){
            if(num%i==0) return false;
        }
        return true;
    }
    */
}