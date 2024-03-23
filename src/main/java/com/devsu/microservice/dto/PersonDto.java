package com.devsu.microservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import java.util.Objects;

public class PersonDto {
    private Long personId;
    private String firstName;
    private String lastName;
    private String gender;
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date birthDate;
    private String address;
    private String phone;

    public PersonDto() {
    }

    public PersonDto(Long personId, String firstName, String lastName, String gender, Date birthDate, String address, String phone) {
        this.personId = personId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.gender = gender;
        this.birthDate = birthDate;
        this.address = address;
        this.phone = phone;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
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
        PersonDto personDto = (PersonDto) o;
        return Objects.equals(personId, personDto.personId) && Objects.equals(firstName, personDto.firstName) && Objects.equals(lastName, personDto.lastName) && Objects.equals(gender, personDto.gender) && Objects.equals(birthDate, personDto.birthDate) && Objects.equals(address, personDto.address) && Objects.equals(phone, personDto.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(personId, firstName, lastName, gender, birthDate, address, phone);
    }

    @Override
    public String toString() {
        return "PersonDto{" +
                "id=" + personId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
