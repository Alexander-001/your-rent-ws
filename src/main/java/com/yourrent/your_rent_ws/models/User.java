package com.yourrent.your_rent_ws.models;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotBlank(message = "El rut no puede estar vacio.")
    private String rut;
    @Column(unique = true)
    @NotBlank(message = "El correo no puede estar vacio.")
    private String email;
    @NotBlank(message = "El nombre no puede estar vacio.")
    @Column(name = "first_name")
    private String firstName;
    @NotBlank(message = "El apellido no puede estar vacio.")
    @Column(name = "last_name")
    private String lastName;
    @NotBlank(message = "El telefono no puede estar vacio.")
    private String phone;
    @NotBlank(message = "La fecha de nacimiento no puede estar vacia.")
    @Column(name = "birth_date")
    private String birthDate;
    @NotBlank(message = "El país no puede estar vacio.")
    private String country;
    @NotBlank(message = "La ciudad no puede estar vacia.")
    private String city;
    @NotBlank(message = "La dirección no puede estar vacia.")
    private String address;
    @NotBlank(message = "La contraseña no puede estar vacia.")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @NotBlank(message = "La imagen no puede estar vacia.")
    private String image;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean enabled;
    @Transient
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean admin;
    @JsonIgnoreProperties({ "users", "handler", "hibernateLazyInitializer" })
    @ManyToMany
    @JoinTable(name = "users_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), uniqueConstraints = {
            @UniqueConstraint(columnNames = { "user_id", "role_id" }) })
    private List<Role> roles;

    @PrePersist
    public void prePersist() {
        enabled = true;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((rut == null) ? 0 : rut.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        User other = (User) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (rut == null) {
            if (other.rut != null)
                return false;
        } else if (!rut.equals(other.rut))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", roles=" + roles +
                '}';
    }

}
