import java.util.List;

public class PegStatePrinter {
    private final char[][] pegsState;
    int high, width;
    public PegStatePrinter(Peg peg1, Peg peg2, Peg peg3) {
        // Создаем массив с высотой, равной числу дисков и шириной, равной тройной ширине самого широкого диска
        int diskStackSize = peg1.getDiskStackSize() + peg2.getDiskStackSize() + peg3.getDiskStackSize();
        high = diskStackSize;
        width = diskStackSize*2+2;
        pegsState = new char[high][width];
    }

    public void drawPegs() {
        List<Disk> disks1, disks2, disks3;
        StringBuilder sb = new StringBuilder(width);
        for (int i = 0; i < high; i++) {
            System.out.println("Do some work");
        }
    }
}
