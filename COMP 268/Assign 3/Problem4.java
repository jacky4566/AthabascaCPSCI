/**
* title: Problem4.java
* description:  Do some work on arrays of size 25
* date: 21/08/2021
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* Problem1
*
* Purpose and Description:
* Do some work on arrays of size 25
*
* Compiling and running instructions
* Compile:  javac Problem4.java
* Run:      java Problem4
*
* Classes:
*
*/

/**
* Test Plan
*/
/**
* Run the application.
* EXPECTED:
* Static test:

for  0; 0.0 + 0.0 = 0.0
for  1; 1.0 + 1.0 = 2.0
for  2; 2.0 + 4.0 = 6.0
for  3; 3.0 + 9.0 = 12.0
for  4; 4.0 + 16.0 = 20.0
for  5; 5.0 + 25.0 = 30.0
for  6; 6.0 + 36.0 = 42.0
for  7; 7.0 + 49.0 = 56.0
for  8; 8.0 + 64.0 = 72.0
for  9; 9.0 + 81.0 = 90.0
for 10; 10.0 + 100.0 = 110.0
for 11; 11.0 + 121.0 = 132.0
for 12; 12.0 + 144.0 = 156.0
for 13; 13.0 + 169.0 = 182.0
for 14; 14.0 + 196.0 = 210.0
for 15; 15.0 + 225.0 = 240.0
for 16; 16.0 + 256.0 = 272.0
for 17; 17.0 + 289.0 = 306.0
for 18; 18.0 + 324.0 = 342.0
for 19; 19.0 + 361.0 = 380.0
for 20; 20.0 + 400.0 = 420.0
for 21; 21.0 + 441.0 = 462.0
for 22; 22.0 + 484.0 = 506.0
for 23; 23.0 + 529.0 = 552.0
for 24; 24.0 + 576.0 = 600.0

* ACTUAL:
* Output as expected above
*
* Good data cases:
*   No user input

* Bad data cases:
*   No user input

*/

public class Problem4 {

    public static void main(String[] args) { // Main program
        final int ARRAYSIZE = 25;
        float arrayOne[] = new float[ARRAYSIZE];
        float arrayTwo[] = new float[ARRAYSIZE];
        float arrayThree[] = new float[ARRAYSIZE];

        for (int i = 0; i < ARRAYSIZE; i++){
            arrayOne[i] = (float)i; //fill the first array with the loop counter value 
            arrayTwo[i] = (float)i * (float)i; //fill the second array with the loop counter value squared
            arrayThree[i] = arrayOne[i] + arrayTwo[i]; //adds the corresponding elements in the first two arrays and puts the result in the corresponding element of the third array
            System.out.println("for " + String.format("%2d", i) + "; " + String.format("%3.1f", arrayOne[i]) + " + " + String.format("%3.1f", arrayTwo[i]) + " = " + String.format("%3.1f", arrayThree[i]));
        }
    }
}
