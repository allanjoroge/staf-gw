package com.legoons.stafgw.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GatewaySearchRequest {

    //sorting
    private String[] sortColumns;
    private String[] sortDirections;
    //pagination
    private Integer pageNo = 0;
    private Integer pageSize = 10;
}
