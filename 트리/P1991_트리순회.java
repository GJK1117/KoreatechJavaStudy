import java.io.*;

public class P1991_트리순회 {
    // 버퍼 입출력 선언
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    // 이진 트리 선언
    static int[][] tree;

    public static void main(String[] args) throws IOException{
        // 트리 노드 개수 입력
        int N = Integer.parseInt(br.readLine());
        // 이진 트리 초기화
        tree = new int[N][2];

        // 이진 트리 입력
        for(int i = 0; i < N; i++){
            // 스페이스 단위로 문자 입력
           String[] input = br.readLine().split(" ");
           // 부모노드, index 접근을 위해 int형으로 유지
           int x = input[0].charAt(0) - 'A';
           // 자식노드, "." 입력을 구분하기 위해 char형을 유지
           char y = input[1].charAt(0);
           char z = input[2].charAt(0);

           // y, z가 "."이 아니라면 자식노드 추가, 맞다면 -1 처리
           if(y != '.') tree[x][0] = y - 'A';
           else tree[x][0] = -1;
           if(z != '.') tree[x][1] = z - 'A';
           else tree[x][1] = -1;
        }

        // 각 함수(전위, 중위, 후위) 실행 후 결과 출력
        // 시작노드는 항상 0(A)
        preorder(0);
        bw.write("\n");
        bw.flush();
        inorder(0);
        bw.write("\n");
        bw.flush();
        postorder(0);
        bw.write("\n");
        bw.flush();
        bw.close();
    }

    // 전위 탐색 함수
    public static void preorder(int idx) throws IOException {
        // 자식노드가 없는 경우
        if(idx == -1) return;
        bw.write((char)(idx + 'A'));
        preorder(tree[idx][0]);
        preorder(tree[idx][1]);
    }

    // 중위 탐색 함수
    public static void inorder(int idx) throws IOException{
        // 자식노드가 없는 경우
        if(idx == -1) return;
        inorder(tree[idx][0]);
        bw.write((char)(idx + 'A'));
        inorder(tree[idx][1]);
    }

    // 후위 탐색 함수
    public static void postorder(int idx) throws IOException{
        // 자식노드가 없는 경우
        if(idx == -1) return;
        postorder(tree[idx][0]);
        postorder(tree[idx][1]);
        bw.write((char)(idx + 'A'));
    }
}
