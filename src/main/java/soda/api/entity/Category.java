package soda.api.entity;
import jakarta.persistence.*;
import lombok.*;
@Entity @Table(name = "categories")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Category {
@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer cedula;
@Column(name="description", nullable = false)
private String des;
}