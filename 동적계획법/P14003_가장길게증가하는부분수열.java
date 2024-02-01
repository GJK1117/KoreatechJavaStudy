import java.io.*;
import java.util.StringTokenizer;

public class P14003_가장길게증가하는부분수열 {
    // 버퍼 입력 및 토큰 입력 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    // 입력배열, 부분수열 count 계산 배열, dp 배열, 정답 출력 배열 선언
    static int[] A = new int[1000001];
    static int[] B = new int[1000001];
    static int[] D = new int[1000001];
    static int[] ans = new int[1000001];
    // N과 부분수열의 최대 길이 변수(결과)
    static int N, maxLength;

    public static void main(String[] args) throws IOException{
        // N 입력 후 N개의 수로 구성된 수열 입력
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        // B배열의 초기 값은 수열의 첫번째 값으로 정의
        int idx;
        B[++maxLength] = A[1];
        // 점화식의 초기 값(인덱스가 1일 때) 정의
        D[1] = 1;
        // 수열의 두번째 위치부터 시작하여 최대 부분수열의 길이를 구함
        for(int i = 2; i <= N; i++){
            // 수열의 이전 위치 값보다 현재 위치 값이 큰 경우
            if(B[maxLength] < A[i]){
                // B배열에 저장된 마지막 수 뒤에 현재 값 저장
                B[++maxLength] = A[i];
                // 현재 위치의 점화식 배열을 이어진 부분수열의 길이로 정의
                D[i] = maxLength;
            }
            else{
                // B배열에서 현재 값이 들어갈 수 있는 위치를 이진 탐색으로 탐색
                idx = binarySearch(1, maxLength, A[i]);
                // 찾은 위치를 현재 위치 값으로 저장
                B[idx] = A[i];
                // 점화식 배열에는 idx값을 저장
                D[i] = idx;
            }
        }

        // 이때 까지 구했던 부분수열의 최대 길이 출력
        System.out.println(maxLength);
        // idx를 maxLength로 초기화
        idx = maxLength;
        // x를 B[maxLength] + 1 즉 최대 길이의 부분 수열에서의 마지막 수(가장 큰 수) + 1로 초기화
        int x = B[maxLength] + 1;
        // 입력 배열과 점화식 배열을 하향식으로 탐색
        for(int i = N; i >=1; i--){
            // idx값과 동일한 위치이며 x로 정의한 값보다 작은 경우(최대 길이가 되는 부분 수열이 여러개 존재할 수 있기 때문)
            if(D[i] == idx && A[i] < x){
                // 정답 배열에 해당 수 추가, 큰 인덱스 위치부터 추가
                ans[idx] = A[i];
                // x 갱신, idx 감소
                x = A[i];
                idx--;
            }
        }

        // 정답 출력
        for(int i = 1; i <= maxLength; i++) System.out.print(ans[i] + " ");
    }

    // 이진 탐색 함수, B배열은 정렬되어 있는 상태이기 때문에 효율적으로 다음 값 입력 위치를 찾기 위함
    public static int binarySearch(int l, int r, int now){
        int mid;
        while(l < r){
            mid = (l + r) / 2;
            if(B[mid] < now) l = mid + 1;
            else r = mid;
        }
        return l;
    }
}
