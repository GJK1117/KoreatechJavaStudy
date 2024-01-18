package 기하;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P2166_다각형의넓이 {
    static int N;
    static int point[][];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        point = new int[N][2];
        for(int i=0; i<N; i++){     //점 좌표 입력받기
            st = new StringTokenizer(br.readLine());
            point[i][0] = Integer.parseInt(st.nextToken());
            point[i][1] = Integer.parseInt(st.nextToken());
        }
        double result = 0;
        for(int i=1; i<N-1; i++){   //CCW 값은 삼각형의 넓이이므로 0번을 원점으로 나머지 두 점들과의 삼각형들의 넓이를 더함
            result += CCW(point[0][0], point[0][1], point[i][0], point[i][1], point[i+1][0], point[i+1][1]);
        }
        System.out.println(String.format("%.1f", Math.abs(result/2)));      //절댓값으로 출력하기
    }
    static long CCW(long x1, long y1, long x2, long y2, long x3, long y3){
        long temp = (x1*y2 + x2*y3 + x3*y1) - (x2*y1 + x3*y2 + x1*y3);
        return temp;
    }
}
