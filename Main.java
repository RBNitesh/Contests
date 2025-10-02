import java.util.*;

// problem-1
class Solution1 {
    public int[] decimalRepresentation(int n) {

        List<Integer> list = new ArrayList<>();
        int placeVal = 1;
        while(n > 0){
            int digit = n % 10;

            if(digit != 0){
                list.add(digit * placeVal);
            }

            n /= 10;
            placeVal *= 10;
        }

        int size = list.size();
        int[] arr = new int[size];
        int idx = 0;
        for(int i=size-1; i>=0; i--){
            arr[idx++] = list.get(i);
        }

        return arr;
    }
}

//problem-2
class Solution2 {
    public long splitArray(int[] nums) {
        int n = nums.length;
        
        long leftSum = 0;
        int prev = Integer.MIN_VALUE;
        int i = 0;
        for( ; i<n; i++){
            if(nums[i] > prev){
                leftSum += nums[i];
                prev = nums[i];
            }
            else break;
        }

        long rightSum = 0;
        prev = Integer.MIN_VALUE;
        int j = n-1;
        for( ; j>=0; j--){
            if(nums[j] > prev){
                rightSum += nums[j];
                prev = nums[j];
            }
            else break;
        }

        if(i <= j) return -1;
        if(i==j+1) return Math.abs(leftSum-rightSum);

        return Math.min(Math.abs(leftSum - (rightSum - nums[j+1])), 
        Math.abs(rightSum - (leftSum - nums[i-1])));
    }
}

// problem-3
class Solution3 {
    static List<Integer> list;
    static int[][][] dp;
    static int mod = 1000000007;
    public int zigZagArrays(int n, int l, int r) {

        dp = new int[n+1][2001][3];
        for(int i=0; i<n+1; i++){
            for(int j=0; j<2001; j++){
                for(int k=0; k<3; k++){
                    dp[i][j][k] = -1;
                }
            }
        }
        
        list = new ArrayList<>();
        int cnt = 0;
        for(int i=l; i<=r; i++){
            list.add(i);
            cnt += countZigZag(n-1, l, r, 0);
            cnt %= mod;
            // backtracking
            list.remove(list.size()-1);
        }
        return cnt;
    }

    private static int countZigZag(int n, int l, int r, int sign){
        if(n==0) return 1;
        
        int last = list.get(list.size()-1);
        if(dp[n][last][sign+1] != -1) return dp[n][last][sign+1];

        int res = 0;
        if(sign==0){
            for(int i=l; i<=r; i++){
                if(i != last){
                    list.add(i);
                    if(i < last){
                        res += countZigZag(n-1, l, r, 1);
                    }
                    else{
                        res += countZigZag(n-1, l, r, -1);
                    }
                    res %= mod;
                    
                    list.remove(list.size()-1);
                }
            }
        }
        else if(sign==1){
            for(int i=last+1; i<=r; i++){
                list.add(i);
                res += countZigZag(n-1, l, r, -1);
                res %= mod;
                
                list.remove(list.size()-1);
            }
        }
        else{
            for(int i=l; i<last; i++){
                list.add(i);
                res += countZigZag(n-1, l, r, 1);
                res %= mod;
                
                list.remove(list.size()-1);
            }
        }

        dp[n][last][sign+1] = res;

        return dp[n][last][sign+1];
    }
}


public class Main{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // problem-1
        // Solution1 s1 = new Solution1();
        // System.out.print("Enter number: ");
        // int n = sc.nextInt();
        // int[] res = s1.decimalRepresentation(n);

        // for(int item : res) System.out.print(item + " ");


        // problem-2
        // Solution2 s2 = new Solution2();
        // int[] arr = {2,6};
        // System.out.println(s2.splitArray(arr));

        // problem-3
        Solution3 s3 = new Solution3();
        System.out.println(s3.zigZagArrays(3, 1, 3));
        System.out.println();
    }
}