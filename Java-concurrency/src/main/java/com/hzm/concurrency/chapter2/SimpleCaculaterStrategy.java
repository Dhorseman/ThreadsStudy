package com.hzm.concurrency.chapter2;

public class SimpleCaculaterStrategy implements CaculaterStrategy {

    private final static double SALARY_RATE=0.1;
    private final static double BONUS_RATE=0.15;

    @Override
    public double caculater(double salary, double bonus) {
        return salary*SALARY_RATE+bonus*BONUS_RATE;
    }
}
