import java.util.Scanner;
import java.util.Arrays;
public class Main1546 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        double[] score = new double[n];
        for (int i = 0; i < n; i++){
            score[i] = sc.nextDouble();
        }

        double m = Arrays.stream(score).max().getAsDouble();
        double[] fake = new double[n];
        for (int i = 0; i < n; i++){
            fake[i] = score[i]/m*100;
        }

        double avg = Arrays.stream(fake).sum()/fake.length;
        System.out.println(avg);
    }
}
