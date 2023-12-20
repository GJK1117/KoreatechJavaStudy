import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P2750_수정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n]; //n 입력

        //정렬되지 않은 배열 입력
        for(int i=0; i<n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }
        //정렬 함수 사용
        //Arrays.sort(arr);

        //버블정렬 사용
        int temp = 0;
        for(int k=0; k<n-1; k++){
            for(int i=0; i<n-1; i++){
                if(arr[i] >= arr[i+1]){
                    temp = arr[i];
                    arr[i] = arr[i+1];
                    arr[i+1] = temp;
                }
            }
        }
        //정렬된 배열 출력
        for (int i : arr) {
            System.out.println(i);
        }
    }
}
