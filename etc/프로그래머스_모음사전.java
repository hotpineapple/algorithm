import java.util.*;
class Solution {
    public int solution(String word) {
        int len = 5 + 25 + 125 + 625 + 3125;
        String[] words = new String[len];
        char[] arr = new char[5];
        arr[0]='A';arr[1]='E';arr[2]='I';arr[3]='O';arr[4]='U';
        int i = 0;
        for(int j=0;j<5;j++) words[i++] = (char)arr[j]+"";
        for(int j=0;j<5;j++){
            for(int k=0;k<5;k++){
                words[i++] = arr[j]+""+arr[k];
            }
        }
        for(int j=0;j<5;j++){
            for(int k=0;k<5;k++){
                for(int l=0;l<5;l++){
                    words[i++] = arr[j]+""+arr[k]+""+arr[l];
                }
            }
        }
        for(int j=0;j<5;j++){
            for(int k=0;k<5;k++){
                for(int l=0;l<5;l++){
                    for(int m=0;m<5;m++){
                        words[i++] = arr[j]+""+arr[k]+""+arr[l]+""+arr[m];
                    }
                }
            }
        }
        for(int j=0;j<5;j++){
            for(int k=0;k<5;k++){
                for(int l=0;l<5;l++){
                    for(int m=0;m<5;m++){
                        for(int n=0;n<5;n++){
                            words[i++] = arr[j]+""+arr[k]+""+arr[l]+""+arr[m]+""+arr[n];
                        }   
                    }
                }
            }
        }
        Arrays.sort(words);
        for(int j=0;j<len;j++){
            if(words[j].equals(word)) return j+1;
        }
        return 0;
    }
}
