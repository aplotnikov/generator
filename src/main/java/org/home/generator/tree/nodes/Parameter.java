package org.home.generator.tree.nodes;

import javax.annotation.Nonnull;
import java.math.BigDecimal;
import java.util.Objects;

public class Parameter {
    private final String name;
    private final BigDecimal value;

    public Parameter(@Nonnull String name, @Nonnull BigDecimal value) {
        this.name = name;
        this.value = value;
    }

    @Nonnull
    public String getName() {
        return name;
    }

    @Nonnull
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public boolean equals(Object anotherObject) {
        if (this == anotherObject) {
            return true;
        }

        if (anotherObject == null || getClass() != anotherObject.getClass()) {
            return false;
        }

        Parameter anotherParameter = (Parameter) anotherObject;
        return Objects.equals(name, anotherParameter.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Parameter[" +
                "name='" + name + '\'' +
                ", value=" + value +
                ']';
    }
}
