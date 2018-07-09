package com.along101.dropwizard.metrics;

import com.codahale.metrics.JmxAttributeGauge;

import javax.management.MBeanServer;
import javax.management.ObjectName;
import java.util.Map;

/**
 * Created by zhangyicong on 2017/4/20.
 */
public class TaggedJmxAttributeGauge extends JmxAttributeGauge implements TaggedMetric {

    private Map<String, String> tags;

	public TaggedJmxAttributeGauge(ObjectName objectName, String attributeName, Map<String, String> tags) {
        super(objectName, attributeName);
        this.tags = tags;
    }

    public TaggedJmxAttributeGauge(MBeanServer mBeanServer, ObjectName objectName, String attributeName, Map<String, String> tags) {
    	super(mBeanServer, objectName, attributeName);
    	this.tags = tags;
    }
	
	public Map<String, String> getTags() {
		return tags;
	}

}
