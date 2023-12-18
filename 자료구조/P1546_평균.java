import java.util.Scanner;

public class P1546_평균 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        //Scanner 로 변수 입력
        int n = scanner.nextInt();
        int[] arr = new int[n]; //입력받은 크기의 배열 생성
        double res = 0, M = 0;  //정답이 실수이므로 double 로 선언

        for (int i=0; i<n; i++) {
            arr[i] = scanner.nextInt(); //성적 입력 후 배열에 저장
            if (arr[i] > M) M = arr[i]; //동시에 최댓값 저장
        }
        for (int i : arr) {
            res += i*100/M; //각 성적을 최댓값으로 나눈 후 100을 곱해주고 더함
        }
        System.out.println(res/n);  //평균계산하여 출력
    }
}