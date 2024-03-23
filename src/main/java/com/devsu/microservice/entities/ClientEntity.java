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
public class ClientEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="client_id")
    private Long id;
    @OneToOne(targetEntity = PersonEntity.class, cascade = CascadeType.PERSIST)
    @JoinColumn(name="person_id")
    private PersonEntity personEntity;

    private String pwd;
    private String status;

    public ClientEntity() {
    }

    public ClientEntity(Long id, PersonEntity personEntity, String pwd, String status) {
        this.id = id;
        this.personEntity = personEntity;
        this.pwd = pwd;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PersonEntity getPerson() {
        return personEntity;
    }

    public void setPerson(PersonEntity personEntity) {
        this.personEntity = personEntity;
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
        ClientEntity clientEntity = (ClientEntity) o;
        return Objects.equals(id, clientEntity.id) && Objects.equals(personEntity, clientEntity.personEntity) && Objects.equals(pwd, clientEntity.pwd) && Objects.equals(status, clientEntity.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personEntity, pwd, status);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", person=" + personEntity +
                ", status='" + status + '\'' +
                '}';
    }
}
