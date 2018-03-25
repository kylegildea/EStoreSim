/*  Name:  Kyle Gildea     
 *  Course: CNT 4714 – Spring 2017     
 *  Assignment title: 
 *  Program 1 – Event-driven Programming  
 *  Date: Sunday January 29, 2017 */

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TotalItemsFrame extends BookOrderFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -2755171219095946286L;
	private JTextField numBooksField;
	private JPanel jp, buttonPanel;
	private JButton okButton;
	private JButton exitButton;
	private String fileLocation = null;
	private int itemsInOrder;
	
	/**
	 * Creates a new Frame with a text field and an ok & exit button to get the
	 * number of titles being purchased
	 */
	public TotalItemsFrame()
	{
			jp = new JPanel();
			buttonPanel = new JPanel();
			JLabel label = new JLabel("Please enter number of books:");
			this.add(label);
			numBooksField = new JTextField(5);
			okButton = new JButton("OK");
			exitButton = new JButton("Exit");
			QuantityWindowButtonHandler qwbh = new QuantityWindowButtonHandler();
			okButton.addActionListener(qwbh);
			exitButton.addActionListener(qwbh);
			jp.add(label);
			jp.add(numBooksField);
			buttonPanel.add(okButton);
			buttonPanel.add(exitButton);
			
			this.setTitle("Personal Organizer");
			this.add(jp,BorderLayout.NORTH);
			this.add(buttonPanel,BorderLayout.PAGE_END);
			this.setTitle("Open File");
			this.setVisible(true);
			this.pack();
	}
	
	
	
	/**
	 * @return Amount of books being purchased given by user
	 */
	public int getItemsInOrder() 
	{
		return itemsInOrder;
	}

	/**
	 * @param itemsInOrder sets Amount of books being purchased by user
	 * Closes Frame, Opens a new Book Ordering frame.
	 */
	public void setItemsInOrder(int itemsInOrder) 
	{
		this.itemsInOrder = itemsInOrder;
		new AddBookFrame(this.getItemsInOrder(), new BookOrderFrame());
		this.setVisible(false);
		this.dispose();
	}
	
	/**
	 * Generates an error popup if the user doesn't enter a valid number of books
	 */
	public void DisplayErrorMsg()
	{
		new OptionPaneDisplay(this, "Error", "Invalid Entry. Please Enter a Valid Integer", 1);
		
	}

	/**
	 * Handles Button Clicks by User
	 */
	
	class QuantityWindowButtonHandler implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ae)
		{
			if(ae.getActionCommand().equals("OK"))
			{
				try
				{
					setItemsInOrder(Integer.parseInt(numBooksField.getText()));
				}
						
				catch(NumberFormatException ex)
				{
					DisplayErrorMsg();
					numBooksField.setText(null);
				}

				catch(Exception ex1)
				{
					numBooksField.setText(null);
				}
			}
			
			if(ae.getActionCommand().equals("Exit"))
			{
				System.exit(0);
			}
		}
	}
}
