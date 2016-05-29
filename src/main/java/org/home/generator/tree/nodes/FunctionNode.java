package org.home.generator.tree.nodes;

import org.home.generator.configuration.Function;

import javax.annotation.Nonnull;

import static org.home.generator.tree.nodes.NodeTypes.FUNCTION;

public class FunctionNode extends AbstractTreeNode<Function> {
    public FunctionNode(@Nonnull Function function) {
        super(FUNCTION, function);
    }
}