import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class P10868_최솟값2 {
    static long[] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int th = 0; //트리 높이
        int length = N;
        while(length != 0){     //트리 높이 구함
            length = length/2;
            th++;
        }
        int treeSize = (int) Math.pow(2, th+1); //트리 사이즈 = 2^k*2
        int start = treeSize/2-1;   //리프노드 시작 인덱스 구하기 -> 리프노드가 바로 원본배열
        //트리 초기화
        tree = new long[treeSize+1];
        for(int i=0; i<tree.length; i++){
            tree[i] = Integer.MAX_VALUE;    //모든 트리값을 최대값으로 초기화
        }
        for(int i=start+1; i<=start+N; i++){
            tree[i] = Long.parseLong(br.readLine());    //리프노드에 입력값 저장
        }
        int k = treeSize-1;
        while(k!=1){
            //현재 트리값이 자신의 부모노드 값보다 작다면 부모노드의 자신의 값 저장 -> 부모노드에 최소값 갱신
            if(tree[k/2] > tree[k]) tree[k/2] = tree[k];
            k--;
        }

        for(int i=0; i<M; i++){
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            //앞서 말했듯 현재 리프노드에 원본배열이 순서대로 저장되어있음 따라서 위치를 리프노드배열에서 시작해주기 위해 리프노드 시작 인덱스 더해줌
            x += start;
            y += start;
            long res = findMin(x, y);   //최소값 찾기
            System.out.println(res);
        };
    }

    //범위 내의 최소값 구하기
    static long findMin(int start, int end){
        long min = Long.MAX_VALUE;
        while(start<=end){
            //이경우는 start가 오른쪽 자식노드일 경우이므로 현재 start의 부모노드는 사용하지 못함
            //따라서 오른쪽 부모노드로 보내주어야함
            //start 노드가 최소인 경우가 있을 수도 있으므로 현재 start노드의 값도 최소를 비교해줌
            if(start%2==1){
                min = Math.min(min, tree[start]);
                start++;    //오른쪽의 부모노드로 보내기 위해 +1
            }
            start /= 2;
            //이경우는 end가 왼쪽 자식노드일 경우이므로 현재 end의 부모노드는 사용하지 못함
            //따라서 왼쪽 부모노드로 보내주어야함
            //이 경우 또한 end 노드가 최소일 수 있으므로 현재 end노드의 값도 최소를 비교해줌
            if(end%2==0){
                min = Math.min(min, tree[end]);
                end--;      //왼쪽의 부모노드로 보내기 위해 -1
            }
            end/=2;
        }
        return min;
    }
}
