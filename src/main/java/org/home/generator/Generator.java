package org.home.generator;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Collections.unmodifiableList;

public class Generator {
    private final List<String> parameters;
    private final List<String> sings;
    private final int generatedSingsAmount;

    private final List<String> functions;

    public Generator(@Nonnull List<String> parameters, @Nonnull List<String> sings, int generatedSingsAmount) {
        checkArgument(!parameters.isEmpty(), "\'parameters\' argument has to be not empty");
        checkArgument(!sings.isEmpty(), "\'signs\' argument has to be not empty");
        checkArgument(generatedSingsAmount > 0, "\'generatedSignsAmount\' argument has to be not empty");

        this.parameters = parameters;
        this.sings = sings;
        this.generatedSingsAmount = generatedSingsAmount;

        this.functions = new LinkedList<>();
    }

    public void generate() {
        generateFunctions("", 0, generatedSingsAmount);
    }

    private void generateFunctions(String originalFunction, int currentIteration, int availableIterations) {
        //  TODO Need to refactor this code
        parameters.stream()
                  .forEach(
                          parameter -> {
                              if (currentIteration < availableIterations) {
                                  sings.stream().forEach(
                                          sing -> generateFunctions(
                                                  originalFunction + parameter + sing,
                                                  currentIteration + 1,
                                                  availableIterations
                                          )
                                  );
                              } else {
                                  functions.add(originalFunction + parameter);
                              }
                          });
    }

    @Nonnull
    public List<String> functions() {
        return unmodifiableList(functions);
    }
}
