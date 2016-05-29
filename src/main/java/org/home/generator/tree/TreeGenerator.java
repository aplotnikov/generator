package org.home.generator.tree;

import org.home.generator.configuration.Function;
import org.home.generator.configuration.FunctionGeneratorConfiguration;
import org.home.generator.tree.nodes.AbstractTreeNode;
import org.home.generator.tree.nodes.BinaryOperationNode;
import org.home.generator.tree.nodes.FunctionNode;
import org.home.generator.tree.nodes.ParameterNode;

import javax.annotation.Nonnull;

public class TreeGenerator {
    private final FunctionGeneratorConfiguration functionGeneratorConfiguration;

    public TreeGenerator(@Nonnull FunctionGeneratorConfiguration functionGeneratorConfiguration) {
        if (!functionGeneratorConfiguration.isValid()) {
            throw new IllegalStateException("Function generator configuration has to be properly configured.");
        }

        this.functionGeneratorConfiguration = functionGeneratorConfiguration;
    }

    @Nonnull
    public FunctionNode generate() {
        FunctionNode rootNode = new FunctionNode(new Function());

        generateTree(rootNode, 0, functionGeneratorConfiguration.getAvailableElementsAmount());

        return rootNode;
    }

    private void generateTree(@Nonnull AbstractTreeNode rootNode, int currentNodeIndex, int availableNodeAmount) {
        functionGeneratorConfiguration.getParameters().stream().forEach(parameter -> {
            ParameterNode parameterNode = new ParameterNode(parameter);
            rootNode.addChild(parameterNode);

            if (currentNodeIndex + 1 < availableNodeAmount) {
                functionGeneratorConfiguration.getBinaryOperations().stream().forEach(binaryOperation -> {
                    BinaryOperationNode signNode = new BinaryOperationNode(binaryOperation);
                    parameterNode.addChild(signNode);

                    generateTree(signNode, currentNodeIndex + 2, availableNodeAmount);
                });
            }
        });
    }
}
