package com.pikaqiu.familybucket.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QAuthUser is a Querydsl query type for AuthUser
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QAuthUser extends EntityPathBase<AuthUser> {

    private static final long serialVersionUID = 1887394477L;

    public static final QAuthUser authUser = new QAuthUser("authUser");

    public final DateTimePath<java.time.LocalDateTime> createTime = createDateTime("createTime", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final DateTimePath<java.time.LocalDateTime> lastLoginTime = createDateTime("lastLoginTime", java.time.LocalDateTime.class);

    public final StringPath password = createString("password");

    public final StringPath phoneNumber = createString("phoneNumber");

    public final NumberPath<Integer> tokenVersion = createNumber("tokenVersion", Integer.class);

    public QAuthUser(String variable) {
        super(AuthUser.class, forVariable(variable));
    }

    public QAuthUser(Path<? extends AuthUser> path) {
        super(path.getType(), path.getMetadata());
    }

    public QAuthUser(PathMetadata metadata) {
        super(AuthUser.class, metadata);
    }

}

