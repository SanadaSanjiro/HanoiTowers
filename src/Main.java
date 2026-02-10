    void main() {
        int diskNumber = 5;
        Peg peg1 = new Peg(diskNumber, 'A');
        Peg peg2 = new Peg(0, 'B');
        Peg peg3 = new Peg(0, 'C');
        PegStatePrinter pg = new PegStatePrinter(peg1, peg2, peg3);
        pg.draw();
        RecursiveDiskMover mover = new RecursiveDiskMover(pg);
        mover.moveDisks(peg1, peg3, peg2, diskNumber);
}