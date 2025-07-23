package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Course {
    private int id;
    private String name;
    private int duration;
    private String instructor;
    private LocalDate createAt;

    public Course() {
    }


    public Course(int id, String name, int duration, String instructor, LocalDate createAt) {
        this.id = id;
        this.name = name;
        this.duration = duration;
        this.instructor = instructor;
        this.createAt = createAt;
    }

    public Course(String name, int duration, String instructor) {
        this.name = name;
        this.duration = duration;
        this.instructor = instructor;
    }

    // Getters & Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = createAt != null ? createAt.format(formatter) : "N/A";

        return String.format(
                "ID: %d | Name: %s | Duration: %d giờ | Instructor: %s | Created At: %s",
                id, name, duration, instructor, formattedDate
        );
    }

}