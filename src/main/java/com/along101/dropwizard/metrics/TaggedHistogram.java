package com.along101.dropwizard.metrics;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.Reservoir;

import java.util.Map;

/**
 * Created by zhangyicong on 2017/4/20.
 */
public class TaggedHistogram extends Histogram implements TaggedMetric {

	private Map<String, String> tags;

	public TaggedHistogram(Reservoir reservoir, Map<String, String> tags) {
		super(reservoir);
		this.tags = tags;
	}

	public Map<String, String> getTags() {
		return tags;
	}

}
