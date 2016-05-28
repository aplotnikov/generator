package org.home.generator.function.nodes;

import javax.annotation.Nonnull;

import static org.home.generator.function.nodes.NodeTypes.PARAMETER;

public class ParameterNode extends AbstractTreeNode<Parameter> {
    public ParameterNode(@Nonnull Parameter parameter) {
        super(PARAMETER, parameter);
    }
}