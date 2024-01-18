import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class P11758_CCW {
    // 버퍼 입출력 및 토큰 입력 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    // 죄표 입력 리스트
    static ArrayList<int[]> coord = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        // p1, p2, p3 입력
        for(int i = 0; i < 3; i++){
            st = new StringTokenizer(br.readLine());
            int[] input = new int[2];
            input[0] = Integer.parseInt(st.nextToken());
            input[1] = Integer.parseInt(st.nextToken());
            coord.add(input);
        }

        // ccw 변수
        int ccw = 0;
        // ccw 계산
        for(int i = 0; i < 3; i++){
            ccw += coord.get(i)[0] * coord.get((i+1)%3)[1];
            ccw -= coord.get((i+1)%3)[0] * coord.get(i)[1];
        }

        // ccw = 0 이면 일직선 방향, ccw < 0 이면 시계방향, ccw > 0 이면 반시계 방향
        if(ccw==0) System.out.println(0);
        else if(ccw<0) System.out.println(-1);
        else System.out.println(1);
    }
}
