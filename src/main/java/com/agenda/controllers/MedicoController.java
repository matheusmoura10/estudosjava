package com.agenda.controllers;

import com.agenda.dto.medicos.MedicoOutputDto;
import com.agenda.dto.medicos.MedicosOutputListagem;
import com.agenda.dto.shared.GeneralResponse;
import com.agenda.Services.MedicoService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.agenda.dto.medicos.MedicoInputDto;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    private MedicoService medicoService;

    @GetMapping
    public ResponseEntity<Page<MedicosOutputListagem>> listarPaginado(
            @PageableDefault(size = 10, page = 0, sort = {"nome"}) Pageable paginacao,
            @RequestParam(required = false) String findBy,
            @RequestParam(required = false) String findByValue
    ) {
        return ResponseEntity.ok(medicoService.listarPaginadoAtivos(paginacao, findBy, findByValue));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MedicoOutputDto> salvar(@RequestBody @Valid MedicoInputDto medico, UriComponentsBuilder uriBuilder) {
        MedicoOutputDto response = medicoService.store(medico);


        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(response.id()).toUri();

        return ResponseEntity.created(uri).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicoOutputDto> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(medicoService.show(id));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MedicoOutputDto> atualizar(@PathVariable Long id, @RequestBody @Valid MedicoInputDto medico) {
        return ResponseEntity.ok(medicoService.update(id, medico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<GeneralResponse> deletar(@PathVariable Long id) {

        medicoService.delete(id);

        return ResponseEntity.ok(
                new GeneralResponse(
                        HttpStatus.OK,
                        "Deletado com sucesso"
                        )
        );
    }
}
