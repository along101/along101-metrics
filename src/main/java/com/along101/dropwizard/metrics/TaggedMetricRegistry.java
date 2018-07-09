package com.along101.dropwizard.metrics;

import com.codahale.metrics.*;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Created by zhangyicong on 2017/4/20.
 */
public class TaggedMetricRegistry extends MetricRegistry {

    public static final String delimiter = "~";

    public TaggedMetricRegistry() {
        super();
    }

    /**
     * 注册或获取已注册的Metrics
     * @param name
     * @param metric
     * @return
     */
    public TaggedMetric getOrRegisterTaggedMetric(final String name, final TaggedMetric metric) {
        String taggedName = TaggedMetricRegistry.getTaggedName(name, metric.getTags());
        TaggedMetric registeredMetric;
        try {
            registeredMetric = register(taggedName, metric);
        } catch (IllegalArgumentException e) {
            registeredMetric = (TaggedMetric) getMetrics().get(taggedName);
        }
        return registeredMetric;
    }

    /**
     * 注册或获取TaggedCounter指标类型
     * A counter is just a gauge for an AtomicLong instance. You can increment or decrement its value.
     * @param name
     * @param tags
     * @return
     */
    public TaggedCounter taggedCounter(final String name,
                                       final Map<String, String> tags) {
        final TaggedCounter counter = new TaggedCounter(tags);
        return (TaggedCounter) getOrRegisterTaggedMetric(name, counter);
    }

    /**
     * 获取TaggedCounter指标类型
     * @param name
     * @param searchTags
     * @return
     */
    public TaggedCounter getTaggedCounter(final String name,
                                          final Map<String, String> searchTags) {
        return (TaggedCounter) getTaggedMetric(name, searchTags);
    }

    /**
     * 注册或获取TaggedMeter指标类型
     * A meter measures the rate of events over time (e.g., “requests per second”).
     * In addition to the mean rate, meters also track 1-, 5-, and 15-minute moving averages.
     * @param name
     * @param tags
     * @return
     */
    public TaggedMeter taggedMeter(final String name,
                                   final Map<String, String> tags) {
        final TaggedMeter metric = new TaggedMeter(tags);
        return (TaggedMeter) getOrRegisterTaggedMetric(name, metric);
    }

    /**
     * 获取TaggedMeter指标类型
     * @param name
     * @param searchTags
     * @return
     */
    public TaggedMeter getTaggedMeter(final String name,
                                      final Map<String, String> searchTags) {
        return (TaggedMeter) getTaggedMetric(name, searchTags);
    }

    /**
     * 注册或获取TaggedHistogram指标类型
     * A histogram measures the statistical distribution of values in a stream of data.
     * In addition to minimum, maximum, mean, etc., it also measures median, 75th, 90th, 95th, 98th, 99th, and 99.9th percentiles.
     * @param reservoir
     * @param name
     * @param tags
     * @return
     */
    public TaggedHistogram taggedHistogram(final Reservoir reservoir,
                                           final String name, final Map<String, String> tags) {
        final TaggedMetric metric = new TaggedHistogram(reservoir, tags);
        return (TaggedHistogram) getOrRegisterTaggedMetric(name, metric);
    }

    /**
     * 获取TaggedHistogram指标类型
     * @param name
     * @param searchTags
     * @return
     */
    public TaggedHistogram getTaggedHistogram(final String name,
                                              final Map<String, String> searchTags) {
        return (TaggedHistogram) getTaggedMetric(name, searchTags);
    }

    /**
     * 注册或获取TaggedTimer指标类型
     * A timer measures both the rate that a particular piece of code is called and the distribution of its duration.
     * @param name
     * @param tags
     * @return
     */
    public TaggedTimer taggedTimer(final String name, final Map<String, String> tags) {
        final TaggedTimer metric = new TaggedTimer(tags);
        return (TaggedTimer) getOrRegisterTaggedMetric(name, metric);
    }

    /**
     * 获取TaggedTimer指标类型
     * @param name
     * @param searchTags
     * @return
     */
    public TaggedTimer getTaggedTimer(final String name,
                                      final Map<String, String> searchTags) {
        return (TaggedTimer) getTaggedMetric(name, searchTags);
    }

    /**
     * 按Metric name和tags获取指标
     * @param name
     * @param searchTags
     * @return
     */
    public TaggedMetric getTaggedMetric(final String name,
                                        final Map<String, String> searchTags) {
        for (Map.Entry<String, Metric> entry : getMetrics().entrySet()) {
            if (!(entry.getValue() instanceof TaggedMetric)) {
                continue;
            }

            if (!getBaseName(entry.getKey()).equals(name)) {
                continue;
            }

            TaggedMetric taggedMetric = (TaggedMetric) entry.getValue();
            boolean found = true;
            if (searchTags != null) {
                for (Map.Entry<String, String> tag : searchTags.entrySet()) {
                    if (!taggedMetric.getTags().entrySet().contains(tag)) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    return taggedMetric;
                }
            } else if (taggedMetric.getTags() == null) {
                // search tags and metric tags are both null
                return taggedMetric;
            }
        }
        return null;
    }

    public static String getTaggedName(final String name,
                                       final Map<String, String> tags) {
        String taggedHashCode = "0";
        if (tags != null) {
            taggedHashCode = String.valueOf(tags.hashCode());
        }
        return name + delimiter + taggedHashCode;
    }

    public static String getBaseName(final String name) {
        if (name.contains(delimiter)) {
            return name.split(Pattern.quote(delimiter))[0];
        }
        return name;
    }
}
