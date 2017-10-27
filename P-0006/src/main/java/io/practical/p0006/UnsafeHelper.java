package io.practical.p0006;

import java.lang.reflect.Field;
import java.security.AccessController;
import java.security.PrivilegedActionException;
import java.security.PrivilegedExceptionAction;

public final class UnsafeHelper {

	@SuppressWarnings("restriction")
	public static final sun.misc.Unsafe UNSAFE;

	static {

		@SuppressWarnings("restriction")
		final PrivilegedExceptionAction<sun.misc.Unsafe> action = new PrivilegedExceptionAction<sun.misc.Unsafe>() {
			/**
			 * {@inheritDoc}
			 */
			@Override
			public sun.misc.Unsafe run() throws NoSuchFieldException, IllegalAccessException {
				Field theUnsafe = sun.misc.Unsafe.class.getDeclaredField("theUnsafe");
				theUnsafe.setAccessible(true);
				return (sun.misc.Unsafe) theUnsafe.get(null);
			}
		};

		try {
			UNSAFE = AccessController.doPrivileged(action);
		} catch (PrivilegedActionException cause) {
			throw new RuntimeException("Unable to load unsafe", cause);
		}
	}

	private UnsafeHelper() {
	}

	@SuppressWarnings("restriction")
	public static sun.misc.Unsafe getUnsafe() {
		return UNSAFE;
	}

}
