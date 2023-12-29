import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class P2178_미로탐색 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        Queue<Index> queue = new LinkedList<>();
        int[][] maze = new int[N][M];       //미로의 값, 최단 경로값 저장할 배열
        int[][] visited = new int[N][M];    //방문여부 배열
        int[] dx = {1, 0, -1, 0}, dy = {0, 1, 0, -1};   //down, right, up, left

        //미로 값 입력
        for(int i=0; i<N; i++){
            String[] sArr = br.readLine().split("");    //하나의 행 붙여서 입력후 분리하여 저장
            for(int j=0; j<M; j++){
                maze[i][j] = Integer.parseInt(sArr[j]);       //분리한 값 미로 배열에 하나씩 저장
            }
        }

        queue.offer(new Index(0,0));    //시작지점 offer
        while(!queue.isEmpty()){
            Index loc = queue.poll();
            for(int i=0; i<4; i++){
                int nx = loc.x + dx[i]; //다음 x 위치
                int ny = loc.y + dy[i]; //다음 y 위치

                //미로범위 밖으로 나갔을 때 예외처리
                if(nx<0 || nx>=N || ny<0 || ny>=M) continue;

                //갈수있는길이고 방문하지 않았을 시
                if(maze[nx][ny]==1 && visited[nx][ny]==0){
                    queue.offer(new Index(nx, ny));     //큐에 추가
                    maze[nx][ny] += maze[loc.x][loc.y]; //이동한횟수 추가
                    visited[nx][ny] = 1;    //방문표시
                }
            }
        }
        System.out.println(maze[N-1][M-1]);
    }

    //(x, y) 지점으로 사용할 클래스
    static class Index{
        int x;
        int y;
        public Index(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
