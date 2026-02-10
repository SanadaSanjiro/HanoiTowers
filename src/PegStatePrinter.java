import java.util.List;

public class PegStatePrinter {
    private final char[][] pegsState;
    private final char PEG_SYMBOL = '▓';
    private final int high, width;
    private final int peg1Position, peg2Position, peg3Position;
    private final Peg peg1, peg2, peg3;
    private final StringBuilder sb = new StringBuilder();

    public PegStatePrinter(Peg peg1, Peg peg2, Peg peg3) {
        // Создаем массив с высотой, равной числу дисков и шириной, равной тройной ширине самого широкого диска
        int diskStackSize = peg1.getDiskStackSize() + peg2.getDiskStackSize() + peg3.getDiskStackSize();
        high = diskStackSize+1;
        System.out.printf("Высота стойки %d%n", high);
        width = high*2*3+2;
        System.out.printf("Ширина стойки %d%n", width);
        pegsState = new char[high][width];
        peg1Position = width/6;
        System.out.printf("Позиция 1-го стержня %d%n", peg1Position);
        peg2Position = peg1Position + width/3;
        System.out.printf("Позиция 2-го стержня %d%n", peg2Position);
        peg3Position = peg2Position + width/3;
        System.out.printf("Позиция 3-го стержня %d%n", peg3Position);
        this.peg1 = peg1;
        this.peg2 = peg2;
        this.peg3 = peg3;
        initArray();
        drawPegs();
        drawDisks(peg1Position, peg1);
        drawDisks(peg2Position, peg2);
        drawDisks(peg3Position, peg3);
    }

    public void draw() {
        initArray();
        drawPegs();
        drawDisks(peg1Position, peg1);
        drawDisks(peg2Position, peg2);
        drawDisks(peg3Position, peg3);
        print();
    }

    private void initArray() {
        for (int i = 0; i<high; i++) {
            for (int j = 0; j < width; j++) {
                pegsState[i][j] = ' ';
            }
        }
    }

    private void drawPegs() {
        for (int i = 0; i < high; i++) {
            if ((i==0)) {
                pegsState[i][peg1Position] = peg1.getName();
                pegsState[i][peg2Position] = peg2.getName();
                pegsState[i][peg3Position] = peg3.getName();
            } else {
                pegsState[i][peg1Position] = PEG_SYMBOL;
                pegsState[i][peg2Position] = PEG_SYMBOL;
                pegsState[i][peg3Position] = PEG_SYMBOL;
            }
        }
    }

    private void drawDisks(int pegPosition, Peg peg) {
        if (!peg.getDisks().isEmpty()) {
            int size = peg.getDisks().size();
            List<Disk> disks = peg.getDisks();
            for (int i = 0; i<size; i++) {
                for (int j = 0; j<=disks.get(i).getSize();j++) {
                    pegsState[high-1-i][pegPosition-j] = PEG_SYMBOL;
                    pegsState[high-1-i][pegPosition+j] = PEG_SYMBOL;
                }
            }
        }
    }

    private void print() {
        for (int i = 0; i < high; i++) {
            for (int j = 0; j < width; j++) {
                sb.append(pegsState[i][j]);
            }
            System.out.println(sb);
            sb.setLength(0);
        }
    }

    static void main() {
        Peg peg1 = new Peg(3, 'A');
        Peg peg2 = new Peg(2, 'B');
        Peg peg3 = new Peg(1, 'C');
        PegStatePrinter pg = new PegStatePrinter(peg1, peg2, peg3);
        pg.draw();
    }
}
