import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class P1377_버블소트1 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Item[] A = new Item[N];       //Item 객체 배열 생성

        for (int i = 0; i < N; i++) {
            A[i] = new Item(Integer.parseInt(br.readLine()), i);    //객체 값 입력 -> 값, 인덱스
        }
        Arrays.sort(A); // A 배열 정렬 -> 시간복잡도 O(nlogn)

        int Max = 0;
        for (int i = 0; i < N; i++) {
            //각 객체의 인덱스 값 이용
            //정렬 전 index - 정렬 후 index 값의 최대값 저장
            if (Max < A[i].index - i) Max = A[i].index - i;
        }
        System.out.println(Max + 1);
    }
}
class Item implements Comparable<Item> {
    int value;
    int index;

    public Item(int value, int index) {
        super();
        this.value = value;
        this.index = index;
    }

    @Override
    public int compareTo(Item o) { // value 기준 오름차순 정렬
        return this.value - o.value;
    }
}
