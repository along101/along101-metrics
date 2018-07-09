package com.along101.dropwizard.metrics;

import com.codahale.metrics.DerivativeGauge;
import com.codahale.metrics.Gauge;

/**
 * Created by zhangyicong on 2017/4/20.
 */
public abstract class TaggedDerivativeGauge<F, T> extends DerivativeGauge<F, T> implements TaggedGauge<T>{
	protected TaggedDerivativeGauge(Gauge<F> base) {
		super(base);
	}
}
