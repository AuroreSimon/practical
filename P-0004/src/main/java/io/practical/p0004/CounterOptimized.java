package io.practical.p0004;

public class CounterOptimized implements Counter {

	private int counter = 0;

	public CounterOptimized() {
	}

	@Override
	public void increment() {
		this.counter++;
	}

	@Override
	public int getCounter() {
		return this.counter;
	}

}
