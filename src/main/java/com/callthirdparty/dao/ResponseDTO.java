package com.callthirdparty.dao;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDTO {

    private Long id;
    private String title;
    private Double price;
    private String description;
    private String category;
    private String image;
}
