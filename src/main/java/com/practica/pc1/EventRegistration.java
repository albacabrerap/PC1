package com.practica.pc1;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name="EventRegistration")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EventRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(referencedColumnName = "eventId")
    private Long eventId;

    @JoinColumn(referencedColumnName = "TicketTypeId")
    private Long TicketTypeId;

    @JoinColumn(referencedColumnName = "userId")
    private Long attendeeId;

    private ZonedDateTime registeredAt;

    private String status;




}
