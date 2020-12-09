package com.yangzhuo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GateWayEntity {

    private Long id;

    private String routeId;

    private String routeName;

    private String routePattern;

    private String routeType;

    private String routeUrl;

}
