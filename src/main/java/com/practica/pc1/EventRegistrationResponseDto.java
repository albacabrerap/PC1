package com.practica.pc1;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventRegistrationResponseDto {
    private Long id;
    private String eventTitle;
    private String TicketType;
    private String atendeeUsername;
    private String Status;

}
