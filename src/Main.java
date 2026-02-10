public class Main {
    public static void main(String[] args) {
        Peg peg = new Peg(2, 'A');
        Disk disk = new Disk(5);
        peg.push(disk);
    }
}