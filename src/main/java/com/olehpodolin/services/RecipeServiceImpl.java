package com.olehpodolin.services;

import com.olehpodolin.commands.RecipeCommand;
import com.olehpodolin.converters.RecipeCommandToRecipe;
import com.olehpodolin.converters.RecipeToRecipeCommand;
import com.olehpodolin.domain.Recipe;
import com.olehpodolin.exceptions.NotFoundException;
import com.olehpodolin.repositories.reactive.RecipeReactiveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeReactiveRepository recipeReactiveRepository;
    private final RecipeCommandToRecipe recipeCommandToRecipe;
    private final RecipeToRecipeCommand recipeToRecipeCommand;

    public RecipeServiceImpl(RecipeReactiveRepository recipeReactiveRepository, RecipeCommandToRecipe recipeCommandToRecipe, RecipeToRecipeCommand recipeToRecipeCommand) {
        this.recipeReactiveRepository = recipeReactiveRepository;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
    }

    @Override
    public Flux<Recipe> getRecipes() {
        log.debug("I'm in the service");

        return recipeReactiveRepository.findAll();
    }

    @Override
    public Mono<Recipe> findById(String id) {

        Recipe recipe = recipeReactiveRepository.findById(id).block();

        if (recipe == null) {
            throw new NotFoundException("Recipe Not Found. For ID value: " + id );
        }

        return Mono.just(recipe);
    }

    @Override
    public Mono<RecipeCommand> findCommandById(String id) {

        RecipeCommand recipeCommand = recipeToRecipeCommand.convert(findById(id).block());

        //enhance command object with id value
        if(recipeCommand.getIngredients() != null && recipeCommand.getIngredients().size() > 0){
            recipeCommand.getIngredients().forEach(rc -> {
                rc.setRecipeId(recipeCommand.getId());
            });
        }

        return Mono.just(recipeCommand);
    }

    @Override
    public Mono<RecipeCommand> saveRecipeCommand(RecipeCommand command) {
        return  recipeReactiveRepository.save(recipeCommandToRecipe.convert(command))
                .map(recipeToRecipeCommand::convert);
    }

    @Override
    public void deleteById(String idToDelete) {
        recipeReactiveRepository.deleteById(idToDelete).block();
    }
}
