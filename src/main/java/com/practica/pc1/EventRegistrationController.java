package com.practica.pc1;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/events")
public class EventRegistrationController {
    @Autowired
    private final EventRegistrationService service;

    public EventRegistrationController(EventRegistrationService eventRegistrations){
        this.service=eventRegistrations;
    }

    @PostMapping("/{eventId}/registrations")
    public ResponseEntity<EventRegistrationResponseDto> postEvent(@RequestBody @Valid EventRegistrationRequestDto dto){
        return ResponseEntity.ok(service.registerinevent(dto));
    }



}
