package com.kewal;

import java.time.Duration;
import java.time.Instant;
import java.util.Comparator;
import java.util.concurrent.ConcurrentLinkedQueue;

public class StatisticImpl implements Statistic {
	
	private ConcurrentLinkedQueue<Value> values = new ConcurrentLinkedQueue<>();
	
	@Override
	public void event(int value) {
		values.add(new Value(value, Instant.now()));
	}

	@Override
	public float mean() {
		return (float) values.stream().mapToDouble(Value::getValue).average().orElse(0.0);
	}

	@Override
	public float mean(int lastNMinutes) {
		return (float) values.stream().filter(d ->
			Duration.between(d.getTime(), Instant.now()).compareTo(Duration.ofMinutes(lastNMinutes)) >= 0 
		).mapToDouble(Value::getValue).average().orElse(0.0);
	}

	@Override
	public float variance() {
		double mean = values.stream().mapToInt(d -> d.getValue()).average().getAsDouble();
		return (float) values.stream().map(Value::getValue)
				.map(i -> i - mean).map(i -> i*i)
                .mapToDouble(i -> i).average().getAsDouble();
	}

	@Override
	public int minimum() {
		return values.stream().map(Value::getValue).min(Integer::compare).get();
	}

	@Override
	public int maximum() {
		return values.stream().map(Value::getValue).min(Comparator.reverseOrder()).get();
	}
}
