package org.home.generator.tree.nodes;

import org.home.generator.configuration.BinaryOperations;

import javax.annotation.Nonnull;

import static org.home.generator.tree.nodes.NodeTypes.BINARY_OPERATION;

public class BinaryOperationNode extends AbstractTreeNode<BinaryOperations> {
    public BinaryOperationNode(@Nonnull BinaryOperations attachedObject) {
        super(BINARY_OPERATION, attachedObject);
    }
}