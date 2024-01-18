import java.io.*;
import java.util.StringTokenizer;

public class P2042_구간합구하기3 {
    // 버퍼 입출력 및 토큰 입력 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static StringTokenizer st;
    // 세그먼트 트리 선언
    static long[] seg;
    public static void main(String[] args) throws IOException{
        // 문제 조건 N, M, K 입력
        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 트리의 깊이 계산
        int height = 0, len = N;
        while(len != 0){
            len /= 2;
            height++;
        }

        // 트리의 크기(노드의 수) 계산 후 트리 초기화
        int treeSize = (int) Math.pow(2, height + 1);
        int sIdx = treeSize / 2 - 1;
        // index를 1부터 사용하려고 함
        seg = new long[treeSize + 1];

        // 트리 입력
        for(int i = sIdx + 1; i <= sIdx + N; i++){
            seg[i] = Long.parseLong(br.readLine());
        }

        // 세그먼트 트리 계산
        setTree(treeSize - 1);

        // M + K 번 입력
        for(int i = 0; i < M + K; i++){
            st = new StringTokenizer(br.readLine());
            long a = Long.parseLong(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            // a이 1이면 세그먼트 트리 값 변경, a가 2이면 구간 합 계산
            if(a == 1) changeTree(sIdx + b, c);
            else if(a == 2) bw.write(getIntervalSum(b + sIdx, (int)c + sIdx) + "\n");
            else return;
        }

        bw.flush();
        bw.close();
    }

    // 세그먼트 트리 계산 함수
    public static void setTree(int size){
        // 자식노드의 합을 부모노드에 저장, index가 큰 쪽부터 계산 시작
        while(size != 1) seg[size/2] += seg[size--];
    }

    // 세그먼트 트리 값 변경 함수
    public static void changeTree(int idx, long val){
        // 이전 값과 변경하려는 값의 차이 계산
        long diff = val - seg[idx];
        // 현재 세그먼트 트리에서 값 차이만큼 적용
        while(idx > 0){
            seg[idx] += diff;
            idx /= 2;
        }
    }

    // 구간 합 계산 함수
    public static long getIntervalSum(int s, int e){
        // 구간 합 변수
        long partSum = 0;
        // 구간 [s, e]의 합 계산
        while(s <= e){
            // s가 홀수인 경우 해당 노드의 부모노드를 계산에 포함하지 않기 위해(구간에서 벗어나기 때문)
            // 값을 더하고 index가 하나 더 큰 위치로 설정
            if(s % 2 == 1) partSum += seg[s++];
            // e가 짝수인 경우 해당 노드의 부모노드를 계산에 포함하지 않기 위해(구간에서 벗어나기 때문)
            // 값을 더하고 index가 하나 작은 위치로 설정
            if(e % 2 == 0) partSum += seg[e--];
            // s, e 모두 부모노드 위치로 설정
            s /= 2;
            e /= 2;
        }
        // 구간 합 반환
        return partSum;
    }
}
