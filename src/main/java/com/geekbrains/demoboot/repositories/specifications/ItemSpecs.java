package com.geekbrains.demoboot.repositories.specifications;

import com.geekbrains.demoboot.entities.Item;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.data.jpa.domain.Specification;
import org.thymeleaf.util.StringUtils;

import java.util.List;

public class ItemSpecs {

    // список из разных id
    public static Specification<Item> idFilter (List<Integer> ids){
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> {
            if (ids.isEmpty()) {
                return criteriaBuilder.conjunction();
            }
            Predicate[] predicates = new Predicate[ids.size()];
            for (int i = 0; i < ids.size(); i++) {
                predicates[i] = criteriaBuilder.equal(root.get("id"), ids.get(i));
            }
            return criteriaBuilder.or(predicates);
        };
    }
    public static Specification<Item> titleContains(String word){
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> {
            if (StringUtils.isEmpty(word)) {
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.like(root.get("title"), "%" + word + "%");
        };
    }

    public static Specification<Item> priceGreaterThanOrEq (Integer value) {
        return (Specification<Item>) (root, criteriaQuery, criteriaBuilder) -> {
            if(value == null){
                return criteriaBuilder.conjunction();
            }
            return criteriaBuilder.greaterThanOrEqualTo(root.get("price"), value);
        };
    }

}
