import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P1948_임계경로 {
    static boolean[] visited;   //방문 표시 배열
    static int[] degree;        //진입 차수 배열
    static int[] time;          //각 도착 최대시간 저장 배열
    static Queue<Integer> queue = new LinkedList<>();
    static ArrayList<ArrayList<City>> cities = new ArrayList<>();   //인접 리스트
    static ArrayList<ArrayList<City>> reverseCities = new ArrayList<>();    //역방향 인접리스트
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        int start, end, res = 0;
        visited = new boolean[N+1];
        degree = new int[N+1];
        time = new int[N+1];
        for(int i=0; i<N+1; i++) {
            cities.add(new ArrayList<>());
            reverseCities.add(new ArrayList<>());
        }
        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            cities.get(a).add(new City(b, c));          //인접 리스트 값 추가
            reverseCities.get(b).add(new City(a, c));   //역방향 인접 리스트 값 추가
            degree[b]++;    //진입차수 증가
        }
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        //위상정렬 수행
        queue.offer(start);
        while(!queue.isEmpty()) {
            Integer k = queue.poll();
            for (City city : cities.get(k)) {
                //도착 최대시간 갱신
                time[city.endPoint] = Math.max(time[city.endPoint], time[k] + city.roadTime);
                degree[city.endPoint]--;
                if (degree[city.endPoint] == 0) {
                    queue.offer(city.endPoint);
                }
            }
        }
        //도착 최대시간으로 도착점부터 역방향으로 탐색
        //역방향 인접 리스트 사용
        queue.offer(end);
        while(!queue.isEmpty()){
            Integer k = queue.poll();
            for (City reverseCity : reverseCities.get(k)) {
                //1분도 쉬지 않으려면 도착 최대시간과 같아야한다.
                //따라서 현재 노드의 도착 최대시간과 전 노드의 도착최대시간 + 두 노드 사이 걸리는 시간이 같아야 최대값을 가지므로 1분도 쉬지못한다.
                if(time[k]==reverseCity.roadTime+time[reverseCity.endPoint]){
                    res++;
                    //중복된 길 세지 않도록 해주기 위해 방문표시
                    if(!visited[reverseCity.endPoint]) {
                        queue.offer(reverseCity.endPoint);
                        visited[reverseCity.endPoint] = true;
                    }
                }
            }
        }
        System.out.println(time[end]);
        System.out.println(res);
    }

    static class City{
        int endPoint;   //도착점
        int roadTime;   //걸리는 시간

        public City(int endPoint, int roadTime) {
            this.endPoint = endPoint;
            this.roadTime = roadTime;
        }
    }
}
