package 정렬;

import java.io.*;
import java.util.StringTokenizer;

public class P1517_버블소트2 {
    public static int[] A, tmp;
    public static long result;      //index가 이동한 거리를 저장할 result
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        A = new int[N+1];     //본 배열
        tmp = new int [N+1];  //정렬을 위한 임시 배열
        StringTokenizer st = new StringTokenizer(br.readLine());    //정렬해야할 수 입력받기
        for(int i=1; i<=N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }
        result = 0;
        merge_sort(1, N);
        System.out.println(result);
    }
    private static void merge_sort(int s, int e){
        if(s >= e)              //s는 start e는 end
            return;
        int m = (s + e) / 2;    //m은 mid
        merge_sort(s, m);
        merge_sort(m+1, e);
        //A를 tmp에 복사
        for(int i=s; i<=e; i++){
            tmp[i] = A[i];
        }
        int k = s;              //A의 포인터
        int index1 = s;         //앞쪽 그룹 포인터
        int index2 = m + 1;     //뒤쪽 그룹 포인터

        //두 그룹의 포인터 모두 끝에 도달하지 않았을 때 반복
        while(index1<=m && index2<=e){
            //왼쪽 그룹의 값이 오른쪽 그룹의 값보다 크면
            if(tmp[index1]>tmp[index2]) {
                //A[k]에 오른쪽 그룹의 값 대입 후 k++, index2++
                A[k++] = tmp[index2++];
                //20번 문제에서 수정한 부분(index가 이동한 거리를 셈)
                result = result + index2 - k;
            }
            //아니라면
            else{
                //왼쪽 그룹의 값을 A[k]에 대입 후 k++, index1++
                A[k++] = tmp[index1++];
            }
        }
        //남은 그룹의 값들 A에 대입해주는 부분
        while(index1 <= m){
            A[k++] = tmp[index1++];
        }
        while(index2 <= e){
            A[k++] = tmp[index2++];
        }
    }
}
