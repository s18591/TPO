package com.company;

import com.company.Interfaces.IAggregable;
import com.company.Interfaces.IContainer;
import com.company.Interfaces.IDeeplyCloneable;

import java.util.List;

public class Container<T extends IAggregable<T, S> & IDeeplyCloneable<T>, S> implements IContainer<T, S> {


    private List<T> list;

    public Container(List<T> list) {
        this.list = list;
    }

    @Override
    public List<T> elements() {
        return list;
    }

    @Override
    public S aggregateAllElements() {
        S tmp = null;
        if (elements() == null) {
            return null;
        }
        for (int i = 0; i < elements().size(); i++) {
          tmp = elements().get(i).aggregate(tmp);
        }
        return tmp;
    }


    @Override
    public T cloneElementAtIndex(int index) {
        if (index > elements().size() || elements() ==  null || index < 0) {
            return null;
        }
        return elements().get(index).deepClone();
    }
}
