package io.practical.p0003;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Vector;
import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;

/**
 * @author <a href="mailto:simon_aurore@hotmail.com">Aurore SIMON</a>
 */
@State(Scope.Thread)
public class MyFirstBenchmark {

	public int elementNbAdd = 500000;
	public int elementNbDelete = 500000;

	public List<Integer> arrayList = new ArrayList<>();
	public List<Integer> linkedList = new LinkedList<>();
	public List<Integer> vectorList = new Vector<>();
	public Set<Integer> hashSetList = new HashSet<>();

	@Benchmark
	public void testMethod() {
		for (int i = 0; i < 100; i++) {
			Math.log(i);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void insertArrayList() {
		for (int i = 0; i < elementNbAdd; i++) {
			arrayList.add(i);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void insertLinkedList() {
		for (int i = 0; i < elementNbAdd; i++) {
			linkedList.add(i);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void insertVectorList() {
		for (int i = 0; i < elementNbAdd; i++) {
			vectorList.add(i);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void insertHashSetList() {
		for (int i = 0; i < elementNbAdd; i++) {
			hashSetList.add(i);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void removeArrayList() {
		for (int i = 0; i < elementNbDelete; i++) {
			arrayList.remove(i);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void removeLinkedList() {
		for (int i = 0; i < elementNbDelete; i++) {
			linkedList.remove(i);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void removeVectorList() {
		for (int i = 0; i < elementNbDelete; i++) {
			vectorList.remove(i);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void removeHashSetList() {
		for (int i = 0; i < elementNbDelete; i++) {
			hashSetList.remove(i);
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void iteratorArrayList() {
		Iterator<Integer> iterator = arrayList.iterator();

		while (iterator.hasNext()) {
			iterator.next();
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void iteratorLinkedList() {
		Iterator<Integer> iterator = linkedList.iterator();

		while (iterator.hasNext()) {
			iterator.next();
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void iteratorVectorList() {
		Iterator<Integer> iterator = vectorList.iterator();

		while (iterator.hasNext()) {
			iterator.next();
		}
	}

	@Benchmark
	@OutputTimeUnit(TimeUnit.SECONDS)
	public void iteratorHashSetList() {
		Iterator<Integer> iterator = hashSetList.iterator();

		while (iterator.hasNext()) {
			iterator.next();
		}
	}

}
