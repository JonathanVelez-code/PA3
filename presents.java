import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.HashSet;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

public class presents{
    public static void main(String[] args) {
        Random rand = new Random(System.currentTimeMillis());
        ConcurrentLinkedQueue<Present> linkedList = new ConcurrentLinkedQueue<>();
        HashSet<Integer> presentTags = new HashSet<>();
        int numPresents = 500000;
        AtomicInteger numThankYouCards = new AtomicInteger();

        Thread[] threads = new Thread[4];
        for (int i = 0; i < threads.length; i++) {
            final int servantNum = i + 1;
            threads[i] = new Thread(() -> {
                for (int tag = servantNum; tag <= numPresents; tag += 4) {
                    Present present = new Present(tag);
                    if (presentTags.contains(tag)) {
                        System.out.println("Servant " + servantNum + ": Present with tag " + tag + " already added.");
                    } else {
                        presentTags.add(tag);
                        linkedList.add(present);
                        numThankYouCards.getAndIncrement();
                        System.out.println("Servant " + servantNum + ": Added present with tag " + tag);
                    }

                    if (numThankYouCards.get() < numPresents) {
                        Present removedPresent = linkedList.poll();
                        if (removedPresent != null) {
                            presentTags.remove(removedPresent.getTag());
                            System.out.println("Servant " + servantNum + ": Removed present with tag " + removedPresent.getTag() + " and wrote a Thank you card.");
                            numThankYouCards.getAndDecrement();
                        }
                    }
                }
            });
            threads[i].start();
        }
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        if (numThankYouCards.get() == 0) {
            System.out.println("All presents have been accounted for.");
        } else {
            System.out.println(numThankYouCards + " presents were not accounted for.");
        }
    }
    static class Present {
        private int tag;

        public Present(int tag) {
            this.tag = tag;
        }

        public int getTag() {
            return tag;
        }
    }
}