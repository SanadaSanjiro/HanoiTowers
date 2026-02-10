import java.awt.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Стержень, на который надеваются диски
 */
public class Peg {
    private final LinkedList<Disk> disks = new LinkedList<>();
    private final char name;

    public Peg(int diskStackSize, char name) {
        for (int i = diskStackSize; i>0; i--) {
            Disk disk = new Disk(i);
            disks.addLast(disk);
        }
        this.name = name;
    }

    /**
     * Получить количесвто дисков на стержне
     * @return int текущее количество дисков, помещенных на стержень
     */
    public int getDiskStackSize() {
        return disks.size();
    }

    /**
     * Возвращает имя стержня
     * @return char
     */
    public char getName() {
        return name;
    }

    /**
     * Получить все диски, помещенные на стержень
     * @return List<Disk> список дисков, помещенных на стержень
     */
    public List<Disk> getDisks() {
        return disks;
    }

    /**
     * Поместить диск на стержень. Если размер диска, который помещают,
     * будет больше верхнего диска на стержне, будет выброшено исключение WrongDiskSizeException
     * @param disk Disk, который требуется поместить на стержень.
     */
    public void push(Disk disk) {
        System.out.printf("Диск %d помещен на стержень %c%n", disk.getSize(), name);
        if (!disks.isEmpty() && disk.getSize()>disks.getLast().getSize()) {
            throw new WrongDiskSizeException(
                    String.format( "Диск размером %d, помещаемый на стержень %c, " +
                            " больше предыдущего диска размером %d",
                            disk.getSize(), name, disks.getLast().getSize()));
        }
        disks.addLast(disk);
    }

    /**
     * Снять верхний диск со стержня. Если дисков на стержне нет,
     * будет выброшено исключение IllegalStateException
     * @return Disk верхний диск на стержне
     */
    public Disk pop() {
        if (disks.isEmpty()) {
            throw new IllegalStateException("На стержне не осталось дисков!");
        }
        Disk disk = disks.removeLast();
        System.out.printf("Диск %d снят со стержня %c%n", disk.getSize(), name);
        return disk;
    }
}