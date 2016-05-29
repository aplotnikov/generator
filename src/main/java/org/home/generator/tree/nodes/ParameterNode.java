package org.home.generator.tree.nodes;

import org.home.generator.configuration.Parameter;

import javax.annotation.Nonnull;

import static org.home.generator.tree.nodes.NodeTypes.PARAMETER;

public class ParameterNode extends AbstractTreeNode<Parameter> {
    public ParameterNode(@Nonnull Parameter parameter) {
        super(PARAMETER, parameter);
    }
}