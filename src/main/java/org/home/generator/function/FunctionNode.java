package org.home.generator.function;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class FunctionNode {
    private final List<FunctionNode> children;

    public FunctionNode() {
        this.children = new ArrayList<>();
    }

    @Nonnull
    public List<FunctionNode> getChildren() {
        return unmodifiableList(children);
    }

    public void addChild(@Nonnull FunctionNode child) {
        children.add(child);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }
}