import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1516_게임개발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());   //건물 종류 수
        Queue<Integer> queue = new LinkedList<>();  //BFS 탐색할 큐
        ArrayList<ArrayList<Integer>> list = new ArrayList<>(); //인접 리스트
        int[] res = new int[N+1];   //이전 건물 짓는 시간 누적값 저장 배열
        for(int i=0; i<N+1; i++) {
            list.add(new ArrayList<>());    //인접리스트 초기화
        }
        int[] degree = new int[N+1];        //진입차수 배열
        int[] time = new int[N+1];          //건물 시간 배열
        for(int i=1; i<N+1; i++){
            st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            time[i] = t;        //건물 시간 저장
            while(true) {
                int x = Integer.parseInt(st.nextToken());
                if(x == -1) break;
                list.get(x).add(i); //먼저 짓는 건물 리스트에 나중에 짓는 건물을 넣음
                degree[i]++;        //진입 차수 증가
            }
        }
        for(int i=0; i<N+1; i++){
            if(degree[i]==0) {
                queue.add(i);       //위상정렬 수행
            }
        }
        while(!queue.isEmpty()){
            Integer building = queue.poll();    //이전 건물
            for (Integer b : list.get(building)) {  //다음 건물 검사
                degree[b]--;        //다음 건물 진입차수 -1
                //이전 누적값 중 최대값을 고려해준다.
                //현재 건물의 비용은 출력해줄 때 더해주므로 고려 X
                //앞에 있는 건물의 짓는 시간이 끝나야 지을 수 있으므로 최대시간을 고려해주어야한다.
                if(res[b] < res[building] + time[building]) res[b] = res[building] + time[building];
                //진입차수 0이면 큐에 넣음
                if(degree[b]==0){
                    queue.offer(b);
                }
            }
        }
        //결과값 출력
        //출력할 때 자신의 건물 짓는 시간 더해줌
        for(int i=1; i<N+1; i++) System.out.println(res[i] + time[i]);
    }
}
