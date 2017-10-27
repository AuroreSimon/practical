package io.practical.p0004;

import java.util.concurrent.atomic.AtomicInteger;

public class CounterAtomic implements Counter {

	private AtomicInteger counter = new AtomicInteger(0);

	public CounterAtomic() {
	}

	@Override
	public void increment() {
		this.counter.getAndIncrement();
	}

	@Override
	public int getCounter() {
		return this.counter.get();
	}

}
