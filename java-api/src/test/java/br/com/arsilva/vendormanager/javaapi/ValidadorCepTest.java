package br.com.arsilva.vendormanager.javaapi;

import br.com.arsilva.vendormanager.javaapi.web.external.api.CepWebService;
import br.com.arsilva.vendormanager.javaapi.web.external.api.dto.AddressDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ValidadorCepTest {

    @Test
    public void validaCep() {
        String cep = "50670260";
        AddressDto addressDto = CepWebService.getService().getAddressFromCep(cep);
        Assertions.assertNotNull(addressDto);
    }
}
