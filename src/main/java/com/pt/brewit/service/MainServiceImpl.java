package com.pt.brewit.service;

import com.pt.brewit.dto.AttachmentDTO;
import com.pt.brewit.dto.ProductDTO;
import com.pt.brewit.mapper.MainMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MainServiceImpl implements MainService {


    private final MainMapper mainMapper;

    @Override
    public List<ProductDTO> getBestProducts() {
        return mainMapper.findBestProducts();
    }

    @Override
    public List<ProductDTO> getProductsByCategory(int category_id) {
        return mainMapper.findProductsByCategory(category_id);
    }

    @Override
    public List<ProductDTO> getProductsByTool() {
        return mainMapper.findProductsByTool();
    }

    @Override
    public List<AttachmentDTO> getMainImg() {
        return mainMapper.findMainImg();
    }

}
