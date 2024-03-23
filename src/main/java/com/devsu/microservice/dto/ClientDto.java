package com.devsu.microservice.dto;

import com.devsu.microservice.entities.ClientEntity;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class ClientDto extends PersonDto {
    private Long id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String pwd;
    private BaseStatus status;

    public ClientDto() {
    }

    public ClientDto(Long id, String pwd, BaseStatus status) {
        this.id = id;
        this.pwd = pwd;
        this.status = status;
    }

    public ClientDto(ClientEntity clientEntity) {
        this.id = clientEntity.getId();
        this.pwd = clientEntity.getPwd();
        this.status = BaseStatus.valueOf(clientEntity.getStatus());
        super.setFirstName(clientEntity.getPerson().getFirstName());
        super.setLastName(clientEntity.getPerson().getLastName());
        super.setPersonId(clientEntity.getPerson().getId());
        super.setGender(clientEntity.getPerson().getGender());
        super.setAddress(clientEntity.getPerson().getAddress());
        super.setPhone(clientEntity.getPerson().getPhone());
        super.setBirthDate(clientEntity.getPerson().getBirthDate());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public BaseStatus getStatus() {
        return status;
    }

    public void setStatus(BaseStatus status) {
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
