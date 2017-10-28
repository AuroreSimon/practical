package io.practical.p0007;

import org.junit.Test;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

public class P0007Test {

	// XML WITHOUT NAMESPACE :

	// nation.xml -> 4.5ko
	// reed.xml -> 285Ko
	// ordres.xml -> 5,378Ko
	// lineitems.xml -> 32,295 Ko

	// XML WITH NAMESPACE :

	// slide3.xml -> 11ko
	// document.xml -> 6,731ko
	// styles.xml -> 162.6 kb

	@Test
	public void dotest() throws Exception {
		// @formatter:off
		Options opt = new OptionsBuilder().include(P0007.class.getSimpleName()).forks(1).build();
		// @formatter:on

		new Runner(opt).run();
	}
}
