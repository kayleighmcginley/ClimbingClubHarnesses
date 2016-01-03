
public class Harness {
	
	public String make;
	public String modelNo;
	private int timesUsed;
	public String instructor;
	public boolean onLoan;
	public String member;
	
	public Harness(String make, String modelNo, int timesUsed, String instructor, boolean onLoan, String member) {
		this.make = make;
		this.modelNo = modelNo;
		this.timesUsed = timesUsed;
		this.instructor = instructor;
		this.onLoan = onLoan;
		this.member = member;
	}
	public Harness(String make, String modelNo, String instructor) {
		this.make = make;
		this.modelNo = modelNo;
		this.timesUsed = 0;
		this.instructor = instructor;
		this.onLoan = false;
		this.member = "";
	}
	public boolean checkHarness(String instructor) {
		this.timesUsed = 0;
		this.instructor = instructor;
		return true;
	}
	public boolean isHarnessOnLoan() {
		if(this.onLoan) return true;
		else			return false;
	}
	public boolean canHarnessBeLoaned() {
		if(this.timesUsed >= 25) return false;
		else 					 return true;
	}
	public void loanHarness(String member) {
		if(canHarnessBeLoaned() && !isHarnessOnLoan()) {
			this.member = member;
			this.timesUsed++;
			this.onLoan = true;
		}
	}
	public void returnHarness() {
		if(isHarnessOnLoan()) {
			this.onLoan = false;
			this.member = "";
		}
	}	
	public String toString() {
		String loan;
		if (onLoan) {
			loan = "is on loan to " + this.member;
		}
		else {
			loan = "is not currently on loan" ;
		}
		return this.make + ", " + this.modelNo + ", used " + this.timesUsed + " time(s), "
				+ "last checked by " + this.instructor + ", " + loan +  ".\n";
	}
}
