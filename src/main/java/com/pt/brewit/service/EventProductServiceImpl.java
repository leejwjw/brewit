package com.pt.brewit.service;

import com.pt.brewit.dto.EventProductDTO;
import com.pt.brewit.dto.MemberDTO;
import com.pt.brewit.mapper.EventProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EventProductServiceImpl implements EventProductService {

    private final EventProductMapper eventProductMapper;
    @Value("${file.dir}")
    private String fileDir; // YAML에서 주입받은 파일 경로

    @Override
    public List<EventProductDTO> getEventProducts() {
        return eventProductMapper.getEventProductInfo();
    }

    @Override
    public void insertEventProduct(EventProductDTO eventProductDTO) {
        eventProductMapper.insertEventProductOne(eventProductDTO);
    }

    public EventProductDTO getFindProductId(int term_item_id) {
        return eventProductMapper.getFindProductForId(term_item_id);
    }
    @Override
    public  List<EventProductDTO> getEventProductList(MemberDTO logged_member) {
        List<EventProductDTO> products = eventProductMapper.getEventProduct(logged_member);
        for (EventProductDTO product : products) {
            String file = getFullPath(product.getAttach_name());
            product.setFilePath(file); // 파일 경로를 DTO에 추가
        }
        return eventProductMapper.getEventProduct(logged_member);
    }
    @Override
    public void deleteProduct(int id) {
        eventProductMapper.deleteProductById(id);
    }
    @Override
    public String getFullPath(String filename) {
        return fileDir + '/' +filename;
    }

}
