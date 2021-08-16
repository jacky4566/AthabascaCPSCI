/*
Jackson Wiebe
3519635
11/07/2021
*/

/* Program Overview
A program that uses two nested for loops and the modulus operator (%) to detect and print all prime numbers from 1 to 10,000 exclusive.
*/

/* Test Plan
No User input. Output should consist of all prime numbers between 1 and 10,000 inclusive.
*/

/* Classes
None Used.
*/

public class Program5 {

    public static void main(String[] args) {
        long counter1 = 0;
        for (long suspect = 2; suspect < 10000; suspect++) { // testing from 1 to 10,000 exclusive.
            boolean primeFlag = true; // assume number is prime
            for (int divisor = 2; divisor <= suspect / 2; divisor++) { // Test all divisors between 2 and half the
                                                                       // suspect value
                if (suspect % divisor == 0) {// not a prime
                    primeFlag = false; // set flag to false
                    break; // no need to keep processing
                }
            }
            if (primeFlag) {
                counter1++;
                System.out.println("Prime Found: " + suspect); // print out the prime found
            }
        }
        System.out.println("Primes Found: " + counter1); // print out the prime found
    }
}