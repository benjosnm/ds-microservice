package com.devsu.microservice.entities;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import java.util.Objects;

@Entity
@Table(name="clients")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="client_id")
    private Long id;
    @OneToOne(targetEntity = Person.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name="person_id")
    private Person person;

    private String pwd;
    private String status;

    public Client() {
    }

    public Client(Long id, Person person, String pwd, String status) {
        this.id = id;
        this.person = person;
        this.pwd = pwd;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(id, client.id) && Objects.equals(person, client.person) && Objects.equals(pwd, client.pwd) && Objects.equals(status, client.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, person, pwd, status);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", person=" + person +
                ", status='" + status + '\'' +
                '}';
    }
}
