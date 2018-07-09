package com.along101.dropwizard.metrics;

import com.codahale.metrics.Metric;

import java.util.Map;

/**
 * Created by zhangyicong on 2017/4/20.
 */
public interface TaggedMetric extends Metric {
	Map<String, String> getTags();
}
