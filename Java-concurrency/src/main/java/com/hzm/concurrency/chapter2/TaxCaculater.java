package com.hzm.concurrency.chapter2;

public class TaxCaculater {
    private final double salary;
    private final double bonus;
    private CaculaterStrategy caculaterStrategy;

    public void setCaculaterStrategy(CaculaterStrategy caculaterStrategy) {
        this.caculaterStrategy = caculaterStrategy;
    }

    public TaxCaculater(double salary, double bonus) {
        this.salary = salary;
        this.bonus = bonus;
    }

    public double getSalary() {
        return salary;
    }

    public double getBonus() {
        return bonus;
    }

    protected double calTax(){
        return caculaterStrategy.caculater(salary,bonus);
    }
    public double caculater(){
        return this.calTax();
    }
}
