package org.home.generator.tree

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.home.generator.configuration.FunctionGeneratorConfiguration
import org.home.generator.configuration.Parameter
import org.home.generator.tree.nodes.FunctionNode
import spock.lang.Specification

import static java.math.BigDecimal.ONE
import static java.math.BigDecimal.TEN
import static org.home.generator.configuration.BinaryOperations.MINUS
import static org.home.generator.configuration.BinaryOperations.PLUS

class TreeGeneratorTest extends Specification {
    void assertTree(FunctionNode functionNode, String pathToExpectedJsonViewFile) {
        def expectedJsonTree = new JsonSlurper().parse(
                getClass().getResource(pathToExpectedJsonViewFile)
        )

        def actualJsonTree = new JsonSlurper().parseText(
                JsonOutput.toJson(functionNode)
        )

        assert expectedJsonTree == actualJsonTree
    }

    def "The generator should generate tree from configuration: x, y parameters, '+' sign, available amount of node 3"() {
        given:
        def functionGeneratorConfiguration = Mock(FunctionGeneratorConfiguration.class)
        functionGeneratorConfiguration.isValid() >> true
        functionGeneratorConfiguration.getParameters() >> [new Parameter('x', ONE), new Parameter('y', TEN)]
        functionGeneratorConfiguration.getBinaryOperations() >> [PLUS]
        functionGeneratorConfiguration.getAvailableElementsAmount() >> 3

        and:
        def generator = new TreeGenerator(functionGeneratorConfiguration)

        when:
        def functionTree = generator.generate()

        then:
        assertTree(functionTree, '/tree/expected_tree_two_parameters_one_binary_operation.json')
    }

    def "The generator should generate tree from configuration: x, y parameters and '+', '-' signs"() {
        given:
        def functionGeneratorConfiguration = Mock(FunctionGeneratorConfiguration.class)
        functionGeneratorConfiguration.isValid() >> true
        functionGeneratorConfiguration.getParameters() >> [new Parameter('x', ONE), new Parameter('y', TEN)]
        functionGeneratorConfiguration.getBinaryOperations() >> [PLUS, MINUS]
        functionGeneratorConfiguration.getAvailableElementsAmount() >> 3

        and:
        def generator = new TreeGenerator(functionGeneratorConfiguration)

        when:
        def functionTree = generator.generate()

        then:
        assertTree(functionTree, '/tree/expected_tree_two_parameters_two_binary_operations.json')
    }

    def "The generator should generate tree from configuration: x parameter and '+', '-' signs"() {
        given:
        def functionGeneratorConfiguration = Mock(FunctionGeneratorConfiguration.class)
        functionGeneratorConfiguration.isValid() >> true
        functionGeneratorConfiguration.getParameters() >> [new Parameter('x', ONE)]
        functionGeneratorConfiguration.getBinaryOperations() >> [PLUS, MINUS]
        functionGeneratorConfiguration.getAvailableElementsAmount() >> 3

        and:
        def generator = new TreeGenerator(functionGeneratorConfiguration)

        when:
        def functionTree = generator.generate()

        then:
        assertTree(functionTree, '/tree/expected_tree_one_parameter_two_binary_operations.json')
    }

    def "IllegalStateException should be thrown when generator configuration is configured improperly"() {
        given:
        def functionGeneratorConfiguration = Mock(FunctionGeneratorConfiguration.class)
        functionGeneratorConfiguration.isValid() >> false

        when:
        new TreeGenerator(functionGeneratorConfiguration)

        then:
        def exception = thrown(IllegalStateException.class)

        and:
        assert exception.message == 'Function generator configuration has to be properly configured.'
    }
}
