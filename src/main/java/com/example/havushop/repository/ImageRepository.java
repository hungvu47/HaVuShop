package com.example.havushop.repository;

import com.example.havushop.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByProduct_ProductId(Long productId);
    Image findFirstByProduct_ProductIdAndIsPrimaryOrderByImageId(Long prroductId, boolean isPrimary);
}
