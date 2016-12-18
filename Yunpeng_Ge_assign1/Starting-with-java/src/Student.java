import java.io.FileWriter;
import java.io.IOException;

public class Student {
	private String student_name = null;
	private int A_preference;
	private int B_preference;
	private int C_preference;
	private int D_preference;
	private int A_get = 0;
	private int B_get = 0;
	private int C_get = 0;
	private int D_get = 0;
	
	Student (String name, int a, int b, int c, int d) {
		this.student_name = name;
		this.A_preference = a;
		this.B_preference = b;
		this.C_preference = c;
		this.D_preference = d;
	}
	
	public String name() {
		return this.student_name;
	}
	/**
	 * Returns the course is full of students or not. 
	 */
	public boolean full_course() {
		if (this.A_get+this.B_get+this.C_get+this.D_get >= 3) {
			return true;
		} else {
			return false;
		}
	}
	/**
	 * Returns the course's name from the student's preference.
	 */
	public String get_course_from_preference(int preference) {
		if (this.A_preference == preference) {
			return "A";
		}
		if (this.B_preference == preference) {
			return "B";
		}
		if (this.C_preference == preference) {
			return "C";
		}
		else {
			return "D";
		}
	}
	/**
	 * Returns the total preference score based on which courses the student has been registered.
	 */
	public int get_total_score() {
		return this.A_preference*this.A_get + this.B_preference*this.B_get + this.C_preference*this.C_get + this.D_preference*this.D_get;
	}
	/**
	 * Register a course for a student and mark that the student has gotten this course.
	 */
	public void register(Course course) {
		if (course.name() == "A") {
			this.A_get = 1;
		}
		if (course.name() == "B") {
			this.B_get = 1;
		}
		if (course.name() == "C") {
			this.C_get = 1;
		}
		if (course.name() == "D") {
			this.D_get = 1;
		}
	}
	/**
	 * Write which courses the student has been registered into the output file.
	 */
	public void output(String filename) throws IOException {
		FileWriter fw = new FileWriter(filename, true);
		fw.write(this.student_name + "  ");
		if (A_get == 1) {
			fw.write("A  ");
		}
		if (B_get == 1) {
			fw.write("B  ");
		}
		if (C_get == 1) {
			fw.write("C  ");
		}
		if (D_get == 1) {
			fw.write("D  ");
		}
		fw.write("" + this.get_total_score() + "\n");
		
		fw.close();
	}
}
