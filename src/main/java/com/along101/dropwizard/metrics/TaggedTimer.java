package com.along101.dropwizard.metrics;

import com.codahale.metrics.Timer;

import java.util.Map;

/**
 * Created by zhangyicong on 2017/4/20.
 */
public class TaggedTimer extends Timer implements TaggedMetric {

	private Map<String, String> tags;
	
	public TaggedTimer(Map<String, String> tags) {
		this.tags = tags;
	}

	public Map<String, String> getTags() {
		return tags;
	}
}
