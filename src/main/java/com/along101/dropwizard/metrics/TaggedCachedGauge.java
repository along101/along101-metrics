package com.along101.dropwizard.metrics;

import com.codahale.metrics.CachedGauge;
import com.codahale.metrics.Clock;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhangyicong on 2017/4/20.
 */
public abstract class TaggedCachedGauge<T> extends CachedGauge<T> implements TaggedGauge<T> {

	protected TaggedCachedGauge(Clock clock, long timeout, TimeUnit timeoutUnit) {
		super(clock, timeout, timeoutUnit);
	}
	
	protected TaggedCachedGauge(long timeout, TimeUnit timeoutUnit) {
		this(Clock.defaultClock(), timeout, timeoutUnit);
    }
}
