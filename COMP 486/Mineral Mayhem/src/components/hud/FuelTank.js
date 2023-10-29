/*
Renders graphics for the fuel tank.

Jackson Wiebe 
3519635
09/10/2023
*/

export default function FuelTank(fuel) {
  const filledHeight = (100.0 - fuel.fuel.fuelRemaining) + "%";
  const filledHeightGrad = (Math.min(100, (100.0 - fuel.fuel.fuelRemaining) + 5.0)) + "%";

  return (
    <svg height="64" width="32" xmlns="http://www.w3.org/2000/svg">
      <linearGradient id="myGradient" gradientTransform="rotate(90)">
        <stop stopColor="#c0f0c0" offset={filledHeight} />
        <stop stopColor="gold" offset={filledHeightGrad} />
        <stop stopColor="gold" offset="100%" />
      </linearGradient>

      {/* Draw the background body */}
      <rect width="100%" height="100%" rx="10" fill="url('#myGradient')" stroke="#000" strokeWidth={2} />

      {/* Add Text */}
      <text x="50%" y="50%" textAnchor="middle" fontFamily="monospace">
        <tspan dy="0em">FUEL</tspan>
        <tspan x="50%" dy="1em">{Math.floor(fuel.fuel.fuelRemaining)}</tspan>
      </text>
    </svg>
  );
}