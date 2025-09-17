package org.example;

public class Car {

    public boolean analyzeCarByParams(Integer treadThickness, Integer fuelUsage, Integer carMillage) {
        if (treadThickness == null || fuelUsage == null || carMillage == null) {
            throw new IllegalArgumentException("Car details cannot be null");
        }

        boolean isMillageCorrect = isCorrect(carMillage, ParamValues.CAR_MILLAGE);
        boolean isFuelUsageCorrect = isCorrect(fuelUsage, ParamValues.FUEL_USAGE);
        boolean isTreadThicknessCorrect = isCorrect(treadThickness, ParamValues.TREAD_THICKNESS);

        return isMillageCorrect && isFuelUsageCorrect && isTreadThicknessCorrect;
    }

    private boolean isCorrect(Integer value, ParamValues validValues) {
        if (value < validValues.getMinValue()) {
            throw new IllegalArgumentException("Incorrect value: below minimum");
        }

        Integer maxValue = validValues.getMaxValue();
        if (maxValue != null && value > maxValue) {
            return false;
        }

        return true; // If the value is within the valid range, return true
    }
}
