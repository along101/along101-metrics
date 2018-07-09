package com.along101.dropwizard.metrics;

import com.codahale.metrics.Gauge;

/**
 * Created by zhangyicong on 2017/4/20.
 */
public interface TaggedGauge<T> extends TaggedMetric, Gauge<T> {

}
