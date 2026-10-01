package com.practica.pc1;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class EventRequestDto {
    @NotNull
    private String title;

    @Size(max=500)
    private String description;

    private String Category;

    private ZonedDateTime eventDate;

    private String Location;
}
