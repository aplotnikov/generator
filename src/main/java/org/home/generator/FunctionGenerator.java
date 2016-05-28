package org.home.generator;

import javax.annotation.Nonnull;
import java.util.List;

public interface FunctionGenerator {
    @Nonnull
    List<String> generate();
}