package automata;

import java.util.ArrayList;
import java.util.List;

public class Car extends Model{

//    private List<Type> parameters;
//    private List<Type> variables;

    Parameter breakingDeceleration;
    Parameter maxSpeed;
    Variable distance;
    Variable speed;
    Variable acceleration;


    Car(){
        breakingDeceleration = new Parameter("m/s2", -10.0);
        maxSpeed = new Parameter("m/s", 13.8);
        distance = new Variable(0.0);
        speed = new Variable(maxSpeed.getValue());
        acceleration = new Variable(breakingDeceleration.getValue());
    }


    public void equation() {
        der(acceleration, new Variable(0.0));
        der(distance, speed);
        der(speed, acceleration);
        if(speed.getValue() <= 0.0) { acceleration.setValue(0.0); }
    }


}
