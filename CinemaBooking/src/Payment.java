
public class Payment {

	private String firstName;
	private String surName;
	private String address1;
	private String address2;
	private long cardNo;
	private int cvv;

	public String getFirstName() {
		return "Name: " + firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return "Surname: " + surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getAddress1() {
		return "Address Line 1: " + address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return "Address Line 2: " + address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public long getCardNo() {
		return cardNo;
	}

	public void setCardNo(long cardNo) {
		this.cardNo = cardNo;
	}

	public int getCvv() {
		return cvv;
	}

	public void setCvv(int cvv) {
		this.cvv = cvv;
	}

}
