/*
Hanldes our inventory. Add items based on keys and return them with lookups

Written with help from Ciabatta's revenge Tutorial by Drew Conley

Jackson Wiebe 
3519635
09/10/2023
*/

export class Inventory {
  constructor() {
    this.inventoryMap = new Map();
  }

  has(key) {
    return Boolean(this.inventoryMap.has(key));
  }

  add(key) {
    if (!key) {
      /* Ensure we don't try to add Null or undefined as keys*/
      console.warn("WARNING! Trying to add falsy key to Inventory", key);
      return;
    }
    if (this.inventoryMap.has(key)) {
      let currentValue = this.inventoryMap.get(key);
      this.inventoryMap.set(key, ++currentValue);
    }else{
      this.inventoryMap.set(key, 1);
    }
  }
}