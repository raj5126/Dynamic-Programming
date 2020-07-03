import java.util.Scanner;
import java.util.*;
public class Knapsack{

	public static void main(String args []){

		Scanner sc = new Scanner(System.in);
		int maxprofit;

		System.out.println("Which Problem you want to simulate : ");
		System.out.println("1 - Knapsack");
		System.out.println("2 - Sum of Subset");
		System.out.println("3 - Equal Sum Partition");
		System.out.println("4 - Count Sum of Subset");
		System.out.println("5 - Minimum Subset Sum difference");
		System.out.println("6 - Count the number of Subset of given Difference");
		System.out.println("7 - Taget Sum Problem (same as Problem No . 6) ");
		System.out.println("Enter Your choice");
		int problem = sc.nextInt();

		if (problem == 1){
			System.out.println("Enter N : "); 
			int n = sc.nextInt();

			System.out.println("Enter W : ");
			int w = sc.nextInt();

			System.out.println("Enter Weight Matrix : ");
			int wt[] = new int[n];
			for (int i=0;i<n;i++){
				wt[i] = sc.nextInt();
			}

			System.out.println("Enter Value Matrix : ");
			int val[] = new int[n];
			for (int i=0;i<n;i++){
				val[i] = sc.nextInt();
			}

			while (true){
 			System.out.println("Which Method You want to use : 1-recursion 2-topdown");
  			int choice = sc.nextInt();

  			if (choice==1){
  				maxprofit = knapsack_recursion(wt,val,w,n);
  			}
  			else if (choice==2){
  				maxprofit = knapsack_topdown(wt,val,w,n);	
  			}
  			else{
  				System.out.println("Wrong Choice Try Again");
  				return;
  			}
		
			System.out.println("Max-Profit is " + maxprofit);
	 	}

		}

		else if (problem == 2){
			System.out.println("Enter N : "); 
			int n = sc.nextInt();

			System.out.println("Enter Sum : ");
	 		int sum = sc.nextInt();

	 		System.out.println("Enter array : ");
	 		int arr[] = new int[n];
	 		for (int a=0;a<n;a++){
	 			arr[a] = sc.nextInt();
	 		}

	 		boolean result = sum_of_subset(arr,sum,n); 
	 		System.out.println("Result is " + result);

		}

		else if (problem == 3){

			int sum = 0;
			System.out.println("Enter N : "); 
			int n = sc.nextInt();

			System.out.println("Enter array : ");
	 		int arr[] = new int[n];
	 		for (int a=0;a<n;a++){
	 			arr[a] = sc.nextInt();
	 			sum = sum + arr[a];
	 		}

	 		if (sum%2!=0){
	 			System.out.println("Not Possible");
	 			return;
	 		}

	 		else{
	 			boolean result = sum_of_subset(arr,sum/2,n);
	 			if (result==true){
	 				System.out.println("Equal sum partition is Possible");
	 			}
	 			else{
	 				System.out.println("Equal sum partition is Not Possible");
	 			}
	 		}
	 	}

	 	else if (problem == 4){

	 		System.out.println("Enter N : ");
	 		int n = sc.nextInt();

	 		System.out.println("Enter array : ");
	 		int arr[] = new int[n];
	 		for (int a=0;a<n;a++){
	 			arr[a] = sc.nextInt();
	 		}

	 		System.out.println("Enter Sum ");
	 		int sum = sc.nextInt();

	 		int total_count = count_sum_of_subset(arr,n,sum);
	 		System.out.println("Total count is : " + total_count);
	 	}

	 	else if (problem == 5){
	 		int range = 0;
	 		System.out.println("Enter N : "); 
			int n = sc.nextInt();

	 		System.out.println("Enter array : ");
	 		int arr[] = new int[n];
	 		for (int a=0;a<n;a++){
	 			arr[a] = sc.nextInt();
	 			range = range + arr[a];
	 		}

	 		int diff = minimum_subset_sum_difference(arr, n, range);
	 		System.out.println("Minimum Subset Sum Difference is " + diff);

	 	}

		else if (problem == 6){

			int range = 0;

			System.out.println("Enter the Difference : ");
			int diff = sc.nextInt();

			System.out.println("Enter N : "); 
			int n = sc.nextInt();

	 		System.out.println("Enter array : ");
	 		int arr[] = new int[n];
	 		for (int a=0;a<n;a++){
	 			arr[a] = sc.nextInt();
	 			range = range + arr[a];
	 		}

			int count = count_number_of_subset_of_given_diff(arr, n, range, diff);
			System.out.println("Number of subset of given diff is " + count);
		}

		else if (problem == 7){

			int range = 0;

			System.out.println("Enter the Target Sum : ");
			int target_sum = sc.nextInt();

			System.out.println("Enter N : "); 
			int n = sc.nextInt();

	 		System.out.println("Enter array : ");
	 		int arr[] = new int[n];
	 		for (int a=0;a<n;a++){
	 			arr[a] = sc.nextInt();
	 			range = range + arr[a];
	 		}
			
			int count = target_sum_subset(arr,n,range,target_sum);
			System.out.println("Target sum of subset occurs " + count + " times.");
		}	

		else{
			System.out.println("Wrong choice Please try again");
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

	public static int knapsack_recursion(int[] wt,int[] val,int w,int n){
			if (n==0 || w==0)
				return 0;
			if (wt[n-1]<=w)
				return max(val[n-1] + knapsack_recursion(wt,val,w-wt[n-1],n-1),knapsack_recursion(wt,val,w,n-1));
			else if (wt[n-1]>w)
				return knapsack_recursion(wt,val,w,n-1);
			return 0;
		}

	public static int knapsack_topdown(int[] wt,int[] val,int w,int n){
		int [][] t = new int[n+1][w+1];

		for (int q=0;q<w+1;q++){
			t[0][q] = 0;
		}

		for (int r=0;r<n+1;r++){
			t[r][0] = 0;
		}

		for (int i=1;i<n+1;i++){
			for (int j=1;j<w+1;j++){
				if (wt[i-1]<=j)
					t[i][j] = max(val[i-1]+t[i-1][j-wt[i-1]],t[i-1][j]);
				else 
					t[i][j] = t[i-1][j];
			}
		}

		System.out.println("Knapsack Matrix : ");
		for (int i=1;i<n+1;i++){
			for (int j=1;j<w+1;j++){
				System.out.print(t[i][j] + "   ");
			}
			System.out.println();
		}
		return t[n][w];
	}

	public static boolean sum_of_subset(int[] arr,int sum,int n){
		boolean [][] t = new boolean[n+1][sum+1];

		for (int q=0;q<sum+1;q++){
			t[0][q] = false;
		}

		for (int r=0;r<n+1;r++){
			t[r][0] = true;
		}

		for (int i=1;i<n+1;i++){
			for (int j=1;j<sum+1;j++){
				if (arr[i-1]<=j)
					t[i][j] = t[i-1][j-arr[i-1]] || t[i-1][j];
				else 
					t[i][j] = t[i-1][j];
			}
		}

		System.out.println("Sum of Subset Matrix : ");
		for (int i=1;i<n+1;i++){
			for (int j=1;j<sum+1;j++){
				System.out.print(t[i][j] + "   ");
			}
			System.out.println();
		}

		return t[n][sum];
	}

	public static int count_sum_of_subset(int [] arr,int n,int sum){
		int [][] t = new int[n+1][sum+1];

		for (int q=0;q<sum+1;q++){
			t[0][q] = 0;
		}

		for (int r=0;r<n+1;r++){
			t[r][0] = 1;
		}

		for (int i=1;i<n+1;i++){
			for (int j=1;j<sum+1;j++){
				if (arr[i-1]<=j)
					t[i][j] = t[i-1][j-arr[i-1]] + t[i-1][j];
				else 
					t[i][j] = t[i-1][j];
			}
		}

		System.out.println("Count Sum of Subset Matrix : ");
		for (int i=1;i<n+1;i++){
			for (int j=1;j<sum+1;j++){
				System.out.print(t[i][j] + "   ");
			}
			System.out.println();
		}

		return t[n][sum]; 
	}	

	public static int minimum_subset_sum_difference(int [] arr,int n,int range){
		boolean [][] t = new boolean[n+1][range+1];
		ArrayList <Integer> vector = new ArrayList<Integer>();
		int min_diff = 9999;

		for (int q=0;q<range+1;q++){
			t[0][q] = false;
		}

		for (int r=0;r<n+1;r++){
			t[r][0] = true;
		}

		for (int i=1;i<n+1;i++){
			for (int j=1;j<range+1;j++){
				if (arr[i-1]<=j)
					t[i][j] = t[i-1][j-arr[i-1]] || t[i-1][j];
				else 
					t[i][j] = t[i-1][j];
			}
		}

		for (int r = 0;r<range/2+1;r++){
			if (t[n][r] == true){
				vector.add(r);
			}
		}

		Iterator itr = vector.iterator();
		while(itr.hasNext()){
			int value = (Integer)itr.next();
			min_diff = min(min_diff,range - 2*value);
		}
		
		return min_diff;
	}

	public static int count_number_of_subset_of_given_diff(int [] arr,int n,int sum, int difference){
		int sum_of_one_subset = (difference + sum)/2;
		System.out.println("Sum of one Subset is " + sum_of_one_subset);
		return count_sum_of_subset(arr,n,sum_of_one_subset);
	}

	public static int target_sum_subset(int[] arr,int n,int range,int target_sum){
		return count_number_of_subset_of_given_diff(arr,n,range,target_sum);
	}

}
