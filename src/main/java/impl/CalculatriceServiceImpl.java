package main.java.impl;

import service.CalculatriceInterface;

public class CalculatriceServiceImpl implements CalculatriceInterface {
    @Override
    public double addition(double note1, double note2) {
        return note1 + note2;
    }

    @Override
    public double soustraction(double note1, double note2) {
        return note1 - note2;
    }

    @Override
    public double multiplication(double note1, double note2) {
        return note1 * note2;
    }

    @Override
    public double division(double note1, double note2) {
        if (note2 == 0) {
            throw new ArithmeticException("Division par zéro impossible");
        }
        return note1 / note2;
    }
}