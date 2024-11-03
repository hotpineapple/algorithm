class Solution {
    public String longestCommonPrefix(String[] strs) {
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<200;i++) {
            if (i>= strs[0].length()) return sb.toString();
            char ch = strs[0].charAt(i);
            for(int j=1;j<strs.length;j++) {
                if (i>=strs[j].length() || ch != strs[j].charAt(i)) return sb.toString();
            }
            sb.append(ch);
        }
        return sb.toString();
    }
}