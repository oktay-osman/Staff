package com.example.demo.services;

import java.io.Serializable;
import java.util.List;

public interface Writer {
    void write(List<? extends Serializable> items, String filePath);
}
