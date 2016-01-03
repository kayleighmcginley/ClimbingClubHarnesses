import java.util.ArrayList;

public class HarnessRecords {
	
	public ArrayList<Harness> harnesses;
	
	public HarnessRecords() {
		 harnesses = new ArrayList<Harness>();
	}
	public HarnessRecords(int numHarnesses, String [] make, String [] modelNo, int [] timesUsed, String [] instructor, boolean [] onLoan, String [] member) {
		for(int i = 0; i < numHarnesses; i++) {
			Harness tmp = new Harness(make[i], modelNo[i], instructor[i]);
			harnesses.add(tmp);
		}
	}
	public boolean isEmpty() {
		if(harnesses.isEmpty())	return true;
		else					return false;
	}
	public void addHarness(Harness h) {
		harnesses.add(h);
	}
	public Harness findHarness(String make, String modelNo) {
		for(int i = 0; i < harnesses.size(); i++) {
			if(harnesses.get(i).make == make && harnesses.get(i).modelNo == modelNo) {
				return harnesses.get(i);
			}
		}
		return null;
	}
	public Harness checkHarness(String instructor, String make, String modelNo) {
		Harness myHarness = findHarness(make, modelNo);
		if(myHarness == null)	return null;
		myHarness.checkHarness(instructor);
		return myHarness;
	}
	public Harness loanHarness(String member) {
		for(int i = 0; i < harnesses.size(); i++) {
			if(!harnesses.get(i).isHarnessOnLoan()) {
				harnesses.get(i).loanHarness(member);
				return harnesses.get(i);
			}
		}
		return null;
	}
	public Harness returnHarness(String make, String modelNo) {
		Harness myHarness = findHarness(make, modelNo);
		if(myHarness == null || !myHarness.isHarnessOnLoan())	return null;
		myHarness.returnHarness();
		return myHarness;
	}
	public Harness removeHarness(String make, String modelNo) {
		Harness myHarness = findHarness(make, modelNo);
		if(myHarness == null)	return null;
		harnesses.remove(myHarness);
		return myHarness;
	}
}
