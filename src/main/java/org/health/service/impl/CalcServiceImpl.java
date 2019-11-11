package org.health.service.impl;

import org.health.service.CalcService;

public class CalcServiceImpl implements CalcService {
    private CalcService calcService;

    public CalcServiceImpl(CalcService calcService) {
        this.calcService = calcService;
    }

    @Override
    public double add(double val1, double val2) {
        return calcService.add(val1, val2);
    }

    @Override
    public double substract(double val1, double val2) {
        return calcService.substract(val1, val2);
    }

    @Override
    public double multipy(double val1, double val2) {
        return calcService.multipy(val1, val2);
    }

    @Override
    public double divide(double val1, double val2) {
        return calcService.divide(val1, val2);
    }

    public double double15() {
        return 15.0;
    }
}
