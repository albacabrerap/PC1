package com.practica.pc1;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventResponseDto {
    private Long id;

    private Long organizerid;

    @NotNull
    private String title;

    private String Category;

    private String Status;
}
