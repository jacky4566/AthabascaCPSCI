/**
* title: HashTable.java
* date:  15/01/2022
* Jackson Wiebe 3519635
* 1.0
*/

/**
* DOCUMENTATION...
*/
/**
*
* HashTable
*
* Purpose and Description:
*  Impliments a basic hash table with a fixed array. Collsions are handled with linear probing. 
*
* Dependent Classes:
*  None
*
* Constructors:
*  None
*
* Class Variables:
*  int magicNumber
*   Contains the magic number for this hash table.
*
*  Integer[] hashTable
*   Our hash table. The size is defined as the magic number based on the maximum number of hashes possible. 
*
* Public Methods:
*  boolean add( int )
*   Adds a new piece of data to the hash table. The input data is hash and stored in the array. 
*   Collsions are handled with linear probing, +1 to the index. If the hash table is full new data is dropped. 
*
* Private Methods:
*  int hash( int )
*   Return a hash of the input data. Hash algorythem is define as input MOD magic number.
*
*/

public class HashTable {
    private int magicNumber = 13;
    private Integer[] hashTable = new Integer[magicNumber];

    public boolean add(int newData){
        int hashKey = hash(newData);
        int fullCounter = magicNumber;
        while(fullCounter-- > 0){
            if (hashTable[hashKey] == null){
                hashTable[hashKey] = newData;
                System.out.println("Adding Int: " + newData + " added to slot: " + hashKey);
                return true;
            }else{
                hashKey++;
                if (hashKey >= magicNumber){
                    hashKey = 0;
                }
                System.out.println("Collsion! Trying new slot: " + hashKey);
            }
        }
        System.out.println("Table full: Item dropped");
        return false;
    }

    private int hash(int data){
        return data % magicNumber;
    }
   
}
