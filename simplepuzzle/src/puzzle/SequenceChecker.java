package puzzle;

public interface SequenceChecker {
	public void setLower(int a);
	public void setUpper(int b);
	public void setRange(int[] range);
	public boolean doCheck();
}
