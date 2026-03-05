/**
 * Класс, предоставляющий итеративный метод для решения головоломки про ханойские башни
 */
public class Iantonspb {

    /**
     * Узел, формирующий LinkedList, который мы будем использовать для хранения стержней с дисками
     * @param <T> Обобщенный тип, нам понадобиться только вариант для хранения стержней Peg
     */
    class ListNode<T> {
        T payload;                              // полезная информация, хранимая в узле
        ListNode<T> next;                       // ссылка на следующий узел
        ListNode (T payload) {
            this.payload = payload;
        }
    }

    private final PegPrinter printer;               // класс, отрисовывающий происходящее с дисками и стержнями

    public Iantonspb(PegPrinter printer) {
        this.printer = printer;
    }

    /**
     * Итеративный метод, перемещающий диски между заданными стержнями
     * @param from Peg стержень, на котором помещены диски
     * @param to Peg стержень, на которые нужно перенести диски
     * @param aux Peg вспомогательный стержень
     */
    public void moveDisks(Peg from, Peg to, Peg aux) {
        ListNode<Peg> current, next;                    // тут будут храниться текущий и следующий стержни с дисками
        current = setMoveDirection(from, to, aux);      // вызываем функцию, определяющую порядок перестановки дисков
        Disk smaller, other1, other2;       // тут будут храниться самый маленький диск, и два каких-то других диска
        int diskNumber = from.getDiskStackSize();   // в начале все диски хранятся на первом стержне
        while (to.getDiskStackSize()<diskNumber) {  // пока все диски не окажутся на целевом стержне
            smaller = current.payload.pop();        // берем самый маленький диск с текущего стержня
            next = current.next;                    // берем следующий по порядку стержень (порядок был определен ранее)
            next.payload.push(smaller);             // и кладем диск на него
            current = next;                 // текущим всегда считается стержень, на который положили самый мелкий диск
            printer.draw();                 // отрисовываем изменившуюся картинку

            // если после перемещения самого мелкого диска  на исходном и вспомогательном стержнях остались еще диски
            // то перемещаем один диск с одного из двух стержней, на которых сейчас не лежит самый мелкий диск
            if (current.next.payload.getDiskStackSize()!=0 || current.next.next.payload.getDiskStackSize() != 0) {
                // если дисков нет на стержне, следующем за текущим - перекладываем на него диск с другого стержня
                if (current.next.payload.getDiskStackSize() == 0) {
                    other1 = current.next.next.payload.pop();
                    current.next.payload.push(other1);
                } else {
                    // если дисков нет на втором после текущего стержне, перекладываем на него диск со следующего стержня
                    if (current.next.next.payload.getDiskStackSize() == 0) {
                        other1 = current.next.payload.pop();
                        current.next.next.payload.push(other1);
                    } else {
                        // если диски есть на обоих стержнях - снимаем по одному диску с каждого
                        other1 = current.next.payload.pop();
                        other2 = current.next.next.payload.pop();
                        // сравниваем их размеры и кладем больший диск обратно и поверх него меньший диск
                        if (other1.r() > other2.r()) {
                            current.next.payload.push(other1);
                            current.next.payload.push(other2);
                        } else {
                            current.next.next.payload.push(other2);
                            current.next.next.payload.push(other1);
                        }
                    }
                }
                printer.draw();     // и снова отрисовываем картинку
            }
        }
    }

    /**
     * Этот метод определяет направление перемещения дисков по стержням на основании количества дисков, которые
     * требуется переместить, используя для этого закольцованный LinkedList
     * @param from Peg стержень, на котором помещены диски
     * @param to Peg стержень, на которые нужно перенести диски
     * @param aux Peg вспомогательный стержень
     * @return возвращает ListNode<Peg>, содержащий первый элемент списка, хранящий стержень с дисками
     */
    private ListNode<Peg> setMoveDirection(Peg from, Peg to, Peg aux) {
        ListNode<Peg> first, second, third;
        int diskNumber = from.getDiskStackSize();
        // если дисков четное количество - самый маленький диск перекладывается по принципу 1->2->3->1...
        if (diskNumber%2 == 0) {
            first = new ListNode<>(from);       // помещаем стержни в список в соответствующем порядке
            second = new ListNode<>(aux);
            third = new ListNode<>(to);
        }
        // если дисков нечетное количество - порядок будет 1->3->2->1...
        else {
            first = new ListNode<>(from);
            second = new ListNode<>(to);
            third = new ListNode<>(aux);
        }
        first.next = second;        // добавляем в узлы списка ссылки на следующий узел для обхода
        second.next = third;
        third.next = first;     // последний узел ссылается на первый, закольцовывая список
        return first;          // возвращаем первый узел списка
    }

    public static void main(String[] args) {
        int diskNumber; // количество дисков, которые нужно переместить между стержнями
        if (args.length> 0) {
            diskNumber = Integer.getInteger(args[0]);
        } else {
            diskNumber = 3;
        }

        Peg peg1 = new Peg(diskNumber, 'A'); // Создаем стержень с заданным числом дисков и два пустых
        Peg peg2 = new Peg(0, 'B');
        Peg peg3 = new Peg(0, 'C');

        PegPrinter pg = new ConsolePegPrinter(peg1, peg2, peg3); // Создаем объект для отображения состояния стойки

        Iantonspb towers = new Iantonspb(pg);
        towers.moveDisks(peg1, peg3, peg2);     // и начинаем итеративно перекладывать диски
    }
}
