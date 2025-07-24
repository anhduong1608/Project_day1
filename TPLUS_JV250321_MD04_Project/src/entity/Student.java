package entity;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Student {
    private int id;
    private String name;
    private LocalDate dob;
    private String email;
    private boolean sex;
    private String phone;
    private String password;
    private LocalDate createAt;

    public Student(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Student() {
        this.createAt = LocalDate.now();
    }


    public Student(int id, String name, LocalDate dob, String email, boolean sex, String phone, String password, LocalDate createAt) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
        this.createAt = LocalDate.now();
    }


    public Student(String name, LocalDate dob, String email, boolean sex, String phone, String password) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
        this.createAt = LocalDate.now();
    }

    // Getter & Setter
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

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public Student(int id, String name, LocalDate dob, String email, boolean sex, String phone, String password) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.sex = sex;
        this.phone = phone;
        this.password = password;
    }

    public Student(int id, String password) {
        this.id = id;
        this.password = password;
    }

    @Override
    public String toString() {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format(
                "ID: %d | Name: %s | Email: %s | Sex: %s | Phone: %s | Created At: %s",
                id,
                name,
                email,
                sex ? "Nam" : "Nữ",
                phone != null ? phone : "N/A",
                createAt != null ? createAt.format(df) : "N/A"
        );
    }
}
