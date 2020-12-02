public class Main {

    public static void main(String[] args) {

        LetterPrinter lp = new LetterPrinter();

        new Thread(() -> {
            lp.printLetter('A');
        }).start();
        new Thread(() -> {
            lp.printLetter('B');
        }).start();
        new Thread(() -> {
            lp.printLetter('C');
        }).start();

        lp.printPhrase();


    }
}
