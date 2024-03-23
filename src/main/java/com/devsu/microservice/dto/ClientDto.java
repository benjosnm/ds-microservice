package com.devsu.microservice.dto;

import com.devsu.microservice.entities.Client;
import com.devsu.microservice.entities.ClientStatus;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ClientDto extends PersonDto {
    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;
    private ClientStatus status;

    public ClientDto() {
    }

    public ClientDto(Long id, String pwd, ClientStatus status) {
        this.id = id;
        this.pwd = pwd;
        this.status = status;
    }

    public ClientDto(Client clientEntity) {
        this.id = clientEntity.getId();
        this.pwd = clientEntity.getPwd();
        this.status = ClientStatus.valueOf(clientEntity.getStatus());
        super.setFirstName(clientEntity.getPerson().getFirstName());
        super.setLastName(clientEntity.getPerson().getLastName());
        super.setPersonId(clientEntity.getPerson().getId());
        super.setGender(clientEntity.getPerson().getGender());
        super.setAddress(clientEntity.getPerson().getAddress());
        super.setPhone(clientEntity.getPerson().getPhone());
        super.setBirthDate(clientEntity.getPerson().getBirthDate());
    }

    @Override
    public Long getPersonId() {
        return id;
    }

    @Override
    public void setPersonId(Long personId) {
        this.id = personId;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public ClientStatus getStatus() {
        return status;
    }

    public void setStatus(ClientStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ClientDto clientDto = (ClientDto) o;
        return Objects.equals(id, clientDto.id) && Objects.equals(pwd, clientDto.pwd) && status == clientDto.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id, pwd, status);
    }

    @Override
    public String toString() {
        return "ClientDto{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }
}