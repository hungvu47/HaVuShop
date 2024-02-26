package com.example.havushop.service;

import com.example.havushop.model.Size;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SizeService {
    List<Size> getAllSize();
}
