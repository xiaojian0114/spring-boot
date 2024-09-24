package org.example.service;

import org.example.enity.Special;


import java.util.List;


public interface SpecialService{

    List<Special> getAll();
    List<Special> getByPage(int limit ,  int page);




}
