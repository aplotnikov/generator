package org.home.generator.function.nodes;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;
import static java.util.stream.Collectors.joining;

public abstract class AbstractTreeNode<T> {
    private final List<AbstractTreeNode<?>> children;
    private final NodeTypes nodeType;
    private final T attachedObject;

    AbstractTreeNode(@Nonnull NodeTypes nodeType, @Nonnull T attachedObject) {
        this.nodeType = nodeType;
        this.attachedObject = attachedObject;
        this.children = new ArrayList<>();
    }

    @Nonnull
    public List<AbstractTreeNode> getChildren() {
        return unmodifiableList(children);
    }

    public void addChild(@Nonnull AbstractTreeNode child) {
        children.add(child);
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    @Nonnull
    public NodeTypes getNodeType() {
        return nodeType;
    }

    @Nonnull
    public T getAttachedObject() {
        return attachedObject;
    }

    @Override
    public String toString() {
        return "TreeNode[" + "type = " + nodeType.name() + ", " +
                "attachedObject = " + attachedObject.toString() + ", " +
                "children = [" + children.stream()
                                         .map(AbstractTreeNode::toString)
                                         .collect(joining(", ")) +
                "]]";
    }
}