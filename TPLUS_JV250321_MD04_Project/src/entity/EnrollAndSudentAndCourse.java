package entity;

public class EnrollAndSudentAndCourse extends Enrollment {
    private String StudentName;
    private String CourseName;

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    public String getCourseName() {
        return CourseName;
    }

    public void setCourseName(String courseName) {
        CourseName = courseName;
    }

    @Override
    public String toString() {
        return super.toString() + String.format(" |Course Name: %s |Student Name: %s", this.CourseName, this.StudentName);
    }
}
