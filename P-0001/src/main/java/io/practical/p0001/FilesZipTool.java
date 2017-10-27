package io.practical.p0001;

import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class FilesZipTool {

	private static String SRC = new StringBuilder().append(System.getProperty("user.dir"))
			.append(FileSystems.getDefault().getSeparator()).append("src")
			.append(FileSystems.getDefault().getSeparator()).append("main")
			.append(FileSystems.getDefault().getSeparator()).append("resources")
			.append(FileSystems.getDefault().getSeparator()).toString();

	private static String FILE_PATH = new StringBuilder().append(SRC).append("file")
			.append(FileSystems.getDefault().getSeparator()).toString();

	private static String COPY_FILE_PATH = new StringBuilder().append(SRC).append("copy")
			.append(FileSystems.getDefault().getSeparator()).toString();

	private static String ZIP_PATH = new StringBuilder().append(SRC).append("zip")
			.append(FileSystems.getDefault().getSeparator()).toString();

	public static void main(String[] args) throws IOException {
		Path zipDirectoryPath = Paths.get(ZIP_PATH);
		Path copyDirectoryPath = Paths.get(COPY_FILE_PATH);
		Files.createDirectories(zipDirectoryPath);
		Files.createDirectories(copyDirectoryPath);

		Path pathZip = Paths.get(ZIP_PATH + "pratical.zip");
		insertZip(pathZip);
		extractZip(pathZip);
	}

	public static void insertZip(Path pathZip) throws IOException {
		try (FileSystem fileSystem = createZip(pathZip)) {

			for (int i = 1; i <= 3; i++) {
				String fileName = new StringBuilder().append("file").append(i).append(".txt").toString();
				Path file = Paths.get(FILE_PATH + fileName);
				Files.copy(file, fileSystem.getPath("/", fileName));
			}
		}
	}

	public static FileSystem createZip(Path pathZip) throws IOException {
		Files.deleteIfExists(pathZip);

		URI uri = URI.create("jar:file:" + pathZip.toUri().getPath());
		Map<String, String> env = new HashMap<>();
		env.put("create", "true");

		return FileSystems.newFileSystem(uri, env);
	}

	public static void extractZip(Path pathZip) throws IOException {
		FileSystem fileSystem = FileSystems.newFileSystem(pathZip, null);
		Path directory = Paths.get(COPY_FILE_PATH);

		Files.walk(fileSystem.getPath("/")).forEach(path -> {
			Path fileZip = path.getFileName();

			if (fileZip != null) {
				String fileName = fileZip.toString();

				try {
					Files.deleteIfExists(Paths.get(COPY_FILE_PATH + fileName));
					Path file = Files.createTempFile(directory, fileName.substring(0, fileName.length() - 4), ".txt");
					Files.copy(fileZip, file, StandardCopyOption.REPLACE_EXISTING);
					Files.move(file, file.resolveSibling(fileName));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		});
	}

}
