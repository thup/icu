package com.thup.icu.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 *@Author tlibn
 *@Date 2021/9/11 15:06
 **/
@AllArgsConstructor
@Data
public class BaseDto implements Serializable {

    private String key;

    private String value;

    private String desc;

}
