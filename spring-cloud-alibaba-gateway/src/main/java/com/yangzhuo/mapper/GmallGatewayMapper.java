package com.yangzhuo.mapper;

import com.yangzhuo.entity.GateWayEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface GmallGatewayMapper extends Mapper{
    @Select("select * from gmall_gateway")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "routeId", column = "route_id"),
            @Result(property = "routeName", column = "route_name"),
            @Result(property = "routePattern", column = "route_pattern"),
            @Result(property = "routeType", column = "route_type"),
            @Result(property = "routeUrl", column = "route_url")
    })
    public List<GateWayEntity> gatewayAll();
}
