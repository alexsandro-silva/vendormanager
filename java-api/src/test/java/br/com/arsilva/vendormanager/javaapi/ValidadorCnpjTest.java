package br.com.arsilva.vendormanager.javaapi;

import br.com.arsilva.vendormanager.javaapi.utilities.Utilities;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidadorCnpjTest {

    @Test
    public void validaNumerosRepetidos() {
        String cnpjInvalido = "11111111111111";
        boolean resultado = Utilities.validarCnpj(cnpjInvalido);
        Assertions.assertFalse(resultado);
    }

    @Test
    public void validaTamanhoCnpj() {
        String mockCnpj = "90876567000112";
        boolean esperado = false;
        boolean resultado = Utilities.validarCnpj(mockCnpj);
        Assertions.assertEquals(esperado, resultado);
    }

    @Test
    public void validaCnpjValido() {
        String mockCnpj = "08334818000314";
        boolean esperado = true;
        boolean resultado = Utilities.validarCnpj(mockCnpj);
        Assertions.assertEquals(esperado, resultado);
    }
}
