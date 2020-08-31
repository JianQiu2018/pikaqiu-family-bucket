package com.pikaqiu.familybucket.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QArticleInfo is a Querydsl query type for ArticleInfo
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QArticleInfo extends EntityPathBase<ArticleInfo> {

    private static final long serialVersionUID = -671990934L;

    public static final QArticleInfo articleInfo = new QArticleInfo("articleInfo");

    public final NumberPath<Integer> commentNum = createNumber("commentNum", Integer.class);

    public final StringPath content = createString("content");

    public final StringPath coverImage = createString("coverImage");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> heatNum = createNumber("heatNum", Integer.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lastModifyTime = createDateTime("lastModifyTime", java.time.LocalDateTime.class);

    public final NumberPath<Integer> moduleType = createNumber("moduleType", Integer.class);

    public final NumberPath<Integer> pageView = createNumber("pageView", Integer.class);

    public final StringPath title = createString("title");

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public QArticleInfo(String variable) {
        super(ArticleInfo.class, forVariable(variable));
    }

    public QArticleInfo(Path<? extends ArticleInfo> path) {
        super(path.getType(), path.getMetadata());
    }

    public QArticleInfo(PathMetadata metadata) {
        super(ArticleInfo.class, metadata);
    }

}

