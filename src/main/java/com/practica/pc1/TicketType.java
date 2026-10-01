package com.practica.pc1;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="TicketType")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="TicketTypeId")
    private Long id;

    @JoinColumn(referencedColumnName = "eventId")
    private Long eventId;

    @NotBlank
    private String name;

    @Min(1)
    private Integer capacity;

    private Integer registeredCount;

    private String status;


}
