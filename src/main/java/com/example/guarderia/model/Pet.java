package com.example.guarderia.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "pets")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @ManyToOne
    private Client client;
    @JsonIgnore
    @OneToMany(mappedBy = "pet")
    private List<Booking> bookings;

    public Pet(String name, Client client){
        this.name = name;
        this.client = client;
    }

    public Pet(int id, String name, Client client) {
        this.id = id;
        this.name = name;
        this.client = client;
    }
}
