package com.pikaqiu.familybucket.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QUploadFile is a Querydsl query type for UploadFile
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QUploadFile extends EntityPathBase<UploadFile> {

    private static final long serialVersionUID = -984292937L;

    public static final QUploadFile uploadFile = new QUploadFile("uploadFile");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final StringPath fileName = createString("fileName");

    public final StringPath fileType = createString("fileType");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath mountUrl = createString("mountUrl");

    public final StringPath url = createString("url");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QUploadFile(String variable) {
        super(UploadFile.class, forVariable(variable));
    }

    public QUploadFile(Path<? extends UploadFile> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUploadFile(PathMetadata metadata) {
        super(UploadFile.class, metadata);
    }

}

