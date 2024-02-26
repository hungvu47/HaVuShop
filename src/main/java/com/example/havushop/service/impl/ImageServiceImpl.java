package com.example.havushop.service.impl;

import com.example.havushop.model.Image;
import com.example.havushop.repository.ImageRepository;
import com.example.havushop.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ImageServiceImpl implements ImageService {
    @Autowired
    private ImageRepository imageRepository;

    @Override
    public List<Image> getAllImages() {
        return imageRepository.findAll();
    }

    @Override
    public List<Image> getImagesByProductId(Long productId) {
        return imageRepository.findByProduct_ProductId(productId);
    }

    @Override
    public Image getPrimaryImageByProductId(Long productId) {
        return imageRepository.findFirstByProduct_ProductIdAndIsPrimaryOrderByImageId(productId, true);
    }
}
