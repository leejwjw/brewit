package com.pt.brewit.service;


import com.pt.brewit.dto.EventProductDTO;

import java.util.List;

public interface EventProductService {
    List<EventProductDTO> getEventProducts();
    void insertEventProduct(EventProductDTO eventProduct);
    EventProductDTO getFindProductId(int term_item_id);
}
