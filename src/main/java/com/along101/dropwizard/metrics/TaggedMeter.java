package com.along101.dropwizard.metrics;

import com.codahale.metrics.Meter;

import java.util.Map;

/**
 * Created by zhangyicong on 2017/4/20.
 */
public class TaggedMeter extends Meter implements TaggedMetric {

	private Map<String, String> tags;
	
	public TaggedMeter(Map<String, String> tags) {
		this.tags = tags;
	}

	public Map<String, String> getTags() {
		return tags;
	}
}
