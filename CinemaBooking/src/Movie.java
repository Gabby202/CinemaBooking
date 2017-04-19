import java.io.Serializable;

import javax.swing.Icon;
import javax.swing.ImageIcon;


/**
 * 
 * Object to store Movie infomation
 * 
 * @author Darragh
 *
 */
public class Movie implements Serializable{
	
	private static final long serialVersionUID = -7133647561789884916L;
	
	private String title;
	private ImageIcon img;
	private int[][] seats;
	private String times[];

	/**
	 * 
	 * Movie object constructor 
	 * 
	 * @param t Movie Title
	 * @param i Movie Poster
	 * @param d Movie Description
	 */
	Movie(String t, ImageIcon i, String[] tm) {
		title = t;
		img = i;
		seats = new int[tm.length][100];
		times = tm;
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
	
	public int[] getSeats(int time) {
		return seats[time];
	}


	public String[] getTimes() {
		return times;
	}

	public void setTimes(String t[]) {
		times = t;
	}
	
	/**
	 * Dev method to test Movie parameters before and after DB storage
	 */
	public void printAllDev(){
		System.out.println("Title: " + title);
		System.out.println("Img Height: " + img.getIconHeight() + " Img Width: " + img.getIconWidth());
		System.out.print("Times: ");
		for(String i : times){
			System.out.print(i + " ");
		}
		System.out.println("");
		System.out.println("Seats for first showing: ");
		for(int i: getSeats(0)){
			System.out.print(i + " ");
		}
		
	}
}
