import java.util.Scanner;

public class Main25501{
    static int result;
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int t = sc.nextInt();
        for (int i = 0; i < t; i++){
            result = 0;
            String s = sc.next();
            System.out.println(isPalindrome(s) + " " + result);
        }
    }
    public static int recursion(String s, int l, int r){
        result++;
        if(l >= r) return 1;
        else if(s.charAt(l) != s.charAt(r)) return 0;
        else return recursion(s, l+1, r-1);
    }
    public static int isPalindrome(String s){
        return recursion(s, 0, s.length()-1);
    }
}