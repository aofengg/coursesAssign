import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;

public class Driver {
	final static int total_student_number = 12;
	final static int total_course_number = 4;
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.out.println("Please enter two command line arguments.\n");
		} else {
			ArrayList<Student> student_list = new ArrayList<Student>();
			try {
				File file = new File(args[0]);
				BufferedReader buffer = new BufferedReader(new FileReader(file));
				String line = null;
			
				while((line = buffer.readLine()) != null) {
					String[] sp = line.split("\\s+");
					//System.out.println(sp[0]+" "+sp[1]+" "+sp[2]+" "+sp[3]+" "+sp[4]+"\n");
					Student student = new Student(sp[0], Integer.parseInt(sp[1]), Integer.parseInt(sp[2]), Integer.parseInt(sp[3]),Integer.parseInt(sp[4]));
					student_list.add(student);
				}
				assignment(student_list);
				int total_score = 0;
				for (int i = 0; i < student_list.size(); i++) {
					student_list.get(i).output(args[1]);
					total_score += student_list.get(i).get_total_score();
				}
				float average_preference_score = (float)total_score/student_list.size();
				FileWriter fw = new FileWriter(args[1], true);
				fw.write("\n" + "Average preference_score is: " + average_preference_score + "\n");
				fw.close();
                System.out.println("The output has been written into the file you wanted.\n");
			} catch (Exception e) {
				System.out.println(e.toString());;
			}
		}
	}
	
	/**
	 * Register a course for a student if the course is available. 
	 * The number of the courses,which the student have registered, will add one.
	 * The number of the students in this course will add one.
	 */
	public static void register(Student student, Course course) {
		student.register(course);
		course.register();
	}
	
	/**
	 * Assign the four courses to the students.
	 * Consider the first preference from all the students firstly.
	 * Then consider the second preference, then third, then fourth.
	 * If a student can not be registered a course which he or she prefer because there is no room,
	 * then all the other courses should be assigned to him or her.
	 */
	public static void assignment(ArrayList<Student> student) {
		Course A = new Course("A");
		Course B = new Course("B");
		Course C = new Course("C");
		Course D = new Course("D");
		for (int preference = 1; preference <= total_course_number; preference++) {
			for (int i = 0; i < student.size(); i++ ) {
				if (!student.get(i).full_course()) {
					if (student.get(i).get_course_from_preference(preference) == A.name() && !A.full()) {
						register(student.get(i), A);
					}
					else if (student.get(i).get_course_from_preference(preference) == B.name() && !B.full()) {
						register(student.get(i), B);
					}
					else if (student.get(i).get_course_from_preference(preference) == C.name() && !C.full()) {
						register(student.get(i), C);
					}
					else if (student.get(i).get_course_from_preference(preference) == D.name() && !D.full()) {
						register(student.get(i), D);
					}
					else {
						for (int j = preference + 1; j <= total_course_number; j++) {
							if (student.get(i).get_course_from_preference(j) == A.name() && !A.full()) {
								register(student.get(i), A);
							}
							else if (student.get(i).get_course_from_preference(j) == B.name() && !B.full()) {
								register(student.get(i), B);
							}
							else if (student.get(i).get_course_from_preference(j) == C.name() && !C.full()) {
								register(student.get(i), C);
							}
							else if (student.get(i).get_course_from_preference(j) == D.name() && !D.full()) {
								register(student.get(i), D);
							}
						}
					}
				}
			}
		}
		
	}

}
