package org.home.generator.function

import spock.lang.Specification

import static NodeTypes.FUNCTION

class FunctionNodeTest extends Specification {
    def node

    void setup() {
        node = new FunctionNode(FUNCTION)
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
        assert stringRepresentation == 'FunctionNode[type = FUNCTION, children = [childNode1, childNode2]]'
    }
}
