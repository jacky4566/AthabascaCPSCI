import {
  DIRECTION_UP,
  DIRECTION_DOWN,
  DIRECTION_RIGHT,
  DIRECTION_LEFT,
} from "../helpers/consts";

/* A utility function to calculate area of triangle formed by (x1, y1), 
(x2, y2) and (x3, y3) */
function area(x1, y1, x2, y2, x3, y3) {
  return Math.abs((x1 * (y2 - y3) + x2 * (y3 - y1) + x3 * (y1 - y2)) / 2.0);
}

/* A function to check whether point P(x, y) lies inside the triangle formed 
by A(x1, y1), B(x2, y2) and C(x3, y3) */
function isInside(x1, y1, x2, y2, x3, y3, x, y) {
  /* Calculate area of triangle ABC */
  let A = area(x1, y1, x2, y2, x3, y3);

  /* Calculate area of triangle PBC */
  let A1 = area(x, y, x2, y2, x3, y3);

  /* Calculate area of triangle PAC */
  let A2 = area(x1, y1, x, y, x3, y3);

  /* Calculate area of triangle PAB */
  let A3 = area(x1, y1, x2, y2, x, y);

  /* Check if sum of A1, A2 and A3 is same as A */
  return (A == A1 + A2 + A3);
}

export class DirectionControls {
  constructor() {
    this.directionKeys = {
      ArrowDown: DIRECTION_DOWN,
      ArrowUp: DIRECTION_UP,
      ArrowLeft: DIRECTION_LEFT,
      ArrowRight: DIRECTION_RIGHT,
      s: DIRECTION_DOWN,
      w: DIRECTION_UP,
      a: DIRECTION_LEFT,
      d: DIRECTION_RIGHT,
    };
    this.heldDirections = [];

    function parseTouch(touch) {
      if (touch) {
        if (isInside(0, window.innerHeight, window.innerWidth / 2, window.innerHeight / 2, window.innerWidth, window.innerHeight, touch.clientX, touch.clientY))
          return DIRECTION_DOWN;
        if (isInside(0, 0, window.innerWidth / 2, window.innerHeight / 2, window.innerWidth, 0, touch.clientX, touch.clientY))
          return DIRECTION_UP;
        if (isInside(0, 0, 0, window.innerHeight, window.innerWidth / 2, window.innerHeight / 2, touch.clientX, touch.clientY))
          return DIRECTION_LEFT;
        if (isInside(window.innerWidth, 0, window.innerWidth / 2, window.innerHeight / 2, window.innerWidth, window.innerHeight, touch.clientX, touch.clientY))
          return DIRECTION_RIGHT;
      }
    }

    this.directionKeyDownHandler = (e) => {
      const dir = this.directionKeys[e.key];
      if (dir && this.heldDirections.indexOf(dir) === -1) {
        this.heldDirections.unshift(dir);
      }
    };

    this.directionKeyUpHandler = (e) => {
      const dir = this.directionKeys[e.key];
      const index = this.heldDirections.indexOf(dir);
      if (index > -1) {
        this.heldDirections.splice(index, 1);
      }
    };

    this.touchKeyDownHandler = (e) => {
      const dir = parseTouch(e.touches[0]);
      if (dir && this.heldDirections.indexOf(dir) === -1) {
        this.heldDirections.unshift(dir);
      }
    };

    this.touchKeyUpHandler = (e) => {
      this.heldDirections = [];

    };

    document.addEventListener("keydown", this.directionKeyDownHandler);
    document.addEventListener("keyup", this.directionKeyUpHandler);
    document.addEventListener("touchstart", this.touchKeyDownHandler);
    document.addEventListener("touchend", this.touchKeyUpHandler);
  }

  get direction() {
    return this.heldDirections[0];
  }

  unbind() {
    document.removeEventListener("keydown", this.directionKeyDownHandler);
    document.removeEventListener("keyup", this.directionKeyUpHandler);
  }
}