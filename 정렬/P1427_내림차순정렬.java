package 정렬;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class P1427_내림차순정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();         //입력받은 문자열
        int[] A = new int[str.length()];    //문자열의 숫자들을 저장해줄 배열
        //문자열의 숫자들을 하나씩 잘라 배열에 저장
        for(int i=0; i<A.length; i++){
            A[i] = Integer.parseInt(str.substring(i, i+1));
        }
        for(int i=0; i<A.length; i++){
            int max = i;
            //최댓값 인덱스 찾기(i+1~끝까지)
            for(int j=i+1; j<A.length; j++){
                if(A[max]<A[j]){
                    max = j;
                }
            }
            //A[i]가 A[max]보다 작으면 스왑
            if(A[i]<A[max]){
                int temp = A[i];
                A[i] = A[max];
                A[max] = temp;
            }
        }
        for(int i=0; i<A.length; i++){
            System.out.print(A[i]);
        }
    }
}
