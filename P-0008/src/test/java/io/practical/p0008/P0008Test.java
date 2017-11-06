package io.practical.p0008;

import org.junit.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class P0008Test {

	@Test
	public void dotest() throws Exception {
		// @formatter:off
		Options opt = new OptionsBuilder().include(P0008.class.getSimpleName()).forks(1).build();
		// @formatter:on

		new Runner(opt).run();
	}
}
