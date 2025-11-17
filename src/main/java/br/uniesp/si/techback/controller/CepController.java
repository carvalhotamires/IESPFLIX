package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.ViaCepResponse;
import br.uniesp.si.techback.service.ViaCepService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cep")
@RequiredArgsConstructor
@SecurityRequirement(name = "basicAuth")
public class CepController {

    private final ViaCepService viaCepService;

    /**
     * Endpoint para consulta de CEP
     * @param cep Número do CEP a ser consultado (pode conter ou não formatação)
     * @return Dados do endereço correspondente ao CEP
     */
    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepResponse> consultarCep(@PathVariable String cep) {
        return ResponseEntity.ok(viaCepService.buscarEnderecoPorCep(cep));
    }
}