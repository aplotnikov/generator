package org.home.generator.configuration;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

import static java.util.Collections.unmodifiableSet;
import static java.util.stream.Collectors.joining;

public class FunctionGeneratorConfiguration {
    private final Set<Parameter> parameters;
    private final Set<BinaryOperations> binaryOperations;
    private int availableElementsAmount;

    public FunctionGeneratorConfiguration() {
        parameters = new LinkedHashSet<>();
        binaryOperations = new LinkedHashSet<>();
        availableElementsAmount = 3;
    }

    public boolean isValid() {
        return !parameters.isEmpty() && !binaryOperations.isEmpty() && availableElementsAmount > 0;
    }

    public void addParameter(@Nonnull String name, @Nonnull BigDecimal value) {
        parameters.add(
                new Parameter(name, value)
        );
    }

    @Nonnull
    public Set<Parameter> getParameters() {
        return unmodifiableSet(parameters);
    }

    public void addBinaryOperation(@Nonnull BinaryOperations binaryOperation) {
        binaryOperations.add(binaryOperation);
    }

    @Nonnull
    public Set<BinaryOperations> getBinaryOperations() {
        return unmodifiableSet(binaryOperations);
    }

    public int getAvailableElementsAmount() {
        return availableElementsAmount;
    }

    public void setAvailableElementsAmount(int availableElementsAmount) {
        this.availableElementsAmount = availableElementsAmount;
    }

    @Override
    public String toString() {
        return "Configuration[" +
                "parameters=[" + parameters.stream()
                                           .map(Parameter::toString)
                                           .collect(joining(", ")) +
                "], " +
                "binary operations=[" + binaryOperations.stream()
                                                        .map(BinaryOperations::toString)
                                                        .collect(joining(", ")) +
                "], " +
                "available elements=" + availableElementsAmount +
                ']';
    }
}
