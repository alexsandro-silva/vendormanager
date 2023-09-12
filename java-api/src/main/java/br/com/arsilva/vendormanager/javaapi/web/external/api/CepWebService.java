package br.com.arsilva.vendormanager.javaapi.web.external.api;

import br.com.arsilva.vendormanager.javaapi.web.external.api.dto.AddressDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CepWebService {

    private static final String URI = "https://viacep.com.br/ws/%S/json/";
    private static volatile CepWebService webServiceInstance;

    private CepWebService() {}

    public static CepWebService getService() {
        CepWebService result = webServiceInstance;
        if (result != null) {
            return result;
        }

        synchronized (CepWebService.class) {
            if (webServiceInstance == null) {
                webServiceInstance = new CepWebService();
            }

            return webServiceInstance;
        }
    }

    public AddressDto validaCep(String cep) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(java.net.URI.create(String.format(this.URI, cep)))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        AddressDto addressDto = null;

        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper mapper = new ObjectMapper();
            addressDto = mapper.readValue(response.body(), AddressDto.class);
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }

        return addressDto;
    }
}
