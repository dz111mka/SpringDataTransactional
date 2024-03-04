package ru.chepikov.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductIdsRequest {
    private List<Long> productIds;

}
