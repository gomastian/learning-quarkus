package com.sebastian.lil.lq;

import javax.inject.Inject;

import org.jboss.logging.Logger;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.annotations.QuarkusMain;


@QuarkusMain
public class CommandRunner implements QuarkusApplication {

    private final FizzBuzz fizzBuzz;
    private static final Logger LOG = Logger.getLogger(CommandRunner.class);

    @Inject
    public CommandRunner(FizzBuzz fizzBuzz){
        super();
        this.fizzBuzz = fizzBuzz;
    }

    @Override
    public int run(String... args) throws Exception {
        LOG.debug("Starting application");
        fizzBuzz.execute();
        LOG.debug("Ending application");
        return 0;
    }
}