package com.pikaqiu.familybucket.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QHeatDetail is a Querydsl query type for HeatDetail
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QHeatDetail extends EntityPathBase<HeatDetail> {

    private static final long serialVersionUID = 1596059899L;

    public static final QHeatDetail heatDetail = new QHeatDetail("heatDetail");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final StringPath heatDescribe = createString("heatDescribe");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final NumberPath<Long> resourceId = createNumber("resourceId", Long.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QHeatDetail(String variable) {
        super(HeatDetail.class, forVariable(variable));
    }

    public QHeatDetail(Path<? extends HeatDetail> path) {
        super(path.getType(), path.getMetadata());
    }

    public QHeatDetail(PathMetadata metadata) {
        super(HeatDetail.class, metadata);
    }

}

