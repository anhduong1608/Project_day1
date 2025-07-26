package entity;

public class StatisticStudentByCourse {
    private int courseId;
    private String courseName;
    private int studentTotal;

    public StatisticStudentByCourse() {
    }

    public StatisticStudentByCourse(int courseId, String courseName, int studentTotal) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentTotal = studentTotal;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getStudentTotal() {
        return studentTotal;
    }

    public void setStudentTotal(int studentTotal) {
        this.studentTotal = studentTotal;
    }

    @Override
    public String toString() {
        return String.format("Course ID : %-5d| Course Name : %-25s| Student Total : %-5d", courseId, courseName, studentTotal);
    }
}
