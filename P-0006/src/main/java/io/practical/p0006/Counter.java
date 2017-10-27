package io.practical.p0006;

public class Counter {

	int counter;

	public Counter() {
		counter = 0;
	}

	public int getValue() {
		return this.counter;
	}

	public void increment() {
		counter++;
	}
}
