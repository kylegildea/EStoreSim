/*  Name:  Kyle Gildea     
 *  Course: CNT 4714 – Spring 2017     
 *  Assignment title: 
 *  Program 1 – Event-driven Programming  
 *  Date: Sunday January 29, 2017 */

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class AddBookFrame extends BookOrderFrame
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8074323939217219126L;
	Book newBook = new Book();
	BookOrderFrame oFrame;
	private JPanel responseButtons;
	private int booksEntered;
	private int totalBooks;
	private final double taxRate = 0.06;
	
	/**
	 * @param booksInOrder number of titles being ordered
	 * @param oFrame Frame containing orderform
	 */
	public AddBookFrame(int booksInOrder, BookOrderFrame oFrame)
	{
		this.oFrame = oFrame;
		this.setBooksEntered(1);
		this.setTotalBooks(0);
		oFrame.setLayout(new GridLayout(0,1,0,5));
		oFrame.pack();
		
		ArrayList<Book> order = new ArrayList<Book>(booksInOrder);
		JLabel []fieldsToAdd = new JLabel[]{new JLabel("Number of Items in Order:"), new JLabel("Enter Book ID for Item #" + this.getBooksEntered() + ":"), new JLabel("Enter Quantity for Item #" + this.getBooksEntered() + ":"), 
				new JLabel("Item #" + this.getBooksEntered() + " info"), new JLabel("Order Subtotal for " + (this.getBooksEntered()) + " items: ")};
		JTextField []userEntryField = new JTextField[]{new JTextField(50),new JTextField(50), new JTextField(50), new JTextField(50), new JTextField(50)};
		JButton exitButton = new JButton("Exit");
		JButton newButton = new JButton("New Order");
		JButton finishButton = new JButton("Finish Order");
		JButton viewButton = new JButton("View Order");
		JButton confirmButton = new JButton("Confirm Item #" + this.getBooksEntered());
		JButton processButton = new JButton("Process Item #" + this.getBooksEntered());
		JPanel []infoPanel = new JPanel[]{new JPanel(),new JPanel(),new JPanel(),new JPanel(),new JPanel()};
		
		for(int i = 0; i < 5; i++)
		{
			infoPanel[i].setLayout(new GridLayout(2,1));
			infoPanel[i].add(fieldsToAdd[i]);
			infoPanel[i].add(userEntryField[i]);
			if(i == 0)
			{
				userEntryField[i].setText(Integer.toString(booksInOrder));
				userEntryField[i].setEnabled(false);
			}
			else if(i == 3 || i == 4)
			{
				userEntryField[i].setEnabled(false);
			}
			oFrame.add(infoPanel[i]);
			infoPanel[i].setVisible(true);
		}
		
		processButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				if(userEntryField[2].getText()  != null && isInteger(userEntryField[2].getText()) && Integer.parseInt(userEntryField[2].getText()) > 0 && isInteger(userEntryField[1].getText()) && userEntryField[1].getText().length() == 5)
				{
					int userBookQty = Integer.parseInt(userEntryField[2].getText());
				
					String fileName = "inventory.txt";
					String line = null;
	
			        try {
			            FileReader fileReader = new FileReader(fileName);
			            BufferedReader bufferedReader = new BufferedReader(fileReader);
	
			            while((line = bufferedReader.readLine()) != null) 
			            {
			                if(line.startsWith(userEntryField[1].getText()))
			                {
			                	line = line.replaceAll(" - ", " , ");
			                	line = line.replace('"', ' ');
			                	String []breakUp = line.split(",");
			                	
			                	newBook.setUPC(breakUp[0]);
			                	newBook.setTitle(breakUp[1]);
			                	newBook.setAuthor(breakUp[2]);
			                	newBook.setQuantity(userBookQty);
			                	newBook.setperItemCost(Float.parseFloat(breakUp[3]));
			                	if(newBook.getQuantity() >=5 && newBook.getQuantity() <=9)
			                	{
			                		newBook.setDiscount(.1);
			                	}
			                	else if(newBook.getQuantity() >=10	 && newBook.getQuantity() <=14)
			                	{
			                		newBook.setDiscount(.15);
			                	}
			                	else if(newBook.getQuantity() >=15)
			                	{
			                		newBook.setDiscount(.2);
			                	}
			                	else
			                		newBook.setDiscount(0);
			                	newBook.setTotalCost((1 - newBook.getDiscount()) * newBook.getperItemCost() * newBook.getQuantity());
			                	userEntryField[3].setText(newBook.toString());
			                	confirmButton.setEnabled(true);
			                	processButton.setEnabled(false);
			                	break;
			                }
			            }
			            if(line == null)
		                {
		                	oFrame.setVisible(false);
		                	displayErrorMsg("Error", "Invalid Entry. Book Not Found", 1);
		                	oFrame.setVisible(true);
		                }
			            bufferedReader.close();         
			        }
			        catch(Exception ex)
			        {
			        	ex.printStackTrace();
			        }
				}
				else
				{
					oFrame.setVisible(false);
					displayErrorMsg("Error", "Please Enter A Valid 5 Digit ID & Quantity", 1);
					oFrame.setVisible(true);
				}
			}
		});
		
		confirmButton.setEnabled(false);
		confirmButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				order.add(newBook);
				displayErrorMsg("Mesage", "Item #" + booksEntered + " accepted.", 2);
				setBooksEntered(getBooksEntered() + 1);
				setTotalBooks(getTotalBooks() + newBook.getQuantity());
				for(int i = 1; i < 4; i++)
				{
					userEntryField[i].setText("");
				}
				
				userEntryField[4].setText(String.format("%.2f", getSubtotal(order)));
				confirmButton.setEnabled(false);
				if(getBooksEntered() <= booksInOrder || booksInOrder == 1)
				{
					updateFields(fieldsToAdd);
					viewButton.setEnabled(true);
					finishButton.setEnabled(true);
					processButton.setEnabled(true);
					updateButtons(processButton, confirmButton);
					
				}
				newBook = new Book();
			}
		});
		
		
		viewButton.setEnabled(false);
		viewButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				StringBuilder currOrder = new StringBuilder();
				for(int i = 0; i < order.size(); i++)
				{
					currOrder.append((i + 1) + ". " + order.get(i).toString() + " " + order.get(i).getQuantity() +"\n");
				}

				displayErrorMsg("Current Order", currOrder.toString(),2);
			}
		});
		
		finishButton.setEnabled(false);
		finishButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				processButton.setEnabled(false);
				StringBuilder currOrder = new StringBuilder();
				currOrder.append(new Date().toString() + "\nNumber of line items: " + order.size() + "\nItem# / ID" + 
				"/ Title / Price / Disc % / Subtotal: \n\n");
				for(int i = 0; i < order.size(); i++)
				{
					currOrder.append((i + 1) + ". " + order.get(i).toString() + " " + order.get(i).getQuantity() +"\n");
				}
				currOrder.append("\n\n\nOrder Subtotal: $" + String.format("%.2f", getSubtotal(order)) + "\n\n\nTax rate: " + String.format("%.2f", 100.0 * taxRate) + "%"
						+ "\n\n\nTax amount: $" + String.format("%.2f", getSubtotal(order) * taxRate) + "\n\n\nOrder total: $" + String.format("%.2f", ((1.0 + taxRate) * getSubtotal(order))));
				
				displayErrorMsg("Final Order", currOrder.toString(), 2);
				
				File transLog = new File("transactions.txt");
				if(!(transLog.exists()))
				{
					try 
					{
						transLog.createNewFile();
					} catch (IOException e1) 
					{
						displayErrorMsg("Error", "Error Creating Transaction Log", 1);
					}
				}
				else
				{
					try
					{
						FileWriter outToLog = new FileWriter(transLog, true);
						BufferedWriter outToLogBuffer = new BufferedWriter(outToLog);
						PrintWriter printToLog = new PrintWriter(outToLogBuffer);
						
						for(int i = 0; i < order.size(); i++)
						{
							printToLog.println(getTimeStamp() + " " + order.get(i).toString() +" " + order.get(i).getQuantity() + " " + new Date().toString());
						}
						printToLog.close();
						outToLogBuffer.close();
						outToLog.close();
						oFrame.setVisible(false);
						oFrame.dispose();
						new TotalItemsFrame();
					}
					catch(IOException ex)
					{
						displayErrorMsg("Error", "Error Writing to Transaction Log", 1);
					}	
				}
			}
		});
		
		newButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				newOrder();
			}
		});
		
		
		exitButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e)
			{
				oFrame.setVisible(false);
				System.exit(0);
			}
		});
		
		responseButtons = new JPanel();
		responseButtons.add(processButton);
		responseButtons.add(confirmButton);
		responseButtons.add(viewButton);
		responseButtons.add(finishButton);
		responseButtons.add(newButton);
		responseButtons.add(exitButton);
		
		
		oFrame.setSize(500, 500);
		oFrame.add(responseButtons, BorderLayout.LINE_END);
		oFrame.pack();
		oFrame.setVisible(true);
	}
	
	/**
	 * @param fields contains fields that are updated after each item is confirmed
	 */
	public void updateFields(JLabel []fields)
	{
		for(int i = 1; i < 5; i++)
		{
			switch(i)
			{
				case 1:
					fields[i].setText("Enter Book ID for Item #" + this.getBooksEntered() + ":");
					break;
				case 2:
					fields[i].setText("Enter Quantity for Item #" + this.getBooksEntered() + ":");
					break;
				case 3:
					fields[i].setText("Item #" + this.getBooksEntered() + " info");
					break;
				case 4:
					fields[i].setText("Order subtotal for " + this.getTotalBooks() + " item(s)");
					break;
				default:
					break;
					
			}
		}
	}
	
	/**
	 * @param processButton Process Button
	 * @param confirmButton Confirm Button
	 */
	public void updateButtons(JButton processButton, JButton confirmButton) 
	{
		processButton.setText("Process Item #" + this.getBooksEntered());
		confirmButton.setText("Confirm Item #" + this.getBooksEntered());
		
	}
	/**
	 * @return number of titles that have been entered so far
	 */
	public int getBooksEntered() {
		return booksEntered;
	}

	/**
	 * @param booksEntered sets number of titles that have been entered so far
	 */
	public void setBooksEntered(int booksEntered) {
		this.booksEntered = booksEntered;
	}

	/**
	 * @return number of titles that have been entered so far
	 */
	public int getTotalBooks() {
		return totalBooks;
	}

	/**
	 * @param totalBooks amount of titles * amount of each title being ordered
	 */
	public void setTotalBooks(int totalBooks) {
		this.totalBooks = totalBooks;
	}
	
	/**
	 * @return time stamp to be used as orderID in YYMMDDhhmmss format
	 */
	public String getTimeStamp()
	{
		SimpleDateFormat formatDate = new SimpleDateFormat("YYMMDDhhmmss");
		String ts = formatDate.format(new Date());
		
		return ts;
	}
	
	/**
	 * @param title title of option pane popup
	 * @param message String to be displayed on error popup
	 * @param type type of option pane to be displayed
	 */
	public void displayErrorMsg(String title, String message, int type)
	{
		new OptionPaneDisplay(this, title, message, type);
		
	}
	
	/**
	 * Clears current order, creates new Total Items to be ordered window
	 */
	public void newOrder()
	{
		oFrame.setVisible(false);
		this.dispose();
		new TotalItemsFrame();
	}
	
	/**
	 * @param order Books already confirmed to be ordered
	 * @return subtotal of books already added to order
	 */
	public double getSubtotal(ArrayList<Book> order)
	{
		double subtotal = 0.0;
		for(int i = 0; i < order.size(); i++)
		{
			subtotal +=order.get(i).getTotalCost();
		}
		
		return subtotal;
	}
	/**
	 * @param num number of books being entered by user
	 * @return false if not a valid integer, true if it is
	 */
	public static boolean isInteger(String num) 
	{
	      boolean isValidInteger = false;
	      try
	      {
	         Integer.parseInt(num);
	         isValidInteger = true;
	      }
	      catch (NumberFormatException ex)
	      {
	         
	      }
	      return isValidInteger;
	   }
}

