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
      this.inventoryMap.set(key, true);
      console.log(this.inventoryMap);
    }
  }