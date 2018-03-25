/*  Name:  Kyle Gildea     
 *  Course: CNT 4714 – Spring 2017     
 *  Assignment title: 
 *  Program 1 – Event-driven Programming  
 *  Date: Sunday January 29, 2017 */


public class Book
{
	private String UPC;
	private String title;
	private String author;
	private int quantity;
	private double perItemCost;
	private double discount;
	private double totalCost;
	
	/**
	 * 
	 */
	public Book()
	{
		
	}
	
	/**
	 * @param UPC - Book UPC
	 * @param title - Title of Book
	 * @param author - Author of Book
	 * @param quantity - Quantity of individual book being order
	 * @param perItemCost - Price of individual book before discount
	 */
	public Book(String UPC, String title, String author, int quantity, double perItemCost)
	{
		this.UPC = UPC;
		this.title = title;
		this.author = author;
		this.quantity = quantity;
		this.perItemCost = perItemCost;
	}
	
	/**
	 * @return Book UPC code Number
	 */
	public String getUPC() {
		return UPC;
	}

	/**
	 * @param uPC UPC of book being set
	 */
	public void setUPC(String uPC) {
		UPC = uPC;
	}

	/**
	 * @return Title of book
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title title of Book being set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return author of book
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author Author of Book being added
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return amount of a particular book being bought
	 */
	public int getQuantity() {
		return quantity;
	}

	/**
	 * @param quantity sets amount of a particular book being bought
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	/**
	 * @return cost of one of a particular book
	 */
	public double getperItemCost() {
		return perItemCost;
	}

	/**
	 * @param perItemCost set cost of one of a particular book
	 */
	public void setperItemCost(double perItemCost) {
		this.perItemCost = perItemCost;
	}

	/**
	 * @return discount based on quantity of book being bought
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * @param discount set discount based on quantity of book being bought
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * @return totalCost of a particular book (quantity of title being purchased * (price * 1 - discount %));
	 */
	public double getTotalCost() {
		return totalCost;
	}

	/**
	 * @param totalCost set totalCost of a particular book (quantity of title being purchased * (price * 1 - discount %));
	 */
	public void setTotalCost(double totalCost) {
		this.totalCost = totalCost;
	}

	@Override
	public String toString() 
	{
		return this.getUPC() + " " + this.getTitle() + " - " + this.getAuthor() + " $" + String.format("%.2f", this.getperItemCost()) + " "
		+ this.getDiscount() * 100.0 + "% $" + String.format("%.2f", this.getTotalCost());  
	}
}
