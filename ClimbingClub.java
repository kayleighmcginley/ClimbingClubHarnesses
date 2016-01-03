import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JOptionPane;	

public class ClimbingClub {

	public static void main(String[] args) {
		
		String[] choices = {"View Records","Loan/Return","Add/Remove Harness", "Update Safety Check"};
		String[] instructors = {"Mick Scott","Kameron Whitney","Ellis Hooper","Kendal Elwes","Will Foster",
				"Abigail Sniders","Becky Sidney","Shirley Johnson","Rachel Anderson","Alan Turing"};
		String[] makes = {"Black Diamond","CAMP","Petzl","Arc'teryx","Metolius","Miller","Delta"};
		ArrayList<String> modelNoList = new ArrayList<String>();
		ArrayList<String> makesList = new ArrayList<String>();
		
		int choice = 0;
		HarnessRecords records = new HarnessRecords();
		
		do {
			choice = JOptionPane.showOptionDialog(null,"Which action would you like to take?", "Climbing Club Record System",
					JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, choices, choices[0]);
			//view records
			if(choice == 0) {
				String output = "";
				if(!records.isEmpty()) {
					for(int i = 0; i < records.harnesses.size(); i++) {
						output += records.harnesses.get(i).toString();
					}
				}
				else {
					output = "Nothing to display.";
				}
				JOptionPane.showMessageDialog(null, output);
			}
			//Loan/Return
			else if(choice == 1) {
				String [] loanReturn = {"Loan", "Return"};
				int loan = JOptionPane.showOptionDialog(null,"Would you like to loan or return a harness?","Climbing Club Record System",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, loanReturn, loanReturn[0]);
				if(loan == 0) {
					//Loan
					String member = JOptionPane.showInputDialog(null, "Who is borowing this harness?");
					if(member != null && !member.isEmpty()) {
						Harness tmp = records.loanHarness(member);
						if(tmp == null) {
							JOptionPane.showMessageDialog(null, "No harness available."); 
						}
					}
				}
				else if(loan == 1) {
					//Return
					Object[] makeL = makesList.toArray();
					Object[] modNoL = modelNoList.toArray(); 
					ArrayList<Object> arList = new ArrayList<Object>();
					for(int i = 0; i < makeL.length; i++) {
						if(records.findHarness((String)makeL[i], (String)modNoL[i]).onLoan) {
							arList.add("" + makeL[i] + " " + modNoL[i]);
						}
					}
					Object[]list = arList.toArray();
					if(list.length != 0) {	
						Object harness = JOptionPane.showInputDialog(null, "Which harness would you like to safety check?",
						   		"Climbing Club Record System" , JOptionPane.QUESTION_MESSAGE, null, list, list[0]);
						if(harness != null) {
							int index = Arrays.asList(list).indexOf(harness);
							
							Harness tmp = records.findHarness((String)makeL[index], (String)modNoL[index]);
							JOptionPane.showMessageDialog(null, tmp.member + " returned " + tmp.make + " " + tmp.modelNo); 
							records.returnHarness((String)makeL[index], (String)modNoL[index]); 
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Nothing to return."); 
					}
				}
				else	System.exit(0);
			}	
			//Add/Remove
			else if(choice == 2) {
				String [] addRemove = {"Add", "Remove"};
				int add = JOptionPane.showOptionDialog(null,"Would you like to add or remove a harness?","Climbing Club Record System",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, addRemove, addRemove[0]);
				//Add
				if(add == 0) {
					String numHarnesses = "";
					int num = 0;
					do {
					numHarnesses = JOptionPane.showInputDialog(null, "How many harnesses would you like to store in the database?");
					try {
						if(numHarnesses == null) 	break;
						num = Integer.parseInt(numHarnesses);
						if(num <= 0) {
							JOptionPane.showMessageDialog(null, "Insert a valid integer greater than 0.");
						}
					} catch(NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "Insert a valid integer greater than 0."); 
					}
					} while(num <= 0);
					if(numHarnesses != null) {
						for(int i = 0; i < num; i++) {
							String make = (String) JOptionPane.showInputDialog(null, "What make is this harness?",
						    		"Climbing Club Record System" , JOptionPane.QUESTION_MESSAGE, null, makes, makes[0]);
							if(make != null) {
								makesList.add(make);
								String modelNo = JOptionPane.showInputDialog(null, "What is the model number?");
								if(modelNo != null && !modelNo.isEmpty()) {
									modelNoList.add(modelNo);
									String instructor = (String) JOptionPane.showInputDialog(null, "Which instructor safety checked this harness?",
								    		"Climbing Club Record System" , JOptionPane.QUESTION_MESSAGE, null, instructors, instructors[0]);
									if(instructor != null) {
										Harness newHarness = new Harness(make, modelNo, instructor);
										records.addHarness(newHarness);
									}
								}
							}
						}
					}
				}
				//Remove
				else if(add == 1) {
					Object[] makeL = makesList.toArray();
					Object[] modNoL = modelNoList.toArray(); 
					Object[]list = new String[makeL.length];
					for(int i = 0; i < makeL.length; i++) {
						list[i] = "" + makeL[i] + " " + modNoL[i];
					}
					if(list.length != 0) {
						Object harness = JOptionPane.showInputDialog(null, "Which harness would you like to safety check?",
					    		"Climbing Club Record System" , JOptionPane.QUESTION_MESSAGE, null, list, list[0]);
						if(harness != null) {
							int index = Arrays.asList(list).indexOf(harness);
							
							Harness tmp = records.findHarness((String)makeL[index], (String)modNoL[index]);
							JOptionPane.showMessageDialog(null, tmp.make + " " + tmp.modelNo + " has been removed."); 
							records.removeHarness((String)makeL[index], (String)modNoL[index]); 
							makesList.remove(makeL[index]);
							modelNoList.remove(modNoL[index]);
						}
					}
					else {
						JOptionPane.showMessageDialog(null, "Nothing to remove."); 
					}					
				}
			}
			//Safety Check
			else if(choice == 3) {
				Object[] makeL = makesList.toArray();
				Object[] modNoL = modelNoList.toArray(); 
				Object[]list = new String[makeL.length];
				for(int i = 0; i < makeL.length; i++) {
					list[i] = "" + makeL[i] + " " + modNoL[i];
				}
				if(list.length != 0) { 
					Object harness = JOptionPane.showInputDialog(null, "Which harness would you like to safety check?",
				    		"Climbing Club Record System" , JOptionPane.QUESTION_MESSAGE, null, list, list[0]);
					if(harness != null) {
						String instructor = (String) JOptionPane.showInputDialog(null, "What instructor safety checked the harness?",
					    		"Climbing Club Record System" , JOptionPane.QUESTION_MESSAGE, null, instructors, instructors[0]);				
						if(instructor != null && !instructor.isEmpty()) {
							int index = Arrays.asList(list).indexOf(harness);
							records.checkHarness(instructor, (String)makeL[index], (String)modNoL[index]);
						}
					}
				}
				else {
					JOptionPane.showMessageDialog(null, "No harnesses in System.");
				}
			}
			
		} while(choice >= 0 && choice <=3);
	}
}
