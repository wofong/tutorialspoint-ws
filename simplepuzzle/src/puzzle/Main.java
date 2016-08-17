package puzzle;

import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
//		int a = 11;
//		int b = 21;
//		LinearChecker lc = new LinearChecker(a, b, new int[]{11,12,17,18,19,21,20});
//		NLogNChecker nc = new NLogNChecker(a, b, new int[]{11,12,17,18,21,20,19});
//		LinearChecker lc = new LinearChecker(a, b, new int[]{1,9,0});
//		NLogNChecker nc = new NLogNChecker(a, b, new int[]{1,9,0});

		int LIMIT = (int)Math.pow(10, 7);
		int RANGE_LIMIT=(int)Math.pow(10, 7);
		int a = 0;
		int b = LIMIT-1;
		int[] input=new int[RANGE_LIMIT-1];
		for(int i=0;i<RANGE_LIMIT-1;i++){
//			if(i>=10000){
//				input[i]= i+1;
//			}else{
//				input[i]= i;
//			}
			input[i] = (int)(Math.random()*LIMIT);
		}
		int[] input2=Arrays.copyOf(input, input.length);
		
		//dumpArray(input);
		//dumpArray(input2);

		LinearChecker lc = new LinearChecker(a, b, input);
		NLogNChecker nc = new NLogNChecker(a, b, input2);
		
		long start=System.currentTimeMillis();
		System.out.println("lc: "+lc.doCheck());
		long time1=System.currentTimeMillis();
		System.out.println("nc: "+ nc.doCheck());
		long time2=System.currentTimeMillis();
		System.out.println("lc="+(time1-start)+", nc="+(time2-time1)+((time1-start)>(time2-time1)?" **nc wins":" **lc wins"));
	}
	public static void dumpArray(int[] range){
		for(int i=0; i<range.length;i++){
			System.out.print(range[i]+", ");
		}
		System.out.println();
	}

}