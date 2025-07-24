package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Enrollment {
    private int id;
    private int student_id;
    private int courseId;
    private LocalDateTime enrollmentAt;
    private String status;

    public Enrollment(int student_id, int courseId, LocalDateTime enrollmentAt) {
        this.student_id = student_id;
        this.courseId = courseId;
        this.enrollmentAt = enrollmentAt;
    }

    public Enrollment() {
    }

    public Enrollment(int id, int student_id, int courseId, LocalDateTime enrollmentAt, String status) {
        this.id = id;
        this.student_id = student_id;
        this.courseId = courseId;
        this.enrollmentAt = enrollmentAt;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public LocalDateTime getEnrollmentAt() {
        return enrollmentAt;
    }

    public void setEnrollmentAt(LocalDateTime enrollmentAt) {
        this.enrollmentAt = enrollmentAt;
    }

    public String getStatus() {
        return status;
    }

    public Enrollment(int student_id, int courseId) {
        this.student_id = student_id;
        this.courseId = courseId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return String.format("ID: %d |Student ID: %d |Course ID: %d |EnrollmentAt: %s |Status: %s", id, student_id, courseId, enrollmentAt != null ? enrollmentAt.format(dtf) : "N/A", status);
    }
}
