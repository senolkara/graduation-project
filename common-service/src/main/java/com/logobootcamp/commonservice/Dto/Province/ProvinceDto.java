package com.logobootcamp.commonservice.Dto.Province;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProvinceDto {
    private Long id;
    private String name;
}
