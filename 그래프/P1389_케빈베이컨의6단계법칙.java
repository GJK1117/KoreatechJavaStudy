import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1389_케빈베이컨의6단계법칙 {
    static int[][] visited; //방문표시 배열 and 깊이 저장 배열
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        ArrayList<Integer>[] friend = new ArrayList[N+1];   //친구관계 인접리스트
        int[] res = new int[N+1];   //케빈베이컨 수 저장 배열
        int min = 99999, ans = 0;   //케빈베이컨 수 최소값, 최소값에 해당하는 사람
        Queue<Integer> queue = new LinkedList<>();  //BFS 큐
        for(int i=1; i<N+1; i++) friend[i] = new ArrayList<>(); //인접리스트 초기화
        for(int i=1; i<M+1; i++){
            //값 입력 ex) 입력: 1 3 -> 1번 3번 친구관계이므로 1번->3번, 3번->1번 모두 연결
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            friend[x].add(y);
            friend[y].add(x);
        }
        //친구 수 만큼 반복(i = 시작 사람)
        for(int i=1; i<N+1; i++){
            visited = new int[N+1][N+1];    //방문표시 배열 and 깊이 저장 배열 초기화
            queue.offer(i); //시작 사람부터 시작
            while(!queue.isEmpty()) {
                Integer x = queue.poll();   //현재 사람
                for (Integer y : friend[x]) {   //현재 사람의 친구들
                    //방문 x
                    if (visited[x][y] > 0) continue;

                    //친구관계일 경우 단계 수 증가
                    visited[x][y] += 1;
                    visited[y][x] += 1;
                    queue.offer(y);
                    //시작 사람과 현재 사람의 친구의 단계 수 증가
                    //(1-3), (3-2) 단계일 때 (1-2)의 단계 수는 1 -> 3 -> 2 이므로 2이다.
                    //여기서 시작 사람은 1, 1의 친구 3의 단계 수에서 +1 해줌
                    if(visited[i][y] == 0) {
                        visited[i][y] = visited[i][x] + 1;
                        visited[y][i] = visited[i][x] + 1;
                    }
                }
            }
            //탐색이 끝나고 시작 사람의 케빈 베이컨 수 저장
            for(int j=1; j<N+1; j++){
                res[i] += visited[i][j];
            }
            //최소 판단해 저장
            if(res[i] < min) {
                min = res[i];
                ans = i;
            }
        }
        System.out.println(ans);
    }
}
