package com.example.guarderia.dato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name="cliente")
public class Cliente {
    @Id
    @Column(name = "documento", nullable = false)
    private Integer documento;
    @Column(name = "nombre", nullable = false)
    private String nombre;
    @Column(name ="direccion", nullable = false)
    private String direccion;

    @JsonIgnore
    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Mascota> mascotas;

    public Cliente(Integer documento, String nombre, String direccion){
        this.documento = documento;
        this.nombre = nombre;
        this.direccion = direccion;
    }
}
