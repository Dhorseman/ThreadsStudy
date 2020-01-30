package com.hzm.concurrency.chapter2;

/**
 * 通过TaxCaculater示例理解run()方法构造，通过在TaxCaculater中设置好各个属性，使得代码耦合性降低，只需要给各种参数赋值，减少代码改动
 */
public class TaxCaculaterMain {
    public static void main(String[] args) {
//        final TaxCaculater caculater = new TaxCaculater(10000d,2000d){
//            @Override
//            public double calTax() {
//                return getSalary()*0.1+getBonus()*0.15;
//            }
//        };
//        double tax = caculater.caculater();
//        System.out.println(tax);

        //传统写法
//        final TaxCaculater caculater = new TaxCaculater(10000d,2000d);
//        CaculaterStrategy strategy = new SimpleCaculaterStrategy();
//        caculater.setCaculaterStrategy(strategy);
//        System.out.println(caculater.caculater());

        //Java8写法
        TaxCaculater caculater = new TaxCaculater(10000d,2000d);
        caculater.setCaculaterStrategy((s,b)-> s * 0.1 + b * 0.15);

    }
}
