package com.educloud.assessment.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
//@RequiredArgsConstructor
//@NoArgsConstructor
public class FieldMeta {
    private String name;
    private String label;

    public FieldMeta(String name, String label) {
        this.name = name;
        this.label = label;
    }
}