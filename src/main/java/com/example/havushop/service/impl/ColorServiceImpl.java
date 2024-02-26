package com.example.havushop.service.impl;

import com.example.havushop.model.Color;
import com.example.havushop.repository.ColorRepository;
import com.example.havushop.service.ColorPrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ColorServiceImpl implements ColorPrService {

    @Autowired
    ColorRepository colorRepository;

    @Override
    public List<Color> getAllColor() {
        return colorRepository.findAll();
    }
}
