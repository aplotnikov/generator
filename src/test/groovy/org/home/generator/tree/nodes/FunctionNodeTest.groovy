package org.home.generator.tree.nodes

import org.home.generator.configuration.Function
import spock.lang.Specification

class FunctionNodeTest extends Specification {
    def node

    void setup() {
        def function = Mock(Function.class)
        function.toString() >> "function"

        node = new FunctionNode(function)
    }

    def "New instance of node should contain no children"() {
        when: "the node is created"
        then:
        assert !node.hasChildren()
    }

    def "The list of children should be unmodifiable"() {
        given: "function node"
        when:
        node.getChildren().add(Mock(FunctionNode.class))

        then:
        thrown(UnsupportedOperationException.class)
    }

    def "The list of children should be modified via 'addChild' method"() {
        given: "root function node"
        and:
        def childNode = Mock(FunctionNode.class)

        when:
        node.addChild(childNode)

        then:
        assert node.hasChildren()
        assert node.getChildren() == [childNode]
    }

    def "'toString' method should show information about node type and children"() {
        given: "root function node"
        and:
        def childNode = Mock(FunctionNode.class)
        childNode.toString() >>> ["childNode1", "childNode2"]

        and:
        node.addChild(childNode)
        node.addChild(childNode)

        when:
        def stringRepresentation = node.toString()

        then:
        assert stringRepresentation == 'TreeNode[type = FUNCTION, attachedObject = function, children = [childNode1, childNode2]]'
    }
}
