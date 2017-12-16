package mk.ukim.finki.aud8;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.stream.IntStream;

public class EratosthenesSieve {
    private static boolean isPrime(int n) {
        return n >= 2 && IntStream.rangeClosed(2, n / 2)
                .noneMatch(i -> n % i == 0);
    }

    private List<Integer> getPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        IntStream.rangeClosed(2, n)
                .forEach(primes::add);
        for (int i = 0; i < primes.size(); ++i) {
            int currentPrime = primes.get(i);
            ListIterator<Integer> iterator = primes.listIterator(i + 1);
            while (iterator.hasNext())
                if (iterator.next() % currentPrime == 0)
                    iterator.remove();
        }
        return primes;
    }

    public static void main(String[] args) {
        for (int i = 2; i < 100; ++i)
            if (isPrime(i))
                System.out.print(i + " ");

        System.out.println();

        EratosthenesSieve sieve = new EratosthenesSieve();
        List<Integer> primes = sieve.getPrimes(100);
        primes.forEach(prime -> System.out.print(prime + " "));
    }
}
