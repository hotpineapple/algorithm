class Solution {
    public String reverseWords(String s) {
        StringBuilder sb = new StringBuilder();
        String[] strArr = s.split(" ");
        for(int i=0;i<strArr.length;i++) {
            String str = strArr[i];
            for(int j=str.length()-1;j>=0;j--) {
                sb.append(str.charAt(j));
            }
            sb.append(" ");
        }
        String str =sb.toString();
        return str.substring(0,str.length()-1);
    }
}