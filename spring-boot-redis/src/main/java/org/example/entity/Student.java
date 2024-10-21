package org.example.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serial;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student implements Serializable {

    @Serial
    private static final long serialVersionUID = -8462341859129410264L;

    private String id;
    private String name;
    private Address address;

}
