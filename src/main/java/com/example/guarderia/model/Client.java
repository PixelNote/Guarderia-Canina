package com.example.guarderia.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="clients")
public class Client {

    @Id
    private Integer document;
    private String name;
    private String address;
    private String email;
    @JsonIgnore
    @OneToMany(mappedBy = "client")
    private List<Pet> pets;

    public Client(Integer document, String name, String address, String email) {
        this.document = document;
        this.name = name;
        this.address = address;
        this.email = email;
    }

}
