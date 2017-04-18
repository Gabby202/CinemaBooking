
public class Payment {

	private String firstName;
	private String surName;
	private String address1;
	private String address2;
	private String cardNo;
	private String cvv;

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

	public String getCardNo() {
		return "Card Number: " + cardNo;
	}

	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}

	public String getCvv() {
		return "CVV: " + cvv;
	}

	public void setCvv(String cvv) {
		this.cvv = cvv;
	}

}
