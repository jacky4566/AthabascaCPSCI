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