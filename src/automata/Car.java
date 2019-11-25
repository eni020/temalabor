package automata;

import java.util.ArrayList;
import java.util.List;

public class Car {

//    private List<Type> parameters;
//    private List<Type> variables;

    Parameter breakingDeceleration;
    Parameter maxSpeed;
    Variable distance;
    Variable speed;
    Variable acceleration;


    Car(){
        breakingDeceleration = new Parameter("m/s2", 10.0);
        maxSpeed = new Parameter("m/s", 13.8);
        distance = new Variable(0.0);
        speed = new Variable(maxSpeed.getValue());
        acceleration = new Variable(0.0);

//        parameters = new ArrayList<>();
//        variables = new ArrayList<>();
//        Acceleration breakingDeceleration = new Acceleration("breakingDeceleration", 10.0);
//        Velocity maxSpeed = new Velocity("maxSpeed", 13.8);
//        Displacement distance = new Displacement("distance",0.0);
//        Velocity speed = new Velocity("speed", maxSpeed.getValue());
//        Acceleration acceleration = new Acceleration("acceleration",0.0);
//        parameters.add(breakingDeceleration);
//        parameters.add(maxSpeed);
//        variables.add(distance);
//        variables.add(speed);
//        variables.add(acceleration);
    }


    public void equation() {
        der(distance, speed);
        der(speed, acceleration);
        if(speed.getValue() <= 0.0) {
            acceleration.setValue(0.0);
        }
    }

    public void der(Variable v1, Variable v2) {
    }
}
