package com.model;

import lombok.Data;

import javax.persistence.Entity;

/**
 * Created by zhaixt on 2017/12/27.
 */
@Data
public class Image {
    private String base64Code;
    private String fileName;
}
