package com.pt.brewit.service;

import com.pt.brewit.dto.EventProductDTO;
import com.pt.brewit.mapper.EventProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventProductServiceImpl implements EventProductService {

    private final EventProductMapper eventProductMapper;


    @Override
    public List<EventProductDTO> getEventProducts() {
        return eventProductMapper.getEventProductForId();
    }
}
