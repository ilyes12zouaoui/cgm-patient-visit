package cgm.ilyes.mapper.to.patient;

import cgm.ilyes.mapper.to.DateFormatPatternConstant;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;

import java.time.LocalDate;

public class PatientCreateTo {

    @NotBlank
    private String name;
    @NotBlank
    private String surname;
    @NotBlank
    private String socialSecurityNumber;
    @Past
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormatPatternConstant.LOCAL_DATE_FORMAT)

    private LocalDate dateOfBirth;

    public PatientCreateTo() {
    }

    public PatientCreateTo(String name, String surname, String socialSecurityNumber, LocalDate dateOfBirth) {
        this.name = name;
        this.surname = surname;
        this.socialSecurityNumber = socialSecurityNumber;
        this.dateOfBirth = dateOfBirth;
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

    @Override
    public String toString() {
        return "PatientCreateTo{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", socialSecurityNumber=" + socialSecurityNumber +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }
}
