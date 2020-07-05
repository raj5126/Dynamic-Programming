import java.util.*;
import java.util.Scanner;
public class Unbounded_Knapsack_Related_Problem{
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Problem You want to Show");
        System.out.println("1 - Unbounded Knapsack");
        System.out.println("2 - Rodd Cutting Problem");
        System.out.println("3 - Coin Changed (Maximum numbers of ways)");
        System.out.println("4- Coin Changed (Minimum numbers of ways)");
        int choice = sc.nextInt();
        if (choice == 1){

            System.out.println("Enter N : ");
            int n = sc.nextInt();
            int weigth[] = new int[n];
            int value[] = new int[n];

            System.out.println("Enter Weight Array :");
            for (int w=0;w<n;w++){
                weigth[w] = sc.nextInt();
            }

            System.out.println("Enter Value Array :");
            for (int v=0;v<n;v++){
                value[v] = sc.nextInt();
            }

            System.out.println("Enter Total capacity");
            int capacity = sc.nextInt();

            int max_profit = Unbounded_Knapsack(weigth,value,n,capacity);
            System.out.println("Max Profit is " + max_profit);

        }
        else if (choice == 2){  

            System.out.println("Enter Length of Road N : ");
            int n = sc.nextInt();
            int length[] = new int[n];
            int price[] = new int[n];

            System.out.println("Intializing Length Array");
            for (int l=0;l<n;l++){
                length[l] = l+1;
            }

            System.out.println("Enter Price Array :");
            for (int p=0;p<n;p++){
                price[p] = sc.nextInt();
            }

            int max_profit = Unbounded_Knapsack(length,price,n,n);
            System.out.println("Max Profit is " + max_profit);

        }
        else if (choice == 3){

            System.out.println("Enter Number of Coins : ");
            int n = sc.nextInt();

            System.out.println("Enter Array of Coins : ");
            int coins[] = new int[n];
            for (int c=0;c<n;c++){
                coins[c] = sc.nextInt();
            }

            System.out.println("Enter weigth : ");
            int weigth = sc.nextInt();

            int number_of_ways = coin_changed_number_of_maximum_ways(coins,weigth,n);
            System.out.println("Maximum number of ways " + number_of_ways);
            
        }
        else if (choice == 4){
            
            System.out.println("Enter Number of Coins : ");
            int n = sc.nextInt();

            System.out.println("Enter Array of Coins : ");
            int coins[] = new int[n];
            for (int c=0;c<n;c++){
                coins[c] = sc.nextInt();
            }

            System.out.println("Enter weigth : ");
            int weigth = sc.nextInt();

            int number_of_coins = coin_changed_minimum_number_of_coins(coins,weigth,n);
            System.out.println("Minimum number of coins  " + number_of_coins);
        }
        else {
            System.out.println("Wrong Choice Try again.");
            return;           
        }
    }

    public static int max(int a,int b){
        if (a>b)
            return a;
        else
            return b;
    }

    public static int min(int a,int b){
        if (a<b)
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

    public static int Unbounded_Knapsack(int[] weigth,int[] value,int n,int capacity){
        int [][] t = new int[n+1][capacity+1];

		for (int q=0;q<capacity+1;q++){
			t[0][q] = 0;
		}

		for (int r=0;r<n+1;r++){
			t[r][0] = 0;
        }
        
        for (int i=1;i<n+1;i++){
			for (int j=1;j<capacity+1;j++){
				if (weigth[i-1]<=j)
					t[i][j] = max(value[i-1]+t[i][j-weigth[i-1]],t[i-1][j]);
				else 
					t[i][j] = t[i-1][j];
			}
        }
        print_arr(t, n, capacity);
        return t[n][capacity];
    }

    public static int coin_changed_number_of_maximum_ways(int[] coins,int weigth,int n) {
        
        int t[][] = new int[n+1][weigth+1];

        for (int q=0;q<weigth+1;q++){
			t[0][q] = 0;
		}

		for (int r=0;r<n+1;r++){
			t[r][0] = 1;
        }

        for (int i=1;i<n+1;i++){
			for (int j=1;j<weigth+1;j++){
				if (coins[i-1]<=j)
					t[i][j] = t[i][j-coins[i-1]]+t[i-1][j];
				else 
					t[i][j] = t[i-1][j];
			}
        }
        print_arr(t, n, weigth);
        return t[n][weigth];
    }

     public static int coin_changed_minimum_number_of_coins(int[] coins,int weigth,int n) {
        
        int t[][] = new int[n+1][weigth+1];

        for (int r=0;r<n+1;r++){
			t[r][0] = 0;
        }

        for (int q=0;q<weigth+1;q++){
			t[0][q] = Integer.MAX_VALUE-1;
		}

        for (int j=1;j<weigth+1;j++){
            if (j % coins[0] == 0){
                t[1][j] = j/coins[0];
            }
            else{
                t[1][j] = Integer.MAX_VALUE - 1;
            }
             
        }

        for (int i=2;i<n+1;i++){
			for (int j=1;j<weigth+1;j++){
				if (coins[i-1]<=j)
					t[i][j] = min(t[i][j-coins[i-1]]+1,t[i-1][j]);
				else 
					t[i][j] = t[i-1][j];
			}
        }
        print_arr(t, n, weigth);
        return t[n][weigth];
		
    }
}