package com.example.havushop.service;

import com.example.havushop.model.Image;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ImageService {
    List<Image> getAllImages();

    public List<Image> getImagesByProductId(Long productId);

    public Image getPrimaryImageByProductId(Long productId);
}
