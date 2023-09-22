class Solution {
    public long[] solution(long[] numbers) {
        long[] answer = new long[numbers.length];
        for(int i=0;i<numbers.length;i++){
            long num = numbers[i];
            if(num%2==0){
                String str = fn1(Long.toBinaryString(num));
                answer[i] = fn3(str);
            }else{
                String str = fn2(Long.toBinaryString(num));
                answer[i] = fn3(str);
            }
        }
        return answer;
    }
    private String fn1(String str){
        return str.substring(0,str.length()-1)+"1";
    }
    private String fn2(String str){
        char[] chArr = str.toCharArray();
        int i=chArr.length-1;
        for(;i>=0;i--){
            if(chArr[i]=='0') {
                chArr[i] = '1';
                break;
            }
        }
        if(i==-1) {
            char[] chArr2 = new char[chArr.length+1];
            chArr2[0] = '1';
            for(int j=0;j<chArr.length;j++){
                chArr2[j+1] = chArr[j];
            }
            chArr = chArr2;
            i=0;
        }
        
        for(int j=i+1;j<chArr.length;j++){
            if(chArr[j]=='1') {
                chArr[j] = '0';
                break;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for(int k=0;k<chArr.length;k++){
            sb.append(chArr[k]);
        }
        return sb.toString();
    }
    private long fn3(String str){
        long num=0;
        for(int i=str.length()-1;i>=0;i--){
            if(str.charAt(i)=='1')num += Math.pow(2,str.length()-1-i);
        }
        return num;
    }
}
