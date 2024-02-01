package 동적계획법;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P14501_퇴사 {
    static int N;
    static int T[];
    static int P[];
    static int D[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        T = new int[N+1];       //소요 시간
        P = new int[N+1];       //비용
        D = new int[N+2];       //누적 비용, 마지막날 상담을 한 뒤(N+1일차)까지 계산해줘야 하므로 N+2
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }
        for(int i=1; i<N+1; i++){
            if(i+T[i] <= N+1){  //상담 기간이 퇴사 이후가 아닌지 체크
                //상담이 끝났을 때 번 누적 비용이 기존보다 더 많으면 갱신
                D[i+T[i]] = Math.max(D[i+T[i]], D[i]+P[i]);
            }
            //다음 검색할 dp배열에 값을 채워주기 위한 코드
            D[i+1] = Math.max(D[i], D[i+1]);
        }
        System.out.println(D[N+1]);
    }
}
