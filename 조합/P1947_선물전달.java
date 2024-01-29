import java.io.IOException;
import java.util.Scanner;

public class P1947_선물전달 {
    static Scanner sc = new Scanner(System.in);
    static long[] Derangement;
    public static void main(String[] args) throws IOException{
        int N = sc.nextInt();
        Derangement = new long[N+1];
        Derangement[1] = 0;
        Derangement[2] = 1;
        for(int i = 3; i <= N; i++){
            Derangement[i] = (i - 1) * (Derangement[i - 1] + Derangement[i - 2]) % 1000000000;
        }
        System.out.println(Derangement[N]);
    }
}
