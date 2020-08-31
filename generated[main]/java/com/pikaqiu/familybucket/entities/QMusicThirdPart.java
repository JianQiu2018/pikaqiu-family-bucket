package com.pikaqiu.familybucket.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.Generated;
import com.querydsl.core.types.Path;


/**
 * QMusicThirdPart is a Querydsl query type for MusicThirdPart
 */
@Generated("com.querydsl.codegen.EntitySerializer")
public class QMusicThirdPart extends EntityPathBase<MusicThirdPart> {

    private static final long serialVersionUID = 884478703L;

    public static final QMusicThirdPart musicThirdPart = new QMusicThirdPart("musicThirdPart");

    public final StringPath author = createString("author");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final StringPath lrc = createString("lrc");

    public final StringPath pic = createString("pic");

    public final StringPath title = createString("title");

    public final StringPath url = createString("url");

    public QMusicThirdPart(String variable) {
        super(MusicThirdPart.class, forVariable(variable));
    }

    public QMusicThirdPart(Path<? extends MusicThirdPart> path) {
        super(path.getType(), path.getMetadata());
    }

    public QMusicThirdPart(PathMetadata metadata) {
        super(MusicThirdPart.class, metadata);
    }

}

