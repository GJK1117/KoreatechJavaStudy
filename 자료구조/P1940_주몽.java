import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class P1940_주몽 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //n, m 입력
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());
        int start = 0, end = n-1, count = 0;    //투포인터 -> 시작인덱스, 끝인덱스, 조합의 개수

        //고유번호 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[n];
        for (int i=0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        //고유번호 배열 오름차순 정렬
        Arrays.sort(arr);

        //시작 인덱스의 값이 끝 인덱스의 값보다 작은 경우에 반복
        while (start < end){
            //시작 인덱스와 끝 인덱스의 값이 m일 경우 조합개수(count) 증가
            //시작 인덱스 증가, 끝 인덱스 감소
            if(arr[start] + arr[end] == m){
                count++;
                start++;
                end--;
            } else if (arr[start] + arr[end] > m) {
                //시작 인덱스와 끝 인덱스의 합이 m보다 큰 경우 값이 작아져야 하므로 끝 인덱스 감소
                end--;
            } else {
                //시작 인덱스와 끝 인덱스의 합이 m보다 작은 경우 값이 커져야 하므로 시작 인덱스 증가
                start++;
            }
        }
        System.out.println(count);
    }
}
