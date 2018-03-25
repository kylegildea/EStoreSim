/*  Name:  Kyle Gildea     
 *  Course: CNT 4714 � Spring 2017     
 *  Assignment title: 
 *  Program 1 � Event-driven Programming  
 *  Date: Sunday January 29, 2017 */

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class OptionPaneDisplay 
{
	private JFrame oFrame;
	private String errorMessage;
	private String title;
	private int choice;
	
	/**
	 * @param oFrame Frame that generated error
	 * @param title title of error box
	 * @param errorMessage message to be dispalyed in error box
	 * @param type 1 - Error, 2 Info/Msg. 3 - Yes/NO
	 */
	public OptionPaneDisplay(JFrame oFrame, String title, String errorMessage, int type)
	{
		this.oFrame = oFrame;
		this.errorMessage = errorMessage;
		this.title = title;
		
		switch(type)
		{
			case 1:
				displayError();
				break;
			
			case 2:
				displayInfo();
				break;
			
			case 3:
				displayYesNo();
			default:
				break;
		}
	}

	private void displayError()
	{
		oFrame.setVisible(false);
		JOptionPane.showMessageDialog(oFrame, errorMessage, title,  JOptionPane.ERROR_MESSAGE);
		oFrame.setVisible(true);
	}
	
	private void displayInfo()
	{
		oFrame.setVisible(false);
		JOptionPane.showMessageDialog(oFrame, errorMessage, title, JOptionPane.INFORMATION_MESSAGE); 
		//oFrame.setVisible(true);
	}
	
	private void displayYesNo()
	{
		Object[] options = {"Yes", "No"};
		
		oFrame.setVisible(false);
		setChoice(JOptionPane.showOptionDialog(oFrame, errorMessage, title, JOptionPane.YES_NO_CANCEL_OPTION,JOptionPane.QUESTION_MESSAGE, null, options,options[1]));
		//oFrame.setVisible(true);
	}
	
	/**
	 * @return choice for type of box
	 */
	public int getChoice()
	{
		return choice;
	}
	
	/**
	 * @param choice type of box to display
	 */
	public void setChoice(int choice)
	{
		this.choice = choice;
	}
}
