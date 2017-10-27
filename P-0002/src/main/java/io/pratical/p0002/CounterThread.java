package io.pratical.p0002;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;

/**
 * @author <a href="mailto:simon_aurore@hotmail.com">Aurore SIMON</a>
 */
public class CounterThread {

	private static int threadNb = 4;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start threading");
		ForkJoinPool test = new ForkJoinPool(threadNb);
		CountDownLatch latch = new CountDownLatch(threadNb);

		for (int i = 0; i < threadNb; i++) {
			test.execute(new Counter("" + i, latch));
		}

		latch.await();
		System.out.println("End threading");
	}
}
