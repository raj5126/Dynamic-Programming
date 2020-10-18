import java.util.*;
import java.util.Scanner;
public class LCS_related_problems {
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Which Problem You want to Show ? ");
        System.out.println("1 - Longest Common Subsequence (Recursion)");
        System.out.println("2 - Longest Common Subsequence (Bottom up - Memorization)");
        System.out.println("3 - Longest Common Subsequence (Top Down - Dynamic method)");
        System.out.println("4 - Longest Common Sequence");
        System.out.println("5 - Print Longest Common Subseqeunce");
        System.out.print("Your choice : ");
        int choice = sc.nextInt();

        System.out.print("Enter String X : ");
        String x = sc.next();
        int n = x.length();

        System.out.print("Enter String Y : ");
        String y= sc.next();
        int m = y.length();
        
        if (choice==1){
            int lcs_length = Longest_common_subsequence_recursion(x,y,n,m);
            System.out.println("Longest common subsequence Length is " + lcs_length);
        }
        else if (choice==2){
            int lcs_length = Longest_common_subsequence_memorization(x, y, n, m);
            System.out.println("Longest common subsequence Length is " + lcs_length);
        }
        else if (choice==3){
            int lcs_length = Longest_common_subsequence_top_down(x, y, n, m);
            System.out.println("Longest common subsequence Length is " + lcs_length);
        }
        else if(choice == 4){
            int lcsequence_length = Longest_common_sequence(x, y, n, m);
            System.out.println("Longest common sequence is : " + lcsequence_length);
        }
        else if (choice == 5){
            String lcs = print_longest_common_subsequence(x , y ,n, m);
            System.out.println("String is : " + lcs);
        }
    }

    public static int max(int a,int b){
        if (a>b)
            return a;
        else
            return b;
    }

    

    public static void print_arr(int[][] arr, int row, int column){
        for (int i = 1;i<row+1;i++){
            for (int j = 1;j<column+1;j++)  
                System.out.print(arr[i][j] + "  ");
            System.out.println();
        }
    }

    public static String print_longest_common_subsequence(String x, String y,int n ,int m){
        int t[][] = new int[n+1][m+1];
        for (int r =0;r<n+1;r++){
            t[r][0] = 0;
        }
        for (int c =0;c<m+1;c++){
            t[0][c] = 0;
        }
        for (int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if (x.charAt(i-1)==y.charAt(j-1)){
                    t[i][j] = 1 + t[i-1][j-1];
                }
                else{
                    t[i][j] = max(t[i][j-1], t[i-1][j]);
                }
            }
        }
        int i = n;
        int j= m;
        String LCS = "";
        while (i > 0 && j > 0){
            if (x.charAt(i-1) == y.charAt(j-1)){
                LCS = x.charAt(i-1) + LCS;
                i=i-1;
                j=j-1;
            }
            else{
                if (t[i][j-1]>t[i-1][j])
                    j=j-1;
                else if (t[i-1][j]>t[i][j-1])
                    i=i-1;
            }
        }
        return LCS;
    }

    public static int Longest_common_subsequence_recursion(String x, String y,int n, int m) {
        if (n==0 || m==0)
            return 0;     
        if (x.charAt(n-1) == y.charAt(m-1))
            return 1 + Longest_common_subsequence_recursion(x, y, n-1, m-1);
        else
            return max(Longest_common_subsequence_recursion(x, y, n-1, m),Longest_common_subsequence_recursion(x, y, n, m-1));  
    }

    public static int Longest_common_subsequence_memorization(String x, String y,int n,int m) {
        int t[][] = new int[n+1][m+1];
        for (int i = 0;i<n+1;i++){
            for (int j = 0;j<m+1;j++){
                t[i][j] = -1;
            }
        }
        if (n==0 || m==0)
            return 0; 
        if (t[n][m]!=-1)
            return t[n][m];
        if (x.charAt(n-1) == y.charAt(m-1))
            return t[n][m] = 1 + Longest_common_subsequence_recursion(x, y, n-1, m-1);
        else
            return t[n][m] = max(Longest_common_subsequence_recursion(x, y, n-1, m),Longest_common_subsequence_recursion(x, y, n, m-1));
    }

    public static int Longest_common_subsequence_top_down(String x, String y, int n, int m) {
        int t[][] = new int[n+1][m+1];
        for (int r =0;r<n+1;r++){
            t[r][0] = 0;
        }
        for (int c =0;c<m+1;c++){
            t[0][c] = 0;
        }
        for (int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if (x.charAt(i-1)==y.charAt(j-1)){
                    t[i][j] = 1 + t[i-1][j-1];
                }
                else{
                    t[i][j] = max(t[i][j-1], t[i-1][j]);
                }
            }
        }
        return t[n][m];
    }

    public static int Longest_common_sequence (String x, String y, int n,int m) {

        int t[][] = new int[n+1][m+1];
        for (int r =0;r<n+1;r++){
            t[r][0] = 0;
        }
        for (int c =0;c<m+1;c++){
            t[0][c] = 0;
        }
        for (int i=1;i<n+1;i++){
            for(int j=1;j<m+1;j++){
                if (x.charAt(i-1)==y.charAt(j-1)){
                    t[i][j] = 1 + t[i-1][j-1];
                }
                else{
                    t[i][j] = t[i-1][j-1];
                }
            }
        }
        print_arr(t, n, m);
        return t[n][m];       
    }

    

}