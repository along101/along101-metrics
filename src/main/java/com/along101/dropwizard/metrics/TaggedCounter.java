package com.along101.dropwizard.metrics;

import com.codahale.metrics.Counter;

import java.util.Map;

/**
 * Created by zhangyicong on 2017/4/20.
 */
public class TaggedCounter extends Counter implements TaggedMetric {

	private Map<String, String> tags;
	
	public TaggedCounter(Map<String, String> tags) {
		this.tags = tags;
	}

	public Map<String, String> getTags() {
		return tags;
	}
}
