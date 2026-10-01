package com.practica.pc1;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EventRegistrationService {
    @Autowired
    private final EventRegistrationRepository repository;
    private final ModelMapper mapper;

    public EventRegistrationService(EventRegistrationRepository eventRegistrationRepository, ModelMapper mapper){
        this.repository=eventRegistrationRepository;
        this.mapper=mapper;
    }

    public EventRegistrationResponseDto registerinevent(EventRegistrationRequestDto dto){
        EventRegistration eventRegistration= mapper.map(dto, EventRegistration.class);
        eventRegistration.setStatus("DRAFT");
        return mapper.map(repository.save(eventRegistration),EventRegistrationResponseDto.class);
    }

}
