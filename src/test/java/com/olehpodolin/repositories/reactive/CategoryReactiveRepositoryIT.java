package com.olehpodolin.repositories.reactive;

import com.olehpodolin.domain.Category;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class CategoryReactiveRepositoryIT {

    @Autowired
    CategoryReactiveRepository categoryReactiveRepository;

    @Before
    public void setUp() throws Exception {
        categoryReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveCategory() throws Exception {
        //given
        Category testCategory = new Category();
        testCategory.setDescription("Food to go");

        //when
        categoryReactiveRepository.save(testCategory).block();

        //then
        assertEquals(Long.valueOf(1L), categoryReactiveRepository.count().block());

    }

    @Test
    public void testFindByDescription() throws Exception{
        //given
        Category testCategory = new Category();
        testCategory.setDescription("Delicious something");

        //when
        categoryReactiveRepository.save(testCategory).block();

        Category fetchedCategory = categoryReactiveRepository.findByDescription(testCategory.getDescription()).block();

        //then
        assertNotNull(fetchedCategory.getId());
    }
}