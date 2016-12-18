
public class Course {
	final static int capacity = 10;
	
	private String course_name = null;
	private int course_contain = 0;
	
	Course(String name) {
		this.course_name = name;
	}
	
	public String name() {
		return this.course_name;
	}
	
	/**
	 * Add one to the number of students the course has contained if someone has been registered. 
	 */
	public void register() {
		this.course_contain++;
	}
	/**
	 * Judge the course is full or not. 
	 */
	public boolean full() {
		if (this.course_contain >= Course.capacity) {
			return true;
		} else {
			return false;
		}
	}
}
