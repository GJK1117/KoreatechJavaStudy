import java.io.*;
import java.util.*;

public class P2252_줄세우기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Queue<Integer> queue = new LinkedList<>();
        ArrayList<Integer>[] arr = new ArrayList[N+1];  //인접리스트
        for(int i=0; i<N+1; i++) arr[i] = new ArrayList<>();    //인접리스트 초기화
        ArrayList<Integer> res = new ArrayList<>();     //결과값 리스트
        int[] degree = new int[N+1];    //진입차수 저장 배열
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            arr[x].add(y);      //인접 리스트에 값저장, 단방향이므로 한쪽 방향으로만 저장
            degree[y]++;        //진입차수 증가
        }
        //진입차수가 0인 값 큐에 저장 -> 진입차수가 0인 즉 누구와도 연결되지 않은 노드들은 정렬과 상관 없으므로 결과값에 모두 저장
        //큐에도 넣어서 다음 연결된 노드들의 진입차수를 감소시킨다.
        for(int i=1; i<=N; i++){
            if(degree[i]==0) {
                queue.offer(i);
                res.add(i);
            }
        }
        //진입차수 0인 노드들부터 시작 만약 연결된 값이 있으면 탐색하고 진입차수 감소
        while(!queue.isEmpty()){
            Integer k = queue.poll();
            for (Integer a : arr[k]) {
                --degree[a];
                //진입차수가 0이 되면 큐에 넣고 결과값에 저장
                if(degree[a] == 0){
                    queue.offer(a);
                    res.add(a);
                }
            }
        }
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        for (Integer re : res) {
            bw.write(re + " ");
        }
        bw.flush();
        bw.close();
    }
}
