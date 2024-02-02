import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P11049_행렬곱연산횟수의최솟값 {
    static int[][] D;   //i~j 행렬까지 최소 연산 횟수 저장 배열
    static int N;
    static Matrix[] M;  //각 행렬을 저장하는 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        D = new int[N+1][N+1];
        M = new Matrix[N+1];
        for(int i=0; i<D.length; i++){
            Arrays.fill(D[i], -1);  //배열 D -1로 초기화
        }
        for(int i=1; i<=N; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            M[i] = new Matrix(y, x);
        }
        System.out.println(execute(1, N));
    }

    //톱-다운 방식의 동적계획법
    //매개변수 시작행렬 인덱스, 종료행렬 인덱스
    static int execute(int s, int e){
        int result = Integer.MAX_VALUE; //결과값
        //방문처리 계산하면 다시 계산 X
        if(D[s][e] != -1) return D[s][e];
        //행렬 1개 곱셈 연산 수
        if(s==e) return 0;
        //행렬 2개 곱셈 연산 수
        if(s+1 == e) return M[s].y * M[s].x * M[e].x;
        //행렬 3개 이상일 때 곱셈 연산 수
        for(int i=s; i<e; i++){
            //재귀 호출로 점화식을 처리함
            //가능한 모든 분할 지점의 최소의 수를 계산
            result = Math.min(result, M[s].y * M[i].x * M[e].x + execute(s, i) + execute(i+1, e));
        }
        return D[s][e] = result;
    }

    //행렬을 나타내는 클래스
    static class Matrix {
        private int y;
        private int x;
        public Matrix(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }
}
