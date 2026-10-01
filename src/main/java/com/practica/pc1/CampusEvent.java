package com.practica.pc1;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Entity
@Table(name="CampusEvent")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampusEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name="eventId")
    private Long id;

    private Long organizerid;

    @NotNull
    private String title;

    @Size(max=500)
    private String description;


    private String Category;

    private ZonedDateTime eventDate;

    private String Location;

    private String Status;


}
