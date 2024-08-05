package me.mhlee.demo.common;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public enum RiderTransportation {
    WALK(1f),
    BICYCLE(2.5f),
    MOTORBIKE(4f),
    CAR(5f);

    private final float maxDistanceKm;

    RiderTransportation(float maxDistanceKm) {
        this.maxDistanceKm = maxDistanceKm;
    }

    public static List<RiderTransportation> getAvailableTransportation(float distanceKm) {
        return Arrays.stream(RiderTransportation.values())
                .filter(it -> it.maxDistanceKm >= distanceKm)
                .collect(Collectors.toList());
    }
}
