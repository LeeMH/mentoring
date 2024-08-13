package me.mhlee.demo.common;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RiderTransportationTest {

    @Test
    public void _1킬로_이내_배달수단_테스트() {
        var result = RiderTransportation.getAvailableTransportation(1f);

        assertTrue(result.size() == RiderTransportation.values().length);
        assertTrue(result.contains(RiderTransportation.WALK));
    }

    @Test
    public void _1킬로_이상_거리에서는_WALK가_제외되어야_한다() {
        var result = RiderTransportation.getAvailableTransportation(1.1f);

        assertTrue(result.size() == RiderTransportation.values().length - 1);
        assertTrue(!result.contains(RiderTransportation.WALK));
    }


}