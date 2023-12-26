package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11399_ATM {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());    //사람 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];       //사람별 걸리는 시간
        int[] S = new int[N];       //사람별 걸리는 시간 합

        //A 입력받기
        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        for(int i=0; i<N; i++){
            int insert = i;         //삽입위치
            for(int j=i-1; j>=0; j--){
                //삽입하려는 A[i]가 A[j]보다 크면 j+1을 삽입위치로
                if(A[i]>A[j]){
                    insert = j+1;
                    break;
                }
                if(j == 0)
                    insert = 0;
            }
            int temp = A[i];        //삽입하려는 값 저장
            //삽입을 위해서 하나씩 뒷쪽으로 이동
            for(int j=i; j>insert; j--){
                A[j] = A[j-1];
            }
            A[insert] = temp;       //저장해놓은 값 위치에 삽입
        }
        //합 배열
        S[0] = A[0];
        for(int i=1; i<N; i++){
            S[i] = S[i-1] + A[i];
        }
        //합 배열의 총합
        int result = 0;
        for(int i=0; i<N; i++){
            result += S[i];
        }
        System.out.println(result);
    }
}
