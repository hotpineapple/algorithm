class Solution {
    public List<Integer> getRow(int rowIndex) {
        int[][] map = new int[rowIndex+1][];
        List<Integer> list = new ArrayList<>();
        if(rowIndex==0) {list.add(1); return list;}
        else if (rowIndex==1) {
            list.add(1);list.add(1); return list;
        }
        map[0] = new int[]{1};
        map[1] = new int[]{1,1};
        
        for(int i=2;i<=rowIndex;i++) {
            int[] row = new int[i+1];
            row[0] = map[i-1][0];
            row[i] = map[i-1][0];
            for(int j=1;j<i;j++) {
                System.out.println("row="+i+",col="+j);
                row[j]=map[i-1][j] + map[i-1][j-1];
            }
            map[i] = row;
        }
        for(int i=0;i<=rowIndex;i++) list.add(map[rowIndex][i]);
        return list;
    }
}