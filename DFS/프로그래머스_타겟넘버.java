class Solution {
    static int cnt, tar, nums[];
    public int solution(int[] numbers, int target) {
        nums = numbers;
        tar=target;
        dfs(0,0,0);
        dfs(0,0,1);
        return cnt/2;
    }
    private void dfs(int sum, int idx, int op){
        if(idx==nums.length) {
            if(sum==tar) cnt++;
            return;
        }
        if(op==0) {
            dfs(sum+nums[idx],idx+1,0);
            dfs(sum+nums[idx],idx+1,1);
        }else if(op==1){
            dfs(sum-nums[idx],idx+1,0);
            dfs(sum-nums[idx],idx+1,1);
        }
    }
}
