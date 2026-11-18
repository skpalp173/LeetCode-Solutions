class Solution {
    public int maxProfit(int[] prices) {
        int n=prices.length;
        int min=Integer.MAX_VALUE;
        int ans=0;
        for(int i=0;i<n;i++){
            ans=Math.max(ans,prices[i]-min);
            min=Math.min(min,prices[i]);
        }
        return ans;
    }
}