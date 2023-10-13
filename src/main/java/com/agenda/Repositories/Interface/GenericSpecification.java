package com.agenda.Repositories.Interface;

import com.agenda.enums.OperadoresSql;
import jakarta.persistence.criteria.*;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecification<T> implements Specification<T> {
    private final String campo;
    private final OperadoresSql operador;
    private final String valor;

    private boolean showDeletado = false;
    private List<String> valores;

    public GenericSpecification(String campo, OperadoresSql operador, String valor, boolean showDeletado) {
        this.campo = campo;
        this.operador = operador;
        this.valor = valor;
        this.showDeletado = showDeletado;
    }

    public GenericSpecification(String campo, String valor, boolean showDeletado) {
        this.campo = campo;
        this.operador = OperadoresSql.IGUAL;
        this.valor = valor;
        this.showDeletado = showDeletado;
    }

    public GenericSpecification(String campo, List<String> valores, boolean showDeletado) {
        this.campo = campo;
        this.operador = OperadoresSql.IN;
        this.valores = valores;
        this.valor = null;
        this.showDeletado = showDeletado;
    }

    public GenericSpecification(boolean showDeletado) {
        this.campo = null;
        this.operador = OperadoresSql.IGUAL;
        this.valor = null;
        this.showDeletado = showDeletado;
    }


    @Override
    public Predicate toPredicate(
            @NotNull Root<T> root,
            @NotNull CriteriaQuery<?> query,
            @NotNull CriteriaBuilder criteriaBuilder
    ) {
        List<Predicate> predicates = new ArrayList<>();

        if (!this.showDeletado) {
            predicates.add(criteriaBuilder.equal(root.get("ativo"), !this.showDeletado));
        }

        if (this.campo == null || this.valor == null || this.operador == null) {
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }

        Expression<String> campoExpression = obterCampoExpressaoModel(root);

        if (campoExpression == null) {
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }

        montarPredicados(criteriaBuilder, predicates, campoExpression);

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

    private void montarPredicados(@NotNull CriteriaBuilder criteriaBuilder, List<Predicate> predicates, Expression<String> campoExpression) {
        switch (operador) {
            case IN:
                predicates.add(campoExpression.in(valores));
                break;
            case LIKE:
                predicates.add(criteriaBuilder.like(campoExpression, "%" + valor + "%"));
                break;
            case NOT_LIKE:
                predicates.add(criteriaBuilder.notLike(campoExpression, "%" + valor + "%"));
                break;
            case IGUAL:
                predicates.add(criteriaBuilder.equal(campoExpression, valor));
                break;
            case DIFERENTE:
                predicates.add(criteriaBuilder.notEqual(campoExpression, valor));
                break;
            case MAIOR:
                predicates.add(criteriaBuilder.greaterThan(campoExpression, valor));
                break;
            case MAIOR_IGUAL:
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(campoExpression, valor));
                break;
            case MENOR:
                predicates.add(criteriaBuilder.lessThan(campoExpression, valor));
                break;
            case MENOR_IGUAL:
                predicates.add(criteriaBuilder.lessThanOrEqualTo(campoExpression, valor));
                break;
            default:
                throw new IllegalArgumentException("Operador desconhecido: " + operador);
        }
    }

    private Expression<String> obterCampoExpressaoModel(@NotNull Root<T> root) {
        Expression<String> campoExpression = null;

        if (campo.contains(".")) {
            String[] campos = campo.split("\\.");
            Join<Object, Object> join = root.join(campos[0]);
            campoExpression = join.get(campos[1]);
        } else {
            campoExpression = root.get(campo);
        }
        return campoExpression;
    }
}
