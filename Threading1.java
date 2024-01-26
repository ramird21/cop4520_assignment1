// Ramir Dalencour
// COP 4520, Spring 2024
// Prof. Juan Parra

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

class actualThreading extends Thread
{
    private int start, end;
    private List<Integer> list;

    actualThreading(int start, int end, List<Integer> list)
    {
        this.start = start;
        this.end = end;
        this.list = list;
    }

    public void run()
    {
        // We make sure to lock in this resourse to make sure to avoid
        // creating race conditions
        synchronized (list)
        {
            for (int i = this.start; i <= this.end; i++)
            {
                if (isPrime(i))
                    list.add(i);
            }
        }
    }

    // I've tweaked the general isPrime function known to all to reduce the overall
    // execution time of the code... We are skipping 4 in the loop and starting at 5 we're
    // also skipping all the even numbers...
    public boolean isPrime(int number)
    {
        if (number <= 1) return false;

        if (number == 2 || number == 3) return true;
        if (number % 2 == 0 || number % 3 == 0) return false;

        for (int i = 5; i <= Math.sqrt(number); i += 2)
        {
            if (number % i == 0) 
                return false;
        }
        return true;
    }
}

public class Threading1
{
    public static void main(String [] args)
    {
        // A few constant declarations for the different thread cuttofs:
        final int firstA = 0, firstB = 12500000;
        final int secondA = 12500001, secondB = 25000000;
        final int thirdA = 25000001, thirdB = 37500000;
        final int fourthA = 37500001, fourthB = 50000000;
        final int fifthA = 50000001, fifthB = 62500000;
        final int sixthA = 62500001, sixthB = 75000000;
        final int seventhA = 75000001, seventhB = 87500000;
        final int eigthA = 87500001, eigthB = 100000000;

        List<Integer> list = new ArrayList<>();

        // Those are our Threads...
        actualThreading myThread1 = new actualThreading(firstA, firstB, list);
        actualThreading myThread2 = new actualThreading(secondA, secondB, list);
        actualThreading myThread3 = new actualThreading(thirdA, thirdB, list);
        actualThreading myThread4 = new actualThreading(fourthA, fourthB, list);
        actualThreading myThread5 = new actualThreading(fifthA, fifthB, list);
        actualThreading myThread6 = new actualThreading(sixthA, sixthB, list);
        actualThreading myThread7 = new actualThreading(seventhA, seventhB, list);
        actualThreading myThread8 = new actualThreading(eigthA, eigthB, list);

        final long start = System.currentTimeMillis();
        
        myThread1.start();
        myThread2.start();
        myThread3.start();
        myThread4.start();
        myThread5.start();
        myThread6.start();
        myThread7.start();
        myThread8.start();

        try {
            // Wait for threads to finish
            myThread1.join();
            myThread2.join();
            myThread3.join();
            myThread4.join();
            myThread5.join();
            myThread6.join();
            myThread7.join();
            myThread8.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        final long end = System.currentTimeMillis();
        long totalTime = (end - start);

        double seconds = totalTime / 1000.0;

        long sum = 0;

        for (int prime : list)
            sum += prime;

        Collections.sort(list);
        int cuttof = list.size() - 10;

        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("primes.txt"));
            
            writer.write(seconds + "s " + list.size() + " " + sum + "\n");

            for (int i = cuttof; i < list.size(); i++)
                writer.write(list.get(i) + "\n");

            writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}