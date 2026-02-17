public class RecursiveDiskMover {
    private final PegPrinter printer;

    public RecursiveDiskMover(PegPrinter printer) {
        this.printer = printer;
    }

    public void moveDisks(Peg from, Peg to, Peg aux, int n) {
        if (n==1) {
            Disk disk = from.pop();
            to.push(disk);
            printer.draw();
        } else {
            moveDisks(from, aux, to, n - 1);
            moveDisks(from, to, aux, 1);
            moveDisks(aux, to, from, n - 1);
        }
    }
}
