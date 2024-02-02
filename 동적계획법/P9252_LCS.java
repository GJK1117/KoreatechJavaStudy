import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class P9252_LCS {
    static long[][] DP; //점화식 배열
    static ArrayList<Character> Path;   //문자 저장 리스트
    static char[] X, Y; //입력 문자열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        X = br.readLine().toCharArray();
        Y = br.readLine().toCharArray();
        DP = new long[X.length+1][Y.length+1];
        Path = new ArrayList<>();

        //점화식 초기화
        //2차원 배열의 행 열 인덱스를 X, Y의 문자들이라고 가정
        //각 문자가 같을 때 왼쪽 대각선의 값에서 +1 해주는 것은 문자가 같으면 최장 공통 수열에 포함되므로 값을 하나 증가시켜주는 것이다.
        //이런 식으로 각 인덱스에 현재 인덱스까지의 가능한 최장 수열의 크기를 저장해준다.
        for(int i=1; i<=X.length; i++) {
            for (int j = 1; j<=Y.length; j++) {
                if (X[i - 1] == Y[j - 1]) {
                    DP[i][j] = DP[i - 1][j - 1] + 1;    //같은 문자일 경우 왼쪽 대각선의 값에서 +1을 해준다.
                } else {
                    DP[i][j] = Math.max(DP[i-1][j], DP[i][j-1]);    //같은 문자가 아닐 경우 위쪽 값과 왼쪽 값 중 큰 값 저장
                }
            }
        }
        System.out.println(DP[X.length][Y.length]); //최장 문자열의 길이를 출력
        getText(X.length, Y.length);    //점화식 배열을 초기화한 순서의 역순으로 진행하며 만족하는 문자를 출력한다.
        for(int i=Path.size()-1; i>=0; i--){
            System.out.print(Path.get(i));
        }
    }

    //점화식 초기화 할때와 역순으로 진행하여 문자를 저장
    static void getText(int r, int c){
        if (r==0 || c==0) return;       //문자열이 존재하지 않을 경우
        //두 문자의 값이 같을 경우 최장 공통 수열에 포함되는 문자이므로 Path 배열에 넣어준 후 왼쪽 대각선 위쪽으로 이동 시켜준다.
        if(X[r-1] == Y[c-1]){   //두 문자열의 문자가 같을 경우에 Path 배열에 저장
            Path.add(X[r-1]);
            getText(r-1, c-1);  //저장 후 왼쪽 대각선으로 이동
        } else {
            //두 문자의 값이 다를 경우 점화식 배열의 역순으로 이동시킨다.
            //점화식 배열에선 문자가 다를 경우 더 큰값을 저장해주었기 때문에 더 큰쪽으로 이동시키는 것이다.
            //위의 값이 왼쪽 값보다 클 경우 위쪽으로 이동
            if(DP[r-1][c] > DP[r][c-1]) getText(r-1, c);
            //위의 값이 왼쪽 값보다 작을 경우 왼쪽으로 이동
            else getText(r, c-1);
        }
    }
}