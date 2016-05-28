package org.home.generator.tree

import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import org.home.generator.tree.nodes.FunctionNode
import org.home.generator.tree.nodes.Parameter
import spock.lang.Specification

import static java.math.BigDecimal.ONE
import static java.math.BigDecimal.TEN
import static org.home.generator.tree.nodes.BinaryOperations.MINUS
import static org.home.generator.tree.nodes.BinaryOperations.PLUS

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
        def generator = new TreeGenerator(
                [
                        new Parameter('x', ONE),
                        new Parameter('y', TEN)
                ] as Set,
                [PLUS] as Set,
                3
        )

        when:
        def functionTree = generator.generate()

        then:
        assertTree(functionTree, '/tree/expected_tree_two_parameters_one_binary_operation.json')
    }

    def "The generator should generate tree from configuration: x, y parameters and '+', '-' signs"() {
        given:
        def generator = new TreeGenerator(
                [
                        new Parameter('x', ONE),
                        new Parameter('y', TEN)
                ] as Set,
                [PLUS, MINUS] as Set,
                3
        )

        when:
        def functionTree = generator.generate()

        then:
        assertTree(functionTree, '/tree/expected_tree_two_parameters_two_binary_operations.json')
    }

    def "The generator should generate tree from configuration: x parameter and '+', '-' signs"() {
        given:
        def generator = new TreeGenerator(
                [new Parameter('x', ONE)] as Set,
                [PLUS, MINUS] as Set,
                3
        )

        when:
        def functionTree = generator.generate()

        then:
        assertTree(functionTree, '/tree/expected_tree_one_parameter_two_binary_operations.json')
    }
}
