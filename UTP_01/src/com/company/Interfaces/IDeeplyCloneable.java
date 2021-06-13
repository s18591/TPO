package com.company.Interfaces;

public interface IDeeplyCloneable<TElement extends IDeeplyCloneable<TElement>> {

    TElement deepClone();

}