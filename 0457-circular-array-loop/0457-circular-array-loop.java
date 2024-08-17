class Solution {
    static boolean[] vst;
    static int[] NUMS;
    public boolean circularArrayLoop(int[] nums) {
        NUMS = nums;
        boolean res = false;
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            vst = new boolean[len];
            int num = nums[i];
            boolean isForward = num > 0;
            while (num < 0)
                num += len;
            if (move(i, (i + num) % len, 1, isForward))
                return true;
        }
        return false;
    }

    private boolean move(int start, int i, int size, boolean isForward) {
        if (start == i) {
            return size > 1 ? true : false;
        }
        if (!vst[i]) {
            int num = NUMS[i];
            if (isForward == num > 0) {
                vst[i] = true;
            while (num < 0)
                num += NUMS.length;
                return move(start, (i + num) % NUMS.length, size + 1, isForward);
            } else {
                return false;
            }
        }
        return false;
    }
}