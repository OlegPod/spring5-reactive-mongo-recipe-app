package com.olehpodolin.repositories.reactive;

import com.olehpodolin.domain.Category;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface CategoryReactiveRepository extends ReactiveMongoRepository<Category, String> {
}
