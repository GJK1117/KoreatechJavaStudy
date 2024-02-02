import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P1915_가장큰정사각형 {
    static long[][] square;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        long max = 0;
        square = new long[N+1][M+1];
        //배열에 수를 공백 없이 입력 받기 위해 문자열로 입력한 후 char 배열에 넣어줌
        for(int i=1; i<=N; i++){
            char[] s = br.readLine().toCharArray();
            for(int j=1; j<=M; j++){
                square[i][j] = s[j-1]-'0';  //'0' 의 아스키 값을 빼서 실제 숫자 저장
            }
        }
        for(int i=1; i<=N; i++){
            for(int j=1; j<=M; j++){
                if(square[i][j]==1) {
                    //정사각형은 두 변의 길이가 같으므로 넓이를 구하기 위해서 한변의 길이만 알면된다. 그 한변을 제곱하면 되기 때문이다.
                    //i, j 를 정사각형의 오른쪽 아래 꼭짓점이라고 가정하고 왼쪽 대각선, 위, 왼쪽 값중 가장 작은 값에 자신의 값을 더해서 저장한다.
                    //i, j 위치에 저장되는 값은 해당 위치에서 만들수 있는 가장 큰 정사각형의 변의 길이이다.
                    square[i][j] += Math.min(Math.min(square[i - 1][j - 1], square[i - 1][j]), square[i][j - 1]);
                    //실시간으로 가장 큰 변 저장
                    if(square[i][j] > max) max = square[i][j];
                }
            }
        }
        //구한 변의 길이 제곱하여 정사각형의 넓이 출력
        System.out.println((int)Math.pow(max, 2));
    }
}
