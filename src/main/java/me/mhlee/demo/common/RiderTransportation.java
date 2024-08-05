package me.mhlee.demo.common;

import java.util.ArrayList;
import java.util.List;

public enum RiderTransportation {
    WALK,
    BICYCLE,
    MOTORBIKE,
    CAR;

    public static List<RiderTransportation> getAvailableTransportation(float distanceKm) {
        List<RiderTransportation> result = new ArrayList<>();
        if (distanceKm <= 1) {
            result.add(RiderTransportation.WALK);
            result.add(RiderTransportation.BICYCLE);
            result.add(RiderTransportation.MOTORBIKE);
            result.add(RiderTransportation.CAR);
        } else if (distanceKm <= 2.5) {
            result.add(RiderTransportation.BICYCLE);
            result.add(RiderTransportation.MOTORBIKE);
            result.add(RiderTransportation.CAR);
        } else if (distanceKm < 4) {
            result.add(RiderTransportation.MOTORBIKE);
            result.add(RiderTransportation.CAR);
        } else if (distanceKm <= 5) {
            result.add(RiderTransportation.CAR);
        }

        return result;
    }
}
