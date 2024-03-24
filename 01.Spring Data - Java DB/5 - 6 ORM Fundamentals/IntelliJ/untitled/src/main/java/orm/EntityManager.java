package orm;

import anotations.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Arrays;

public class EntityManager<E> implements DbContext<E> {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(E entity) {
        return false;
    }

    @Override
    public Iterable<E> find(Class<E> table) {
        return null;
    }

    @Override
    public Iterable<E> find(Class<E> table, String where) {
        return null;
    }

    @Override
    public E findFirst(Class<E> table) {
        return null;
    }

    @Override
    public E findFirst(Class<E> table, String where) {
        return null;
    }

    private Field getIdColumn(Class<E> clazz) {
        return Arrays.stream(clazz.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity does not have primary key"));


//        Field[] declaredFields = clazz.getDeclaredFields();
//        for (Field declaredField : declaredFields) {
//            boolean annotationPresent = declaredField.isAnnotationPresent(Id.class);
//            if (annotationPresent) {
//                return declaredField;
//            }
//
//        }
//        throw  new UnsupportedOperationException("Entity does not have primary key");

    }

}
