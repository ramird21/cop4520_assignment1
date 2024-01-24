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

    public void run() // We could add synchronized here (before the void)
    {
        synchronized (list) // Or we could just remove this synchronized block of code...
        {
            for (int i = this.start; i <= this.end; i++)
            {
                if (isPrime(i))
                    list.add(i);
            }
        }
    }

    public boolean isPrime(int number)
    {
        if (number <= 1) return false;

        for (int i = 2; i <= Math.sqrt(number); i++) 
            if (number % i == 0) 
                return false;

        return true;
    }
}

public class Threading1
{
    public static void main(String [] args)
    {
        List<Integer> list = new ArrayList<>();
        // Those are our Threads...
        actualThreading myThread1 = new actualThreading(0, 12500000, list);
        actualThreading myThread2 = new actualThreading(12500001, 25000000, list);
        actualThreading myThread3 = new actualThreading(25000001, 37500000, list);
        actualThreading myThread4 = new actualThreading(37500001, 50000000, list);
        actualThreading myThread5 = new actualThreading(50000001, 62500000, list);
        actualThreading myThread6 = new actualThreading(62500001, 75000000, list);
        actualThreading myThread7 = new actualThreading(75000001, 87500000, list);
        actualThreading myThread8 = new actualThreading(87500001, 100000000, list);

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
            // We actually put the things we are writing to the file in here!!
            writer.write(seconds + "s " + list.size() + " " + sum + "\n");

            for (int i = cuttof; i < list.size(); i++)
                writer.write(list.get(i) + "\n");

            writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }
}