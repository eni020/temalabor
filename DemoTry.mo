package DemoTry
  model Car
    parameter Real breakingDeceleration(unit = "m/s2") = 10.0;
    parameter Real maxSpeed(unit = "m/s") = 13.8;
    Modelica.Blocks.Interfaces.IntegerInput lightcolor;
    TrafficLightColor color;
    Real distance;
    //Distance from starting point
    Real speed;
    Real acceleration;
  initial equation
    distance = 0.0;
    speed = maxSpeed;
    acceleration = 0.0;
  equation
    Integer(color)=lightcolor;
    der(acceleration)=0.0;
    der(distance) = speed;
    der(speed) = acceleration;
    when (color==TrafficLightColor.red) then reinit(acceleration, -1*breakingDeceleration); end when;
    when (speed<=0.0) then reinit(acceleration,0.0); end when;
  end Car;




type TrafficLightColor=enumeration(green,yellow,red,redyellow);

  model TrafficLight
      Modelica.Blocks.Interfaces.IntegerOutput lightcolor;
      Modelica.StateGraph.InitialStep green;
      Modelica.StateGraph.Step yellow;
      Modelica.StateGraph.Step red;
      Modelica.StateGraph.Step redyellow;
      Modelica.StateGraph.Transition redtoredyellow(enableTimer=true, waitTime=30);
      Modelica.StateGraph.Transition redyellowtogreen(enableTimer=true, waitTime=2);
      Modelica.StateGraph.Transition greentoyellow(enableTimer=true, waitTime=5);//To make simulation fast
      Modelica.StateGraph.Transition yellowtored(enableTimer=true, waitTime=2);
      TrafficLightColor color;
    equation
      lightcolor=Integer(color);
      if (green.active) then color=TrafficLightColor.green; 
        elseif (yellow.active) then color=TrafficLightColor.yellow;
        elseif (redyellow.active) then color=TrafficLightColor.redyellow;
        else color=TrafficLightColor.red; end if;
      connect(red.outPort[1], redtoredyellow.inPort);
      connect(redtoredyellow.outPort, redyellow.inPort[1]);
      connect(redyellow.outPort[1], redyellowtogreen.inPort);
      connect(redyellowtogreen.outPort, green.inPort[1]);
      connect(green.outPort[1], greentoyellow.inPort);
      connect(greentoyellow.outPort, yellow.inPort[1]);
      connect(yellow.outPort[1], yellowtored.inPort);
      connect(yellowtored.outPort, red.inPort[1]);
  end TrafficLight;

  model PedestrianCrossing
    Car car;
    TrafficLight trafficlight;
  equation
    connect(trafficlight.color,car.color);
  end PedestrianCrossing;


end DemoTry;
