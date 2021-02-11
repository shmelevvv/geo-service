package ru.netology.i18n;

import org.junit.Test;
import ru.netology.entity.Country;

import static org.junit.Assert.assertEquals;

public class TestLocalizationServiceImpl {
    @Test
    public void test_locate_message() {
        String expected = "Добро пожаловать";
        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String result = localizationService.locale(Country.RUSSIA);

        assertEquals(expected, result);
    }
}
