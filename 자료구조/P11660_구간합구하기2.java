import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11660_구간합구하기2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][] arr = new int[N+1][N+1];

        //원본 배열 입력
        for(int i=1; i<N+1; i++){
            //다음 행의 값 입력시 새로 토큰 생성해주어야 함
            st = new StringTokenizer(br.readLine());
            for(int j=1; j<N+1; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] sumArr = new int[N+1][N+1];
        //구간합 배열 생성
        for(int i=1; i<N+1; i++){
            for(int j=1; j<N+1; j++){
                sumArr[i][j] = sumArr[i-1][j] + sumArr[i][j-1] - sumArr[i-1][j-1] + arr[i][j];
            }
        }
        //문제 값을 토대로 값 출력
        for(int i = 0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            //질의에 해당하는 구간합 출력
            System.out.println(sumArr[x2][y2] - sumArr[x1-1][y2] - sumArr[x2][y1-1] + sumArr[x1-1][y1-1]);
        }
    }
}
