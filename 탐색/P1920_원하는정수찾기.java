package 탐색;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1920_원하는정수찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());      //데이터 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for(int i=0; i<N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(A);

        int M = Integer.parseInt(br.readLine());      //찾아야 할 개수
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<M; i++){
            boolean check = false;
            int find = Integer.parseInt(st.nextToken());
            int s = 0;                  //시작
            int e = A.length - 1;       //끝
            while(s <= e){
                int m = (s + e) / 2;    //가운데
                if(A[m]<find){
                    s = m + 1;
                }
                else if(A[m]>find){
                    e = m - 1;
                }
                else{                   //탐색 완료
                    check = true;
                    break;
                }
            }
            if(check)
                System.out.println(1);
            else
                System.out.println(0);
        }
    }
}