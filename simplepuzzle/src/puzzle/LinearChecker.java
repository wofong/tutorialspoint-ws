package puzzle;

public class LinearChecker implements SequenceChecker{
	private int a;
	private int b;
	private int[] range;
	
	public LinearChecker(int a, int b, int[] range){
		this.a = a;
		this.b = b;
		this.range = range;
	}
	
	public boolean smartCheckSequence(int a, int b, int[] range){
		//scenario 1: continuous section in the middle of the domain.
		long time0 = System.currentTimeMillis();
		int domain_base = b-a+1;
		
		//normalize the input range
		a=0;
		b=b-a;
		for(int i=0; i<range.length; i++){
			range[i] = range[i]%domain_base;
		}
		long time1 = System.currentTimeMillis();
		System.out.println("[lc] after normalize: " + (time1-time0));
		
		//it takes O(N) to find min/max
		int[] min_max = min_max(range);
		int min = min_max[0];
		int max = min_max[1];
		long time2 = System.currentTimeMillis();
		System.out.println("[lc] after min_max: " + (time2-time1));
		long time3 = System.currentTimeMillis();
		long time4 = System.currentTimeMillis();
		boolean flag;
		if(max-min+1==range.length){
			//scenario 1 is very straightforward
			flag=true;
		}else{
			//scenario 2.1 range is broken, so we must have both 0 and base-1 covered
			if(min==0&&max==domain_base-1){
				int missingNumber = findMissingNumber(range);
				time3 = System.currentTimeMillis();
				System.out.println("[lc] missingNumber: " + (time3-time2));
				
				//dumpArray(range);
				//System.out.println("missing number = " + missingNumber);
				//now update the range based on the break point
				for(int i=0; i<range.length; i++){
					range[i] = range[i]-missingNumber>0? range[i]-domain_base:range[i];
				}
				min_max=min_max(range);
				min = min_max[0];
				max = min_max[1];
				time4 = System.currentTimeMillis();
				System.out.println("[lc] after second min-max: " + (time4-time3));
				
//				System.out.println("[min,max]= [ "+min+","+max+" ]");
//				dumpArray(range);

				flag=max-min+1==range.length;
			}else{
				//2.2 if the range does not cover both 0 and base-1, then the range is broken
				flag=false;
			}
		}
		long time5= System.currentTimeMillis();
		System.out.println("[lc] before returning: " + (time5-time4));
		return flag;
	}
	/**
	 * range is scaled to 0 based, this function finds the missing number. 
	 * for example, {0,1,3,4,5,10}, will give you 2. 
	 * 
	 * @param range
	 * @return
	 */
	public int findMissingNumber(int[] range){
		boolean[] seen = new boolean[b-a+1];
		for(int i=0;i<range.length;i++){
			seen[range[i]]=true;
		}
		
		for(int i=0;i<seen.length;i++){
			if(!seen[i]){
				//this is where the sequence is broken
				return i;
			}
		}
		//not found? all seen?
		return -1;
	}

	private int[] min_max(int[] range){
		int min=range[0];
		int max=range[0];
		
		for(int i=0;i<range.length;i++){
			if(range[i]<min){
				min=range[i];
			}else if(range[i]>max){
				max=range[i];
			}
		}
		return new int[]{min,max};
	}
	@Override
	public void setLower(int a) {
		this.a = a;
	}
	@Override
	public void setUpper(int b) {
		this.b = b;
	}
	@Override
	public void setRange(int[] range) {
		this.range = range;
	}
	@Override
	public boolean doCheck() {
		return smartCheckSequence(a, b, range);
	}	
}
