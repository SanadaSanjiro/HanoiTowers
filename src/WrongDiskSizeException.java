/**
 * Исключение, которое выбрасывается при нарушении правила головоломки, запрещающего класть большие диски на меньшие
 */

public class WrongDiskSizeException extends RuntimeException{
    public WrongDiskSizeException(String message) {
        super(message);
    }
}
