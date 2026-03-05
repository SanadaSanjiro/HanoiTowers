public class Iantonspb {

    class ListNode<T> {
        T payload;
        ListNode<T> next;
        ListNode (T payload) {
            this.payload = payload;
        }
    }

    private final PegPrinter printer;

    public Iantonspb(PegPrinter printer) {
        this.printer = printer;
    }

    public void moveDisks(Peg from, Peg to, Peg aux) {
        System.out.println(to.getName());
        ListNode<Peg> current, next;
        current = setMoveDirection(from, to, aux);
        Disk smaller, other1, other2;
        int diskNumber = from.getDiskStackSize();
        while (to.getDiskStackSize()<diskNumber) {
            smaller = current.payload.pop();
            next = current.next;
            next.payload.push(smaller);
            current = next;
            printer.draw();
            if (current.next.payload.getDiskStackSize()==0) {
                other1 = current.next.next.payload.pop();
                current.next.payload.push(other1);
            } else {
                if (current.next.next.payload.getDiskStackSize() == 0) {
                    other1 = current.next.payload.pop();
                    current.next.next.payload.push(other1);
                } else {
                    other1 = current.next.payload.pop();
                    other2 = current.next.next.payload.pop();
                    if (other1.r() > other2.r()) {
                        current.next.payload.push(other1);
                        current.next.payload.push(other2);
                    } else {
                        current.next.next.payload.push(other2);
                        current.next.next.payload.push(other1);
                    }
                }
            }
            printer.draw();
        }
    }

    private ListNode<Peg> setMoveDirection(Peg from, Peg to, Peg aux) {
        ListNode<Peg> first, second, third;
        int diskNumber = from.getDiskStackSize();
        if (diskNumber%2 == 0) {
            first = new ListNode<>(from);
            second = new ListNode<>(aux);
            third = new ListNode<>(to);
        } else {
            first = new ListNode<>(from);
            second = new ListNode<>(to);
            third = new ListNode<>(aux);
        }
        first.next = second;
        second.next = third;
        third.next = first;
        return first;
    }

    static void main(String[] args) {
        int diskNumber; // количество дисков, которые нужно переместить между стержнями
        if (args.length> 0) {
            diskNumber = Integer.getInteger(args[0]);
        } else {
            diskNumber = 3;
        }

        Peg peg1 = new Peg(diskNumber, 'A'); // Создаем стержень с заданным числом дисков и два пустых
        Peg peg2 = new Peg(0, 'B');
        Peg peg3 = new Peg(0, 'C');

        PegPrinter pg = new ConsolePegPrinter(peg1, peg3, peg2); // Создаем объект для отображения состояния стойки

        Iantonspb towers = new Iantonspb(pg);
        towers.moveDisks(peg1, peg2, peg3);
    }
}
