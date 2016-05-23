package org.home.generator;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

public class Generator {
    private final List<String> parameters;
    private final List<String> sings;
    private final List<String> functions;

    public Generator(@Nonnull List<String> parameters, @Nonnull List<String> sings) {
        this.functions = new LinkedList<>();
        this.parameters = parameters;
        this.sings = sings;
    }

    public void generate() {
        generateFunctions("", 0, sings.size());
    }

    private void generateFunctions(String originalFunction, int currentIteration, int availableIteration) {
        //  TODO Need to refactor this code
        parameters.stream()
                  .forEach(parameter -> {
                      sings.stream()
                           .forEach(sing -> {
                               if (currentIteration < availableIteration) {
                                   generateFunctions(
                                           originalFunction + parameter + sing,
                                           currentIteration + 1,
                                           availableIteration
                                   );
                               } else {
                                   functions.add(originalFunction + parameter);
                               }
                           });
                  });
    }

    @Nonnull
    public List<String> functions() {
        return functions;
    }
}
