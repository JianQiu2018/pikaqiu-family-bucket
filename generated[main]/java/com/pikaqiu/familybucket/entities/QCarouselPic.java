package com.pikaqiu.familybucket.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QCarouselPic is a Querydsl query type for CarouselPic
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QCarouselPic extends EntityPathBase<CarouselPic> {

    private static final long serialVersionUID = -741679952L;

    public static final QCarouselPic carouselPic = new QCarouselPic("carouselPic");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final BooleanPath isOpen = createBoolean("isOpen");

    public final DateTimePath<java.time.LocalDateTime> lastModifyTime = createDateTime("lastModifyTime", java.time.LocalDateTime.class);

    public final StringPath picUrl = createString("picUrl");

    public final StringPath rediectUrl = createString("rediectUrl");

    public final NumberPath<Integer> sort = createNumber("sort", Integer.class);

    public final StringPath title = createString("title");

    public QCarouselPic(String variable) {
        super(CarouselPic.class, forVariable(variable));
    }

    public QCarouselPic(Path<? extends CarouselPic> path) {
        super(path.getType(), path.getMetadata());
    }

    public QCarouselPic(PathMetadata metadata) {
        super(CarouselPic.class, metadata);
    }

}

