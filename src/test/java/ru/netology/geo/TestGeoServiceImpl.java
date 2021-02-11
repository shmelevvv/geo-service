package ru.netology.geo;

import org.junit.Test;
import ru.netology.entity.Country;

import static org.junit.Assert.assertEquals;

public class TestGeoServiceImpl {
    @Test
    public void test_location_by_ip() {
        String ip = "172.16.10.18";
        GeoServiceImpl geoService = new GeoServiceImpl();

        Country expected = Country.RUSSIA;
        Country result = geoService.byIp(ip).getCountry();
        assertEquals(expected, result);
    }
}
