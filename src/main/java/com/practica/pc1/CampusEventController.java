package com.practica.pc1;

import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;

@Controller
@RequestMapping("/events")
public class CampusEventController {
    @Autowired
    private final CampusEventService service;

    private CampusEventController(CampusEventService service){
        this.service=service;
    }

    @PreAuthorize("hasRole('ORGANIZER') or hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<EventResponseDto> postEvent(@RequestBody @Valid EventRequestDto dto){
        return ResponseEntity.ok(service.createevent(dto));
    }

    @PreAuthorize("hasRole('ORGANIZER') or hasRole('ADMIN')")
    @PatchMapping("/{eventId}/publish")
    public ResponseEntity<EventResponseDto> publishEvent(@PathParam("eventId") Long id){
        return ResponseEntity.ok(service.publish(id));
    }

    @GetMapping
    public ResponseEntity<PagedResponseDto<EventResponseDto>> getAllEvents(@RequestParam String category, @RequestParam(required = false)ZonedDateTime from, @PageableDefault(page = 0, size = 10) Pageable pageable) {
        Page<EventResponseDto> productsPage = service.getAllEvents(pageable);
        PagedResponseDto<EventResponseDto> responseDto = new PagedResponseDto<>(productsPage);
        return ResponseEntity.ok(responseDto);
    }


}
