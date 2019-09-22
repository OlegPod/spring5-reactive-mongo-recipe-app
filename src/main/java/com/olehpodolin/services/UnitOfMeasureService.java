package com.olehpodolin.services;

import com.olehpodolin.commands.UnitOfMeasureCommand;
import reactor.core.publisher.Flux;

public interface UnitOfMeasureService {

    Flux<UnitOfMeasureCommand> listAllUoms();
}
