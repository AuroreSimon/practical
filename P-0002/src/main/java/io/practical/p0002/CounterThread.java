package io.practical.p0002;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ForkJoinPool;

public class CounterThread {

	private static int threadNb = 4;

	public static void main(String[] args) throws InterruptedException {
		System.out.println("Start threading");
		ForkJoinPool forkJoinPool = new ForkJoinPool(threadNb);
		CountDownLatch countDownLatch = new CountDownLatch(threadNb);

		for (int i = 0; i < threadNb; i++) {
			forkJoinPool.execute(new Counter("" + i, countDownLatch));
		}

		countDownLatch.await();
		System.out.println("End threading");
	}
}
