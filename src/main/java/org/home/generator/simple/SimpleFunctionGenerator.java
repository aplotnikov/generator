package org.home.generator.simple;

import org.home.generator.FunctionGenerator;
import org.home.generator.configuration.FunctionGeneratorConfiguration;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class SimpleFunctionGenerator implements FunctionGenerator {
    private final FunctionGeneratorConfiguration functionGeneratorConfiguration;
    private final List<String> functions;

    public SimpleFunctionGenerator(@Nonnull FunctionGeneratorConfiguration functionGeneratorConfiguration) {
        if (!functionGeneratorConfiguration.isValid()) {
            throw new IllegalStateException("Function generator configuration has to be properly configured.");
        }

        this.functionGeneratorConfiguration = functionGeneratorConfiguration;
        this.functions = new LinkedList<>();
    }

    @Nonnull
    @Override
    public List<String> generate() {
        // TODO refactor collection
        functions.clear();

        generateFunctions("", 0, functionGeneratorConfiguration.getAvailableElementsAmount());

        return unmodifiableList(functions);
    }

    private void generateFunctions(String originalFunction, int currentIteration, int availableIterations) {
        functionGeneratorConfiguration.getParameters().stream().forEach(
                parameter -> {
                    if (currentIteration < availableIterations) {
                        functionGeneratorConfiguration.getBinaryOperations().stream().forEach(
                                sing -> generateFunctions(
                                        originalFunction + parameter.getName() + sing,
                                        currentIteration + 1,
                                        availableIterations
                                )
                        );
                    } else {
                        functions.add(originalFunction + parameter.getName());
                    }
                });
    }
}
