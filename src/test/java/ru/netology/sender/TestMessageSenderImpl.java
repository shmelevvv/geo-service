package ru.netology.sender;

import org.junit.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;

public class TestMessageSenderImpl {
    @Test
    public void test_send_message_rus_ip() {
        String ip = "172.16.10.18";
        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("Moskow", Country.RUSSIA, "Lenina", 5));
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        MessageSender messageSender = new MessageSenderImpl(geoService, new LocalizationServiceImpl());
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String message = messageSender.send(headers);
        String expected = localizationService.locale(Country.RUSSIA);

        assertEquals(expected, message);
    }

    @Test
    public void test_send_message_usa_ip() {
        String ip = "96.18.11.12";
        GeoService geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("New York", Country.USA, "Lenina", 5));
        LocalizationService localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        MessageSender messageSender = new MessageSenderImpl(geoService, new LocalizationServiceImpl());
        Map<String, String> headers = new HashMap<String, String>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);

        String message = messageSender.send(headers);
        String expected = localizationService.locale(Country.USA);

        assertEquals(expected, message);
    }
}
