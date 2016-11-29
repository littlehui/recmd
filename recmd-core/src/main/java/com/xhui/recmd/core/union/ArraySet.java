package com.xhui.recmd.core.union;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by littlehui on 2016/10/13.
 */
public class ArraySet<E> {

    private static final long serialVersionUID = -7548294595221509577L;

    private ArrayList<E> list = new ArrayList<E>();

    /**
     * @param index
     * @return
     * @see java.util.ArrayList#get(int)
     */
    public E get(int index) {
        return list.get(index);
    }

    public void set(int index, E e) {
        list.set(index, e);
    }

    public boolean add(E o) {
        if (!list.contains(o)) {
            return list.add(o);
        } else {
            return false;
        }
    }

    public int size() {
        return list.size();
    }
}
