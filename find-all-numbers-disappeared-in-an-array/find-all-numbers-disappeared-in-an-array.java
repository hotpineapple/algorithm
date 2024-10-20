class Solution {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> list = new ArrayList<>();
        for(int i=1;i<=nums.length;i++){
            list.add(i);
        }
        for (int i=0;i<nums.length;i++) {
            list.set(nums[i]-1,0);
        }
        Iterator<Integer> it = list.iterator();
        while(it.hasNext()) {
            if(it.next()==0)it.remove();
        }
        return list;
    }
}