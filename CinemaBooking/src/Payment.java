/**
 * 
 * @author Gabby
 * payment form setters and getters
 */
public class Payment {

	private String firstName;
	private String surName;
	private String address1;
	private String address2;
	private long cardNo;
	private int cvv;

	/**
	 * 
	 * @return name string
	 */
	public String getFirstName() {
		return "Name: " + firstName;
	}

	/**
	 * 
	 * @param firstName set name
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * 
	 * @return surname string 
	 */
	public String getSurName() {
		return "Surname: " + surName;
	}

	/**
	 * 
	 * @param surName set surname
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}

	/**
	 * 
	 * @return address line 1 
	 */
	public String getAddress1() {
		return "Address Line 1: " + address1;
	}

	/**
	 * 
	 * @param address1 set address line 1 
	 */
	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	/**
	 * 
	 * @return address line 2 
	 */
	public String getAddress2() {
		return "Address Line 2: " + address2;
	}

	
	/**
	 * 
	 * @param address2 set address line 2
	 */
	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	/**
	 * 
	 * @return card Number long
	 */
	public long getCardNo() {
		return cardNo;
	}

	/**
	 * 
	 * @param cardNo set card number
	 */
	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}

	/**
	 * 
	 * @return cvv int 
	 */
	public int getCvv() {
		return cvv;
	}

	/**
	 * 
	 * @param cvv set cvv int
	 */
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

}
