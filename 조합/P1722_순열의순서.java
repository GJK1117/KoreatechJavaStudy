import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1722_순열의순서 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] P = new int[21];      //k==2 일 경우 순열 저장 배열
        boolean[] visited = new boolean[21];    //방문표시 배열
        long K = 0;             //k==1 일때 입력값 변수
        long res2 = 1;       //k==2 일때 결과값 변수
        int[] res1 = new int[21];   //k==1 일때 결과값 배열
        StringTokenizer st = new StringTokenizer(br.readLine());
        int num =Integer.parseInt(st.nextToken());
        if(num==1) K = Long.parseLong(st.nextToken());    //값 입력
        else for(int i=1; i<=N; i++) P[i] = Integer.parseInt(st.nextToken());

        //K번째 수열 출력
        if(num==1){
            for(int i=1; i<=N; i++){
                int cnt = 1;    //현재 자리에서 만들 수 있는 모든 경우의 수를 계산하기 위한 변수
                for(int j=1; j<=N; j++){
                    if(visited[j]) continue;        //이미 사용한 숫자 continue
                    long n = factorial(N-i);    //현재 자리에 하나의 숫자가 올경우 될 수 있는 경우의 수
                    if(K <= cnt*n){     //입력 값이 현재 자리에서 만들수 있는 경우의 수보다 작을 때
                        K -= ((cnt-1)*n);   //앞의 경우를 모두 빼줌
                        res1[i] = j;        //수열 결과에 저장
                        visited[j] = true;  //넣었으므로 방문 처리
                        break;              //다음 자리로 이동
                    }
                    cnt++;  //K가 현재 자리에서 만들 수 있는 경우의 수보다 크므로 하나 증가 시켜준다.
                }
            }
            for(int i=1; i<=N; i++){
                System.out.print(res1[i] + " ");
            }
        }
        //몇번째 수열인지 출력
        else {
            for(int i=1; i<=N; i++){
                long cnt = 0;       //아직 사용되지 않은 숫자 세기 위한 변수
                for(int j=1; j<P[i]; j++){
                    if(!visited[j]) cnt++;  //사용되지 않았다면 그만큼 카운트 해줌
                }
                long n = factorial(N-i);
                res2 += cnt * n;   //앞의 경우의 수 모두 더해줌
                visited[P[i]] = true;       //사용했으므로 방문처리
            }
            System.out.println(res2);
        }
    }

    //팩토리얼 계산
    public static long factorial(int x){
        if(x==1 || x==0) return 1;
        return x * factorial(x-1);
    }
}
