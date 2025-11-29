package com.great.project.service;

import com.great.project.domain.model.Product;
import com.great.project.domain.model.Section;
import com.great.project.domain.repository.SectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashSet;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class SectionService {

    private final SectionRepository sectionRepository;

    @Autowired
    public SectionService(final SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Transactional
    public void createGoodiesSectionAndProducts() {
        // idempotent - skip if already initialized
        if (sectionRepository.count() > 0) {
            return;
        }

        final Section section = new Section();
        section.setName("Goodies");

        final LinkedHashSet<Product> products =
                IntStream.rangeClosed(1, 10)
                         .boxed()
                         .map(id -> new Product("The product with the ID " + id, section))
                         .collect(Collectors.toCollection(LinkedHashSet::new));
        section.setProducts(products);

        sectionRepository.save(section);
    }
}
