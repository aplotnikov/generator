package org.home.generator;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;

public class Generator {
    private final List<String> parameters;
    private final List<String> sings;
    private final List<String> functions;

    public Generator(@Nonnull List<String> parameters, @Nonnull List<String> sings) {
        checkArgument(!parameters.isEmpty(), "\'parameters\' argument has to be not empty");
        checkArgument(!sings.isEmpty(), "\'signs\' argument has to be not empty");

        this.parameters = parameters;
        this.sings = sings;

        this.functions = new LinkedList<>();
    }

    public void generate() {
        generateFunctions("", 0, sings.size());
    }

    private void generateFunctions(String originalFunction, int currentIteration, int availableIteration) {
        //  TODO Need to refactor this code
        parameters.stream()
                  .forEach(
                          parameter ->
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
                                       }));
    }

    @Nonnull
    public List<String> functions() {
        return functions;
    }
}
