package com.example.demo.services;

import java.io.Serializable;
import java.util.List;

public interface Reader {
    List<? extends Serializable> read (String pathFile);
}
