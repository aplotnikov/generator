package org.home.generator.function.nodes;

import javax.annotation.Nonnull;

import static org.home.generator.function.nodes.NodeTypes.FUNCTION;

public class FunctionNode extends AbstractTreeNode<Function> {
    public FunctionNode(@Nonnull Function function) {
        super(FUNCTION, function);
    }
}