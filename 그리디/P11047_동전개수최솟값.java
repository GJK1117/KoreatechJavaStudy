import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11047_동전개수최솟값 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   //동전 종류
        int K = Integer.parseInt(st.nextToken());   //가치 합
        int res = 0;        //결과값 저장할 변수

        int[] coin = new int[N];    //동전가치 오름차순으로 주어짐
        for(int i=0; i<N; i++) coin[i] = Integer.parseInt(br.readLine());

        //배열 끝부터 반복하며 K보다 작을때 K와 나누어 몫과 나머지를 구해준다.
        //여기서 몫이 동전의 개수이고, 나머지가 남은 금액이다.
        for(int i=N-1; i>=0; i--){
            if(coin[i] <= K){
                int value = K / coin[i];
                res += value;
                K %= coin[i];
            }
        }
        System.out.println(res);
    }
}
