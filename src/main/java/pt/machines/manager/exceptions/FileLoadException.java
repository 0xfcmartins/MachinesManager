package pt.machines.manager.exceptions;

/**
 * This class represents an exception that is thrown when there is an error loading a file.
 */
public class FileLoadException extends Exception {

    public FileLoadException(String message) {
        super(message);
    }
}
