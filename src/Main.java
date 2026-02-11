public class Main {
    public static void main(String[] args) {
        int diskNumber; // количество дисков, которые нужно переместить между стержнями
        if (args.length> 0) {
            diskNumber = Integer.parseInt(args[0]);
        } else {
            diskNumber = 5;
        }

        Peg peg1 = new Peg(diskNumber, 'A'); // Создаем стержень с заданным числом дисков и два пустых
        Peg peg2 = new Peg(0, 'B');
        Peg peg3 = new Peg(0, 'C');

        PegStateDisplay pg = new PegStateDisplay(peg1, peg2, peg3); // Создаем объект для отображения состояния стойки

        pg.draw();  // Отрисовываем стойку

        RecursiveDiskMover mover = new RecursiveDiskMover(pg);
        mover.moveDisks(peg1, peg3, peg2, diskNumber); // Запускаем рекурсивный метод для перемещения дисков между стержнями
    }
}