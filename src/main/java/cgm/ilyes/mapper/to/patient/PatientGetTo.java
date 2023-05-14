package cgm.ilyes.mapper.to.patient;

import cgm.ilyes.mapper.to.DateFormatPatternConstant;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class PatientGetTo {

    private long id;

    private String name;
    private String surname;
    private String socialSecurityNumber;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatPatternConstant.LOCAL_DATE_FORMAT)

    private LocalDate dateOfBirth;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatPatternConstant.ZONED_DATE_TIME_FORMAT)

    private ZonedDateTime createdAt;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatPatternConstant.ZONED_DATE_TIME_FORMAT)

    private ZonedDateTime updatedAt;


    public PatientGetTo() {
    }

    public PatientGetTo(long id, String name, String surname, String socialSecurityNumber, LocalDate dateOfBirth) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.socialSecurityNumber = socialSecurityNumber;
        this.dateOfBirth = dateOfBirth;
    }

    public PatientGetTo(long id, String name, String surname, String socialSecurityNumber, LocalDate dateOfBirth, ZonedDateTime createdAt, ZonedDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.socialSecurityNumber = socialSecurityNumber;
        this.dateOfBirth = dateOfBirth;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public void setSocialSecurityNumber(String socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public ZonedDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public String toString() {
        return "PatientGetTo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", socialSecurityNumber=" + socialSecurityNumber +
                ", dateOfBirth=" + dateOfBirth +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
