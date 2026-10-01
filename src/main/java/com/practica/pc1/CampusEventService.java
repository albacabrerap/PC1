package com.practica.pc1;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CampusEventService {
    @Autowired
    private final CampusEventRepository eventRepository;
    private final ModelMapper mapper;

    public CampusEventService(CampusEventRepository eventRepository, ModelMapper mapper){
        this.eventRepository=eventRepository;
        this.mapper=mapper;
    }

    public EventResponseDto createevent(EventRequestDto dto){
        CampusEvent campusEvent= mapper.map(dto, CampusEvent.class);
        return mapper.map(eventRepository.save(campusEvent),EventResponseDto.class);
    }
    public EventResponseDto publish(Long id, String status){
        CampusEvent event=eventRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("NotFound"));
        event.setStatus(status);
        return mapper.map(event, EventResponseDto.class);
    }

    public Page<EventResponseDto> getAllEvents(Pageable pageable) {
        Page<CampusEvent> eventsPage = eventRepository.findAll(pageable);
        
        return eventsPage.map(product -> mapper.map(product, EventResponseDto.class));
    }
}
