import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Movie implements Serializable{
	
	private static final long serialVersionUID = -7133647561789884916L;
	
	private String title;
	private ImageIcon img;
	private String desp;

	Movie(String t, ImageIcon i, String d) {
		title = t;
		img = i;
		desp = d;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String t) {
		title = t;
	}

	public Icon getImg() {
		return img;
	}

	public void setImg(ImageIcon i) {
		img = i;
	}

	public String getDesp() {
		return desp;
	}

	public void setDesp(String d) {
		desp = d;
	}
	
	public void printAllDev(){
		System.out.println("Title: " + title);
		System.out.println("Img Height: " + img.getIconHeight() + " Img Width: " + img.getIconWidth());
		System.out.println("Desp: " + desp);
	}
}