package com.olehpodolin.repositories.reactive;

import com.olehpodolin.domain.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@DataMongoTest
public class RecipeReactiveRepositoryIT {

    @Autowired
    RecipeReactiveRepository recipeReactiveRepository;

    @Before
    public void setUp() throws Exception {
        recipeReactiveRepository.deleteAll().block();
    }

    @Test
    public void testSaveRecipe() throws Exception {
        //given
        Recipe testRecipe = new Recipe();
        testRecipe.setDescription("Test");

        //when
        recipeReactiveRepository.save(testRecipe).block();

        //then
        assertEquals(Long.valueOf(1L), recipeReactiveRepository.count().block());
    }
}