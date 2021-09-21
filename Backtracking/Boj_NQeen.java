public class Main {
	static int ans, N;
	static int[] col; //2차원 배열일 필요가 없음
	
	//같은 행에는 놓지 않는 방식
	public static void main(String[] args) {
		 Scanner sc = new Scanner(System.in);
		 N = sc.nextInt();
		 
		 col = new int[N+1];//0 제외 1~N번째 퀸의 번호를 인덱스로 활용하기 위함
		 setQueen(0);
		 System.out.println(ans);
	}
	public static void setQueen(int rowNo) {
		//유망하지 않으면 안가는 방법도  있지만
		//백트래킹에서는 주로 일단 간다음에 유망하지 않으면 돌아오는 방식을 씀
    
		if(!isAvailable(rowNo)) return; //rowNo 1일 때도 관계 없음
		if(rowNo==N) {
			ans++;
			return;
		}
		//"자식" 노드의 가지를 파생
		for(int i=1; i<=N;i++) {
			col[rowNo+1] = i;
			setQueen(rowNo+1);
		}
	}
  
	public static boolean isAvailable(int rowNo) {
//		if(Math.abs(col[rowNo]-col[i])<=1) return false; //이건 잘못된코드.. 1칸 말고 2칸씩떨어진 대각선도 있음
		for(int i=1;i<rowNo;i++) {
			if(col[rowNo]==col[i] || Math.abs(col[rowNo]-col[i])==rowNo-i) return false;
		}
		return true;
	}

}
