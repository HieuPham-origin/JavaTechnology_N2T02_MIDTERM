package com.example.midterm.services;

import com.example.midterm.models.Color;

import java.util.List;
import java.util.Optional;

public interface ColorService {
    List<Color> getAllColors();
    Color addColor(Color color);
    Optional<Color> getColorById(int id);
    void deleteColor(int id);
    Color udatecolor(int id, Color color);
}
