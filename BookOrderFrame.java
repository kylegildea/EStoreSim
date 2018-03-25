/*  Name:  Kyle Gildea     
 *  Course: CNT 4714 – Spring 2017     
 *  Assignment title: 
 *  Program 1 – Event-driven Programming  
 *  Date: Sunday January 29, 2017 */

import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

public class BookOrderFrame extends JFrame 
{
	private static final long serialVersionUID = 7336748332948474910L;

	/**
	 * Used to maintain constant UI across frames
	 */
	public BookOrderFrame() 
    {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels())
            {
                if("Nimbus".equals(info.getName())) 
                {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } 
        catch (Exception e) 
        {
            
        }
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
