package org.home.generator.function;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.joining;

public class FunctionNode {
    private final List<FunctionNode> children;
    private final NodeTypes nodeType;

    public FunctionNode(@Nonnull NodeTypes nodeType) {
        this.nodeType = nodeType;
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

    @Override
    public String toString() {
        return "FunctionNode[" + "type = " + nodeType.name() + ", " +
                "children = [" + children.stream()
                                         .map(FunctionNode::toString)
                                         .collect(joining(", ")) +
                "]]";
    }
}