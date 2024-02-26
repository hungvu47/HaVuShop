package com.example.havushop.service;

import com.example.havushop.model.Color;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ColorPrService {
    List<Color> getAllColor();
}
