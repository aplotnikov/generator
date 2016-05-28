package org.home.generator.simple;

import org.home.generator.FunctionGenerator;

import javax.annotation.Nonnull;
import java.util.LinkedList;
import java.util.List;

import static com.google.common.base.Preconditions.checkArgument;
import static java.util.Collections.unmodifiableList;

public class SimpleFunctionGenerator implements FunctionGenerator {
    private final List<String> parameters;
    private final List<String> binaryOperations;
    private final int generatedBinaryOperationsAmount;

    private final List<String> functions;

    public SimpleFunctionGenerator(@Nonnull List<String> parameters, @Nonnull List<String> binaryOperations, int generatedBinaryOperationsAmount) {
        checkArgument(!parameters.isEmpty(), "\'parameters\' argument has to be not empty");
        checkArgument(!binaryOperations.isEmpty(), "\'binaryOperations\' argument has to be not empty");
        checkArgument(generatedBinaryOperationsAmount > 0, "\'generatedBinaryOperationsAmount\' argument has to be not empty");

        this.parameters = parameters;
        this.binaryOperations = binaryOperations;
        this.generatedBinaryOperationsAmount = generatedBinaryOperationsAmount;

        this.functions = new LinkedList<>();
    }

    @Nonnull
    @Override
    public List<String> generate() {
        functions.clear();

        generateFunctions("", 0, generatedBinaryOperationsAmount);

        return unmodifiableList(functions);
    }

    private void generateFunctions(String originalFunction, int currentIteration, int availableIterations) {
        parameters.stream().forEach(
                parameter -> {
                    if (currentIteration < availableIterations) {
                        binaryOperations.stream().forEach(
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
}
