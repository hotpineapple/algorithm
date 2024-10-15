class Solution {
    public int[] sortedSquares(int[] nums) {
       
        Map<Integer,Integer> map = new TreeMap<>(); 
        for (int i=0;i<nums.length;i++) {
            int square = (int)(Math.pow(nums[i],2));
            int cnt = map.getOrDefault(square,0);
            map.put(square,cnt+1);
        }
        
        int[] res = new int[nums.length];
        int i=0;
        for (Integer key : map.keySet()) {
            int cnt = map.get(key);
            for(int j=0;j<cnt;j++) res[i++] = key;
        }
        
        return res;
    }
}