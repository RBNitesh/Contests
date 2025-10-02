import java.util.*;

// problem-1
class Solution1 {
    public String majorityFrequencyGroup(String s) {
        int n = s.length();

        // stores the freq of chars in s
        int[] freq = new int[26];

        for(int i=0; i<n; i++){
            freq[s.charAt(i)-'a']++;
        }

        int mxFreq = 0;
        for(int i=0; i<26; i++){
            mxFreq = Math.max(mxFreq, freq[i]);
        }

        String[] groupFreq = new String[mxFreq+1];
        for(int i=0; i<mxFreq+1; i++){
            groupFreq[i] = "";
        }
        
        for(int i=0; i<26; i++){
            if(freq[i] != 0){
                groupFreq[freq[i]] += (char)('a' + i);
            }
        }

        int mxGroup = 0;
        String res = "";
        for(int i=0; i<mxFreq+1; i++){
            if(groupFreq[i].length() >= mxGroup){
                res = groupFreq[i];
                mxGroup = groupFreq[i].length();
            }
        }

        return res;
    }
}

// problem-2
class Solution2 {
    static int[] dp;
    public int climbStairs(int n, int[] costs) {
        dp = new int[n+1];
    
        for(int i=1; i<n+1; i++){
            dp[i] = Integer.MAX_VALUE;
            for(int j=i-1; j>=Math.max(0, i-3); j--){
                dp[i] = Math.min(dp[i], dp[j] + (costs[i-1] + (i - j) * (i - j)));
            }
        }
        return dp[n];
        
        // Memoization solution is giving TLE
        // return minCost(0, n, costs);
    }

    public static int minCost(int i, int n, int[] costs){
        if(i==n) return 0;

        if(dp[i] != 0) return dp[i];

        int cost = Integer.MAX_VALUE;
        for(int j=i+1; j<=j+3; j++){
            if(j <= n){
                cost = Math.min(cost, (costs[j-1] + (j - i)*(j - i)) + minCost(j, n, costs));
            }
        }
        dp[i] = cost;

        return dp[i];
    }
}

// problem-3
class Solution3 {
    public int distinctPoints(String s, int k) {
        int n = s.length();

        int up = 0, right = 0;
        Set<Pair<Integer,Integer>> set = new HashSet<>();

        int i = 0;
        for(; i<k; i++){
            char ch = s.charAt(i);

            if(ch=='L' || ch=='R'){
                right += ch=='R'? 1 : -1;
            }
            else{
                up += ch=='U'? 1 : -1;
            }
        }
        set.add(new Pair<>(up, right));


        int j = 0;
        for( ; i<n; i++){
            char ch1 = s.charAt(j++); // add 
            char ch2 = s.charAt(i); // remove

            if(ch1 != ch2){
                if(ch1=='L' || ch1=='R'){
                    right += ch1=='R'? 1 : -1;
                }
                if(ch1=='U' || ch1=='D'){
                    up += ch1=='U'? 1 : -1;
                }

                
                if(ch2=='L' || ch2=='R'){
                    right -= ch2=='R'? 1 : -1;
                }
                if(ch2=='U' || ch2=='D'){
                    up -= ch2=='U'? 1 : -1;
                }

                set.add(new Pair<>(up, right));
            }
        }
        return set.size();
    }
}

public class Main1{
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        // problem-1
        // System.out.print("Enter input string: ");
        // String s = sc.next();
        // Solution1 s1 = new Solution1();
        // System.out.println(s1.majorityFrequencyGroup(s));

        // problem-2
        // Solution2 s2 = new Solution2();
        // int n = 4;
        // int[] costs = {1,2,3,4};
        // System.out.println(s2.climbStairs(n, costs));

        // problem-3
        Solution3 s3 = new Solution3();
        System.out.println(s3.distinctPoints("DURLU", 2));
    }
}