package io.practical.p0006;

import org.junit.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class P0006Test {

	@Test
	public void test() {
		UnsafeHelper.getUnsafe();
	}

	@Test
	public void dotest() throws Exception {
		// @formatter:off
		Options opt = new OptionsBuilder().include(P0006.class.getSimpleName()).forks(1).build();
		// @formatter:on

		new Runner(opt).run();
	}
}
