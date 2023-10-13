package com.agenda.controllers;

import com.agenda.Services.AgendaService;
import com.agenda.Services.AgendaServiceBuilder;
import com.agenda.dto.agendas.AgendaInputDto;
import com.agenda.dto.agendas.AgendaOutputDto;
import com.agenda.dto.agendas.AgendaPaginadaOutputDto;
import com.agenda.dto.agendas.DiasDisponiveisDto;
import com.agenda.dto.shared.Response;
import com.agenda.enums.AgendaEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agendas")
public class AgendaController {

    @Autowired
    private AgendaServiceBuilder agendaServiceBuilder;

    @Autowired
    private AgendaService agendaService;

    @GetMapping
    public ResponseEntity<Response> index(
            @PageableDefault(size = 10, page = 0, sort = {"medico.nome"}) Pageable paginacao,
            @RequestParam(required = false) String findBy,
            @RequestParam(required = false) String findByValue
    ) {

        return ResponseEntity.ok(
                new Response(AgendaEnum.AGENDA_OBITIDA_COM_SUCESSO.getMensagem(), agendaService.index(paginacao, findBy, findByValue))
        );
    }

    @PostMapping
    public ResponseEntity<Response> salvar(@RequestBody @Valid AgendaInputDto agenda) {
        AgendaOutputDto agendaOutputDto = agendaService.store(agenda);

        Response response = new Response(AgendaEnum.AGENDA_SALVA_COM_SUCESSO.getMensagem(), agendaOutputDto);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response> show(@PathVariable Long id) {

        AgendaOutputDto agendaOutputDto = agendaService.show(id);
        Response response = new Response(AgendaEnum.AGENDA_OBITIDA_COM_SUCESSO.getMensagem(), agendaOutputDto);

        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response> update(@PathVariable Long id, @RequestBody @Valid AgendaInputDto agenda) {
        AgendaOutputDto agendaOutputDto = agendaService.update(id, agenda);

        Response response = new Response(AgendaEnum.AGENDA_ATUALIZADA_COM_SUCESSO.getMensagem(), agendaOutputDto);

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response> delete(@PathVariable Long id) {
        Boolean deletado = agendaService.delete(id);

        Response response = new Response(AgendaEnum.AGENDA_DELETADA_COM_SUCESSO.getMensagem(), deletado);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}/agenda")
    public ResponseEntity<Response> showAgenda(@PathVariable Long id) {

        DiasDisponiveisDto diasDisponiveisDto = agendaServiceBuilder.obterAgenda(id);

        return ResponseEntity.ok(
                new Response(AgendaEnum.AGENDA_OBITIDA_COM_SUCESSO.getMensagem(), diasDisponiveisDto)
        );
    }

}
