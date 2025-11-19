package hu.Szebasztian.basicBillManagementApp.data.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "bills")
public class Bill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private LocalDate exhibitionDate;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private int amount;


}
