package org.home.generator.function

import spock.lang.Specification
import spock.lang.Title

import static org.home.generator.function.NodeType.FUNCTION

@Title('Specification for function node class')
class FunctionNodeTest extends Specification {
    def node

    void setup() {
        node = new FunctionNode(FUNCTION)
    }

    def "New instance of node should contain no children"() {
        when: "the node is created"
        then: "the node contains no children"
        assert !node.hasChildren()
    }

    def "The list of children should be unmodifiable"() {
        given: "function node"
        when: "the list of children is changing"
        node.getChildren().add(Mock(FunctionNode.class))

        then: "UnsupportedOperationException is thrown"
        thrown(UnsupportedOperationException.class)
    }

    def "The list of children should be modified via 'addChild' method"() {
        given: "root function node"
        and: "child function node"
        def childNode = Mock(FunctionNode.class)

        when: "'addChild' method is executed"
        node.addChild(childNode)

        then: "root node contains child node"
        assert node.hasChildren()
        assert node.getChildren() == [childNode]
    }

    def "'toString' method should show information about node type and children"() {
        given: "root function node"
        and: "child function node"
        def childNode = Mock(FunctionNode.class)
        childNode.toString() >>> ["childNode1", "childNode2"]

        and: "root node contains children"
        node.addChild(childNode)
        node.addChild(childNode)

        when: "'toString' method is launched"
        def stringRepresentation = node.toString()

        then: "String content should contains information about node type and children nodes"
        assert stringRepresentation == 'FunctionNode[type = FUNCTION, children = [childNode1, childNode2]]'
    }
}
