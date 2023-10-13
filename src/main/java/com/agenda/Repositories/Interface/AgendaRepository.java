package com.agenda.Repositories.Interface;

import com.agenda.dto.agendas.AgendaPaginadaOutputDto;
import com.agenda.enums.OperadoresSql;
import com.agenda.models.AgendaModel;
import com.agenda.models.MedicoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface AgendaRepository extends CustomRepository<AgendaModel, Long>, JpaSpecificationExecutor<AgendaModel> {
    default Page<AgendaPaginadaOutputDto> findAllByFieldValueLikePaginate(String findBy, String findByValue, Pageable paginacao) {
        List<Specification<AgendaModel>> specifications = List.of(
                new GenericSpecification<>(findBy, OperadoresSql.LIKE, findByValue, true)
        );

        Specification<AgendaModel> specification = specifications.stream().reduce(Specification::and).orElseThrow();

        return findAll(specification, paginacao).map(AgendaPaginadaOutputDto::criar);
    }
}
