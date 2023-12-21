import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class P11004_K번째수 {
    public static void main(String[] args) throws IOException {
        // 버퍼 입력 사용
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        // 스페이스 단위로 입력하기 위해 토큰 선언 및 초기화, 엔터 입력 후 다음 입력 시에 항상 이 구문을 사용하여 토큰 초기화
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine());

        // 입력할 숫자 개수 변수
        int N = Integer.parseInt(stringTokenizer.nextToken());
        // K번째 수
        int K = Integer.parseInt(stringTokenizer.nextToken());

        stringTokenizer = new StringTokenizer(bufferedReader.readLine());
        // 순차 값을 저장할 배열
        int[] input = new int[N];

        // 배열 값 초기화
        for(int i = 0; i < N; i++){
            input[i] = Integer.parseInt(stringTokenizer.nextToken());
        }

        // 퀵 정렬
        quickSort(input, 0, N-1, K - 1);
        // 결과 출력
        System.out.println(input[K - 1]);
    }

    // 퀵 정렬 함수
    public static void quickSort(int[] arr, int left, int right, int K){
        // left가 right보다 작거나 같을 경우
        if(left<=right){
            int pivot = partition(arr, left, right);
            if(pivot==K) return;
            else if(pivot > K) quickSort(arr, left, pivot-1, K);
            else quickSort(arr, pivot+1, right, K);
        }
    }

    // 퀵 정렬의 파티션 함수
    public static int partition(int[] arr, int lo, int hi){
        // lo + 1와 hi가 같을 경우
        if(lo + 1 == hi) {
            // lo와 hi를 비교하여 lo가 크면 lo와 hi를 바꾸고 hi를 반환
            if(arr[lo] > arr[hi]) swap(arr, lo, hi);
            return hi;
        }

        // 중간 값 찾기
        int mid = (lo + hi) / 2;
        // mid와 lo의 위치 변환
        swap(arr, lo, mid);
        // lo를 pivot으로 설정
        int pivot = arr[lo];
        // lo + 1부터 hi까지 구간 설정
        int i = lo + 1, j = hi;
        // i가 j보다 작거나 같을 경우까지 반복
        while(i <= j){
            // pivot보다 큰 값을 찾을 때까지 i를 오른쪽으로 이동
            while(pivot < arr[j] && j > 0) j--;
            // pivot보다 작은 값을 찾을 때까지 j를 왼쪽으로 이동
            while(pivot > arr[i] && i < arr.length - 1) i++;
            // i가 j보다 작거나 같을 경우 i와 j를 바꾸고 i를 오른쪽으로, j를 왼쪽으로 이동
            if(i<=j) swap(arr, i++, j--);
        }

        // lo와 j를 바꾸고 j를 반환
        arr[lo] = arr[j];
        arr[j] = pivot;
        return j;
    }

    // 배열의 두 값을 바꾸는 함수
    public static void swap(int[] arr, int i, int j){
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
