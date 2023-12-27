import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1931_회의실배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] rooms = new int[N][2];  //강의 배열
        int res = 1, s = 0, e = 1;      //결과값, 현재회의, 다음회의

        //각 회의의 시작시간 끝시간 입력
        StringTokenizer st;
        for(int i=0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            rooms[i][0] = Integer.parseInt(st.nextToken());
            rooms[i][1] = Integer.parseInt(st.nextToken());
        }
        //람다식 이용 (매개변수,..) -> {실행문 ..}
        //최선의 경우 찾기 위해 끝시간으로 비교 -> 끝시간이 적을 수록 많은 회의가능
        //끝나는 시간 기준으로 오름차순 정렬, 끝나는 시간이 같을 때는 시작시간 오름차순 정렬
        Arrays.sort(rooms, (o1, o2) -> (o1[1] == o2[1]) ? Integer.compare(o1[0], o2[0]) : Integer.compare(o1[1], o2[1]));
        // OR
        //Arrays.sort(rooms, (o1, o2) -> (o1[1] == o2[1]) ? o1[0] - o2[0] : o1[1] - o2[1] );

        //다음 회의 시작시간이 현재 회의 끝시간 이상이여야 함 -> 최선의 경우
        //작으면 다음 회의 비교
        while(e < N){
            if(rooms[e][0] >= rooms[s][1]){
                res++;
                s = e;
                e++;
            }else{
                e++;
            }
        }
        System.out.println(res);
    }
}
