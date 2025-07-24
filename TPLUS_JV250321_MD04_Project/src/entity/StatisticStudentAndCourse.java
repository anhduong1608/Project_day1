package entity;

public class StatisticStudentAndCourse {
    private int totalStudent;
    private int totalCourse;

    public int getTotalStudent() {
        return totalStudent;
    }

    public StatisticStudentAndCourse() {
    }

    public StatisticStudentAndCourse(int totalStudent, int totalCourse) {
        this.totalStudent = totalStudent;
        this.totalCourse = totalCourse;
    }

    public void setTotalStudent(int totalStudent) {
        this.totalStudent = totalStudent;
    }

    public int getTotalCourse() {
        return totalCourse;
    }

    public void setTotalCourse(int totalCourse) {
        this.totalCourse = totalCourse;
    }

    @Override
    public String toString() {
        return String.format("Total student: %d| Total course: %d", totalStudent, totalCourse);
    }
}
