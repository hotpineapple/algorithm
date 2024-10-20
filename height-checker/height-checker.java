class Solution {
    public int heightChecker(int[] heights) {
        int[] cpy = new int[heights.length];
        for(int i=0;i<heights.length;i++) {
            cpy[i] = heights[i];
        }
        Arrays.sort(heights);
        int cnt=0;
        for(int i=0;i<heights.length;i++) {
            if (cpy[i]!=heights[i]) cnt++;
        }
        return cnt;
    }
}