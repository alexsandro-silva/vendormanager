package br.com.arsilva.vendormanager.javaapi;

import br.com.arsilva.vendormanager.javaapi.utilities.Utilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

public class UtilitiesTest {

    @Test
    public void validaMenorIdade() {
        Date data = new Date(2000, Calendar.JANUARY, 1);
        Assertions.assertTrue(Utilities.isMenorIdade(data));
    }
}
