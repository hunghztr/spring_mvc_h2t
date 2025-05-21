package com.jdev.h2t_shop.model.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
public class ListDto<A,B,C> {
    A item1;
    B item2;
    C item3;
}
