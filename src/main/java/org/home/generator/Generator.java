package org.home.generator;

import javax.annotation.Nonnull;
import java.util.List;

public interface Generator {
    @Nonnull
    List<String> generate();
}