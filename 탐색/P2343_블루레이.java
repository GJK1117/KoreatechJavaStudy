import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P2343_블루레이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] lecture = new int[N];
        int start = 0, end = 0, cnt = 0, sum = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            lecture[i] = Integer.parseInt(st.nextToken());
            end += lecture[i];      //배열의 합 end 인덱스에 저장
            if (start <= lecture[i]) start = lecture[i];    //배열에서 최댓값 start 인덱스에 저장
        }

        //이진탐색
        while(start<=end){
            cnt = 0;
            sum = 0;
            int mid = (start+end)/2;
            for(int i=0; i<N; i++){
                if(sum + lecture[i] > mid){     //모든 동영상 저장할 수 있는지 확인
                    cnt++;      //블루레이 수 증가
                    sum = 0;    //합 초기화
                }
                sum += lecture[i];
            }
            if(sum != 0) cnt++;

            //블루레이 개수가 조건보다 많아져 현재 mid 값으로 모든 동영상 저장 못하면 오른쪽 배열 탐색(더 큰값 탐색)
            if(cnt > M) start = mid + 1;
            //현재 mid 값으로 모든 동영상 저장 가능하면 왼쪽 배열 탐색(최소값을 찾아야하므로 더 작은값 탐색)
            else end = mid - 1;
        }
        System.out.println(start);
    }
}
