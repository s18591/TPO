package com.company.Sample;

import com.company.Interfaces.IAggregable;
import com.company.Interfaces.IDeeplyCloneable;

public class WeatherOnPlanet implements IAggregable<WeatherOnPlanet, Double>,
        IDeeplyCloneable<WeatherOnPlanet> {


    private double temperature;

    public WeatherOnPlanet() {
    }

    public WeatherOnPlanet(double temperature) {
        this.temperature = temperature;
    }

    public double getTemperature() {
        return temperature;
    }


    @Override
    public Double aggregate(Double intermediateResult) {
        if (intermediateResult == null) {
            return temperature;
        }
        return temperature * intermediateResult;
    }

    @Override
    public WeatherOnPlanet deepClone() {
        WeatherOnPlanet weather = new WeatherOnPlanet();
        weather.temperature = getTemperature();
        return weather;
    }
}
