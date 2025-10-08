package org.pragma.creditya.dynamodb.helper;

public interface CustomMapper<E, D> {
    D toData(E entity);
    E toEntity(D data);
}