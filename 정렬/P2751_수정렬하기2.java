package 정렬;

import java.io.*;

public class P2751_수정렬하기2 {
    public static int[] A, tmp;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        A = new int[N+1];     //본 배열
        tmp = new int [N+1];  //정렬을 위한 임시 배열
        for(int i=1; i<=N; i++){
            A[i] = Integer.parseInt(br.readLine());
        }
        merge_sort(1, N);
        for(int i=1; i<=N; i++){
            bw.write(A[i]+"\n");
        }
        bw.flush();
        bw.close();
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
