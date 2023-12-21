import java.io.*;

public class P10989_수정렬하기3 {
    public static void main(String[] args) throws IOException {
        // 버퍼 입력, 출력 사용
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력할 숫자 개수 변수
        int N = Integer.parseInt(br.readLine());
        // 입력 배열 변수 선언
        int[] A = new int[N];

        // 입력
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        // 기수 정렬
        radixSort(A);

        // 정렬된 결과를 출력
        for (int i = 0; i < N; i++) {
            bw.write(A[i] + "\n");
        }

        bw.flush();
        bw.close();
    }

    // 기수 정렬 함수
    public static void radixSort(int[] A) {
        // 중간 결과를 저장할 임시 배열 변수
        int[] output = new int[A.length];
        // 자리수를 지정할 변수
        int jarisu = 1;
        // 자리수를 지정할 때마다 카운트할 변수
        int count = 0;

        // 최대 자리수가 5자리수 이므로 5번 반복
        while (count != 5) {
            // 0~9까지의 숫자를 저장할 배열
            int[] bucket = new int[10];

            // 각 자리수의 숫자를 카운트
            for (int j : A) {
                bucket[(j / jarisu) % 10]++;
            }

            // 각 자리수의 숫자를 누적합으로 변경
            for (int i = 1; i < 10; i++) {
                bucket[i] += bucket[i - 1];
            }

            // 누적합을 이용하여 임시 배열에 정렬
            for (int i = A.length - 1; i >= 0; i--) {
                output[bucket[(A[i] / jarisu) % 10] - 1] = A[i];
                bucket[(A[i] / jarisu) % 10]--;
            }

            // 임시 배열의 값을 원래 배열에 복사
            System.arraycopy(output, 0, A, 0, A.length);

            // 자리수를 증가시킴
            jarisu *= 10;
            // 자리수를 증가시킨 횟수를 카운트
            count++;
        }
    }
}
