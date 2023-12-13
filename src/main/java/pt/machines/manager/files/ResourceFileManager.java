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

    /**
     * Reads the content of a file at the specified URI and returns it as an array of strings.
     *
     * @param uri the URI of the file to be loaded
     * @return the content of the file as an array of strings
     * @throws IOException if an I/O error occurs while reading the file
     */
    private static String[] load(URI uri) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get(uri)));
        return content.split(NEWLINE);
    }

    /**
     * Loads the content of a resource file and returns it as an array of strings.
     *
     * @param resourceName the name of the resource file to be loaded
     * @return the content of the resource file as an array of strings
     * @throws Exception if an error occurs while loading the resource file
     */
    public static String[] loadResource(String resourceName) throws Exception {
        validateResourceName(resourceName);
        URL fileUrl = getResourceURL(resourceName);
        validateFileUrl(fileUrl, resourceName);

        return load(fileUrl.toURI());
    }

    /**
     * Returns the URL of a resource file.
     *
     * @param resourceName the name of the resource file
     * @return the URL of the resource file
     */
    private static URL getResourceURL(String resourceName) {
        return ResourceFileManager.class.getResource(SLASH + resourceName);
    }

    /**
     * Validates the given resource name.
     *
     * @param resourceName the name of the resource to be validated
     * @throws FileLoadException if the resource name is null
     */
    private static void validateResourceName(String resourceName) throws FileLoadException {
        if (isNull(resourceName))
            throw new FileLoadException("Invalid resource name!");
    }

    /**
     * Validates the given file URL.
     *
     * @param fileUrl      the URL of the file to be validated
     * @param resourceName the name of the resource associated with the file URL
     * @throws FileLoadException if the file URL is null
     */
    private static void validateFileUrl(URL fileUrl, String resourceName) throws FileLoadException {
        if (isNull(fileUrl))
            throw new FileLoadException("Resource " + resourceName + " not found!");
    }

    /**
     * Stores the content in an array of strings into a resource file specified by the given resource path.
     *
     * @param resourcePath the path of the resource file to be stored
     * @param linesToWrite the content to be written to the file
     * @throws IOException        if an I/O error occurs while writing the file
     * @throws FileLoadException  if the resource name is null or the file URL is null
     * @throws URISyntaxException if the URL of the file has an invalid syntax
     */
    public static void store(String resourcePath, String[] linesToWrite) throws IOException, FileLoadException, URISyntaxException {
        validateResourceName(resourcePath);
        URL fileUrl = getResourceURL(resourcePath);
        validateFileUrl(fileUrl, resourcePath);
        Path filePath = Paths.get(fileUrl.toURI());
        Files.write(filePath, Arrays.asList(linesToWrite), Charset.defaultCharset());
    }
}
