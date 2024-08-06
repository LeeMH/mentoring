package me.mhlee.demo.domain.point;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QPoints is a Querydsl query type for Points
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QPoints extends EntityPathBase<Points> {

    private static final long serialVersionUID = 61643603L;

    public static final QPoints points = new QPoints("points");

    public final NumberPath<Long> balance = createNumber("balance", Long.class);

    public final NumberPath<Long> beforeBalance = createNumber("beforeBalance", Long.class);

    public final DateTimePath<java.sql.Timestamp> createdAt = createDateTime("createdAt", java.sql.Timestamp.class);

    public final DateTimePath<java.sql.Timestamp> updatedAt = createDateTime("updatedAt", java.sql.Timestamp.class);

    public final NumberPath<Long> userId = createNumber("userId", Long.class);

    public final NumberPath<Long> version = createNumber("version", Long.class);

    public QPoints(String variable) {
        super(Points.class, forVariable(variable));
    }

    public QPoints(Path<? extends Points> path) {
        super(path.getType(), path.getMetadata());
    }

    public QPoints(PathMetadata metadata) {
        super(Points.class, metadata);
    }

}

