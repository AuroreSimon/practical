package io.practical.p0005;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.EnumSet;

import org.junit.Test;

import sun.misc.Unsafe;

@SuppressWarnings("restriction")
public class P0005Test {

	@Test
	public void test() throws URISyntaxException, IOException, NoSuchFieldException, SecurityException {
		Unsafe unsafe = UnsafeHelper.getUnsafe();
		Path filepath = Paths.get(getClass().getClassLoader().getResource("file.txt").toURI());

		try (FileChannel fileChannel = (FileChannel) Files.newByteChannel(filepath,
				EnumSet.of(StandardOpenOption.READ))) {
			MappedByteBuffer buffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, fileChannel.size());

			long bufferAddress = unsafe.objectFieldOffset(Buffer.class.getDeclaredField("address"));
			long begin = unsafe.getLong(buffer, bufferAddress) + 3;

			System.out.println("By byte");
			byByte(begin, unsafe, begin, buffer.limit());

			System.out.println("\n\nBy short");
			byShort(begin, unsafe, begin, buffer.limit());

			System.out.println("\n\nBy int");
			byInt(begin, unsafe, begin, buffer.limit());

			System.out.println("\n\nBy long");
			byLong(begin, unsafe, begin, buffer.limit());
		}
	}

	private void byByte(long index, Unsafe unsafe, long begin, int end) {
		while (index < (begin + end)) {
			byte[] content = ByteBuffer.allocate(1).put(unsafe.getByte(index)).array();
			System.out.print(new String(content));
			index = index + 1;
		}
	}

	private void byShort(long index, Unsafe unsafe, long begin, int end) {
		while (index < (begin + end)) {
			byte[] content = ByteBuffer.allocate(2).putShort(unsafe.getShort(index)).array();
			System.out.print(new StringBuffer(new String(content)).reverse());
			index = index + 2;
		}

		byByte(index, unsafe, begin, end);
	}

	private void byInt(long index, Unsafe unsafe, long begin, int end) {
		while (index < (begin + end)) {
			byte[] content = ByteBuffer.allocate(4).putInt(unsafe.getInt(index)).array();
			System.out.print(new StringBuffer(new String(content)).reverse());
			index = index + 4;
		}

		byShort(index, unsafe, begin, end);
	}

	private void byLong(long index, Unsafe unsafe, long begin, int end) {
		while (index < (begin + end)) {
			byte[] content = ByteBuffer.allocate(8).putLong(unsafe.getLong(index)).array();
			System.out.print(new StringBuffer(new String(content)).reverse());
			index = index + 8;
		}

		byInt(index, unsafe, begin, end);
	}

}
