package com.xhui.recmd.spark.services;

import java.util.regex.Pattern;

/**
 * Created by littlehui on 2016/9/12.
 */
public abstract class AbstractVisitMather implements VisitMather {

    protected Pattern pattern;

    public AbstractVisitMather(String regex) {
        this.pattern = Pattern.compile(regex);
    }

    @Override
    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    @Override
    public void setRegex(String regex) {
        this.pattern = Pattern.compile(regex);
    }
}
