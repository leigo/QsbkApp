package com.leigo.qsbk.app.model;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2014/8/19.
 */
public class QbBean implements Serializable {
    public int count;
    public List<Article> items;
    public int total;
    public int page;
}
