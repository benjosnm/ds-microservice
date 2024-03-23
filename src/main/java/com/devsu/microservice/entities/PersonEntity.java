package com.devsu.microservice.entities;

import com.devsu.microservice.dto.ClientDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

@Entity
@Table(name="persons")
public class PersonEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="person_id", unique = true)
    private Long id;
    @NotBlank(message = "First name is required.")
    private String firstName;
    @NotBlank(message = "Last name is required.")
    private String lastName;
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Past
    private Date birthDate;
    private String address;
    private String phone;

    public PersonEntity() {
    }

    public PersonEntity(Long id, String firstName, String lastName, String gender, Date birthDate, String address, String phone) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
    }

    public PersonEntity(ClientDto clientDto) {
        this.firstName = clientDto.getFirstName();
        this.lastName = clientDto.getLastName();
        this.gender = clientDto.getGender();
        this.birthDate = clientDto.getBirthDate();
        this.address = clientDto.getAddress();
        this.phone =  clientDto.getPhone();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonEntity personEntity = (PersonEntity) o;
        return Objects.equals(id, personEntity.id) && Objects.equals(firstName, personEntity.firstName) && Objects.equals(lastName, personEntity.lastName) && Objects.equals(gender, personEntity.gender) && Objects.equals(birthDate, personEntity.birthDate) && Objects.equals(address, personEntity.address) && Objects.equals(phone, personEntity.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, gender, birthDate, address, phone);
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
