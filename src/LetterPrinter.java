public class LetterPrinter {

    private static final int iterations = 5;
    private static final int phraseLength = 15;

    private boolean isComplete = true;
    private char lastLetter = 'C';

    public void printLetter(char letter) {
        int counter = 0;
        while (counter < iterations) {
            if (saveLetter(letter)) counter++;
        }
    }

    private synchronized boolean saveLetter(char letter) {
        while (!isComplete) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if ((lastLetter == 'A' && letter == 'B') ||
                (lastLetter == 'B' && letter == 'C') ||
                (lastLetter == 'C' && letter == 'A')) {
            isComplete = false;
            lastLetter = letter;
            notifyAll();
            return true;
        }
        return false;
    }

    public synchronized void printPhrase() {
        int counter = 0;
        while (counter < phraseLength) {
            while (isComplete) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(lastLetter);
            isComplete = true;
            counter++;
            notifyAll();
        }
    }

}
