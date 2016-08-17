package puzzle;

import java.util.Arrays;

public class NLogNChecker implements SequenceChecker{
	private int a;
	private int b;
	private int[] range;
	
	public NLogNChecker(int a, int b, int[] range){
		this.a = a;
		this.b = b;
		this.range = range;
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
		return checkSequence(a,b,range);
	}

	public boolean checkSequence(int a, int b, int[] range) {
		// scenario 1: continuous section in the middle of the domain
		long time0 = System.currentTimeMillis();
		int domain_base = b - a + 1;
		for (int i = 0; i < range.length; i++) {
			range[i] = range[i] % domain_base;
		}
		long time1 = System.currentTimeMillis();
		System.out.println("[nc] after normalize: " + (time1-time0));

		int min, max;
		min = min(range);
		max = max(range);
		long time2 = System.currentTimeMillis();
		System.out.println("[nc] min-max: " + (time2-time1));
		if (max - min + 1 == range.length) {
			return true;
		} else {
			//System.out.println("[ "+min+", "+max+" ]");
			//we must have min=0,max=base-1, otherwise the range is not right.
			if(min!=0 || max!=domain_base-1){
				return false;
			}
			// scenario 2: the numbers are wrapping around, O(N*ln(N))
			long time3 = System.currentTimeMillis();
			Arrays.sort(range);
			long time4 = System.currentTimeMillis();
			System.out.println("[nc] after sorting: " + (time4-time3));
			for (int h = 0, t = range.length - h - 1; h < t; h++, t--) {
				if (range[h] + 1 == range[h + 1]) {
					if (range[t] == range[t - 1] + 1) {
						continue;
					} else {
						// from item h to t-1 should be in seq
						if (range[h] + range[t] != domain_base - 1)
							return false;
						else
							return inSeq(a, b, h, t - 1, range);
					}
				} else {
					// from item h+1 to t will be in seq
					if (range[h] + range[t] != domain_base - 1)
						return false;
					else
						return inSeq(a, b, h + 1, t, range);
				}
			}
			return true;
		}
	}


	private boolean inSeq(int a, int b, int h, int t, int[] range) {
		boolean flag;
		flag = (range[h] + t - h == range[t]);
		return flag;
	}
	
	private int min(int[] range) {
		int index = 0;
		int min = range[0];
		do {
			if (min > range[index]) {
				min = range[index];
			}
			index++;
		} while (index < range.length);
		return min;
	}
	
	private int max(int[] range) {
		int index = 0;
		int max = range[0];
		do {
			if (max < range[index]) {
				max = range[index];
			}
			index++;
		} while (index < range.length);
		return max;
	}
}
