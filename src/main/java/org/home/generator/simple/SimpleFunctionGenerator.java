package org.home.generator.simple;

import org.home.generator.FunctionGenerator;
import org.home.generator.configuration.FunctionGeneratorConfiguration;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

public class SimpleFunctionGenerator implements FunctionGenerator {
    private final FunctionGeneratorConfiguration functionGeneratorConfiguration;

    public SimpleFunctionGenerator(@Nonnull FunctionGeneratorConfiguration functionGeneratorConfiguration) {
        if (!functionGeneratorConfiguration.isValid()) {
            throw new IllegalStateException("Function generator configuration has to be properly configured.");
        }

        this.functionGeneratorConfiguration = functionGeneratorConfiguration;
    }

    @Nonnull
    @Override
    public List<String> generate() {
        List<String> functions = new ArrayList<>();

        generateFunctions(functions, "", 0, functionGeneratorConfiguration.getAvailableElementsAmount());

        return functions;
    }

    private void generateFunctions(@Nonnull List<String> functions, @Nonnull String originalFunction, int currentIteration, int availableIterations) {
        functionGeneratorConfiguration.getParameters().stream().forEach(
                parameter -> {
                    if (currentIteration < availableIterations) {
                        functionGeneratorConfiguration.getBinaryOperations().stream().forEach(
                                sing -> generateFunctions(
                                        functions,
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
