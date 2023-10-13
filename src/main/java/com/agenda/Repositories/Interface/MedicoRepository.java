package com.agenda.Repositories.Interface;

import com.agenda.enums.OperadoresSql;
import com.agenda.models.MedicoModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<MedicoModel, Long>, JpaSpecificationExecutor<MedicoModel> {

    default Page<MedicoModel> findAllByFieldValueLikePaginate(String campo, String valor, Pageable pageable, boolean showDeletado) {

        List<Specification<MedicoModel>> specifications = List.of(
                new GenericSpecification<>(campo, OperadoresSql.LIKE, valor, showDeletado)
        );

        Specification<MedicoModel> specification = specifications.stream().reduce(Specification::and).orElseThrow();

        return findAll(specification, pageable);
    }

    Optional<MedicoModel> findByIdAndAtivoTrue(Long id);
}
