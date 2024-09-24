package org.example.mapper;

import org.example.enity.Special;

import java.util.List;


public interface SpecialMapper {

    List<Special> selectAll();
    List<Special> selectByPage(int limit, int offset);


}
