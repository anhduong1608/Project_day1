package entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CourseEnrollment extends Course {
    private LocalDateTime enrollmentAt;
    private String enrollStatus;

    public CourseEnrollment() {
        this.enrollmentAt = enrollmentAt;
        this.enrollStatus = enrollStatus;
    }

    public LocalDateTime getEnrollmentAt() {
        return enrollmentAt;
    }

    public String getEnrollStatus() {
        return enrollStatus;
    }

    public void setEnrollStatus(String enrollStatus) {
        this.enrollStatus = enrollStatus;
    }

    public void setEnrollmentAt(LocalDateTime enrollmentAt) {
        this.enrollmentAt = enrollmentAt;
    }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return super.toString() + String.format("| %-20s | %-10s|", enrollmentAt != null ? enrollmentAt.format(dtf) : "N/A", enrollStatus  );
    }
}
