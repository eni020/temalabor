model Car	
	parameter Real breakingDeceleration(unit = "m/s2") = -10.0;
	parameter Real maxSpeed(unit = "m/s") = 13.8;
	Real distance;
	Real speed;
	Real acceleration;
initial equation
	distance = 0.0;
	speed = maxSpeed;
	acceleration = breakingDeceleration;
equation
	der(acceleration) = 0.0;
	der(distance) = speed;
	der(speed) = acceleration;
	when (speed <= 0.0) then reinit(acceleration, 0.0); end when;
end Car;
