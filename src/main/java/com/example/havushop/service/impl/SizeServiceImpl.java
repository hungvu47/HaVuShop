package com.example.havushop.service.impl;

import com.example.havushop.model.Size;
import com.example.havushop.repository.SizeRepository;
import com.example.havushop.service.SizeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SizeServiceImpl implements SizeService {
    @Autowired
    SizeRepository sizeRepository;

    @Override
    public List<Size> getAllSize() {
        return sizeRepository.findAll();
    }
}
