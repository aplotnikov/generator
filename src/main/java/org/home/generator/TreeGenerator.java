package org.home.generator;

import org.home.generator.function.nodes.AbstractTreeNode;
import org.home.generator.function.nodes.BinaryOperationNode;
import org.home.generator.function.nodes.BinaryOperations;
import org.home.generator.function.nodes.Function;
import org.home.generator.function.nodes.FunctionNode;
import org.home.generator.function.nodes.Parameter;
import org.home.generator.function.nodes.ParameterNode;

import javax.annotation.Nonnull;
import java.util.Set;

public class TreeGenerator {
    private final Set<Parameter> parameters;
    private final Set<BinaryOperations> binaryOperations;
    private final int availableNodeAmount;

    public TreeGenerator(@Nonnull Set<Parameter> parameters, @Nonnull Set<BinaryOperations> binaryOperations, int availableNodeAmount) {
        this.parameters = parameters;
        this.binaryOperations = binaryOperations;
        this.availableNodeAmount = availableNodeAmount;
    }

    @Nonnull
    public FunctionNode generate() {
        FunctionNode rootNode = new FunctionNode(new Function());

        generateTree(rootNode, 0, availableNodeAmount);

        return rootNode;
    }

    private void generateTree(@Nonnull AbstractTreeNode rootNode, int currentNodeIndex, int availableNodeAmount) {
        parameters.forEach(parameter -> {
            ParameterNode parameterNode = new ParameterNode(parameter);
            rootNode.addChild(parameterNode);

            if (currentNodeIndex + 1 < availableNodeAmount) {
                binaryOperations.forEach(binaryOperation -> {
                    BinaryOperationNode signNode = new BinaryOperationNode(binaryOperation);
                    parameterNode.addChild(signNode);

                    generateTree(signNode, currentNodeIndex + 2, availableNodeAmount);
                });
            }
        });
    }
}