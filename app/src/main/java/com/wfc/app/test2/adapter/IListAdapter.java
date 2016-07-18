package com.wfc.app.test2.adapter;

import java.util.List;

/**
 * Created by wangfengchen on 16/7/5.
 *
 */
public interface IListAdapter<T> {

    void replace(List<T> l);

    void addList(List<T> l);

    void clear();
}
