import java.awt.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Peg {
    private final LinkedList<Disk> disks = new LinkedList<>();
    private final int number;

    public Peg(int diskStackSize, int pegNumber) {
        for (int i = diskStackSize; i>0; i--) {
            Disk disk = new Disk(i);
            disks.addLast(disk);
        }
        this.number = pegNumber;
    }

    public int getDiskStackSize() {
        return disks.size();
    }

    public List<Disk> getDisks() {
        List<Disk> copy = new LinkedList<>();
        Collections.copy(copy, disks);
        return copy;
    }

    public void push(Disk disk) {
        System.out.println("Disk " + disk.getSize() + " put on the peg number " + number);
        disks.addLast(disk);
    }

    public Disk pop() {
        if (!disks.isEmpty()) {
            throw new IllegalStateException("No disks left on this peg!");
        }
        Disk disk = disks.removeLast();
        System.out.println("Disk " + disk.getSize() + " taken from the peg number " + number);
        return disk;
    }
}
