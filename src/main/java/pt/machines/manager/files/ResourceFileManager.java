package pt.machines.manager.files;

import pt.machines.manager.exceptions.FileLoadException;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

import static java.util.Objects.isNull;

/**
 * The FilesLoader class is responsible for loading resource files and returning their contents as an array of strings.
 * It provides a static method {@code loadResource} that takes a resource name as input and returns the content of the
 * resource file.
 */
public class ResourceFileManager {

    private static final String SLASH = "/";
    private static final String NEWLINE = "\n";

    private static String[] load(URI uri) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(uri)));
        return content.split(NEWLINE);
    }

    public static String[] loadResource(String resourceName) throws Exception {
        validateResourceName(resourceName);
        URL fileUrl = getResourceURL(resourceName);
        validateFileUrl(fileUrl, resourceName);

        return load(fileUrl.toURI());
    }

    private static URL getResourceURL(String resourceName) {
        return ResourceFileManager.class.getResource(SLASH + resourceName);
    }

    private static void validateResourceName(String resourceName) throws FileLoadException {
        if (isNull(resourceName))
            throw new FileLoadException("Invalid resource name!");
    }

    private static void validateFileUrl(URL fileUrl, String resourceName) throws FileLoadException {
        if (isNull(fileUrl))
            throw new FileLoadException("Resource " + resourceName + " not found!");
    }

    public static void store(String resourcePath, String[] linesToWrite) throws IOException, FileLoadException, URISyntaxException {
        validateResourceName(resourcePath);
        URL fileUrl = getResourceURL(resourcePath);
        validateFileUrl(fileUrl, resourcePath);
        Path filePath = Paths.get(fileUrl.toURI());
        Files.write(filePath, Arrays.asList(linesToWrite), Charset.defaultCharset());
    }
}
