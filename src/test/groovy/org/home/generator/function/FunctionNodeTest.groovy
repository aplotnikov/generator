package org.home.generator.function

import spock.lang.Specification
import spock.lang.Title

@Title("Specification for function node class")
class FunctionNodeTest extends Specification {
    def "New instance of node should contain no children"() {
        when: "the node is created"
        def node = new FunctionNode()

        then: "the node contains no children"
        assert !node.hasChildren()
    }

    def "The list of children should be unmodifiable"() {
        given: "function node"
        def node = new FunctionNode()

        when: "the list of children is changing"
        node.getChildren().add(Mock(FunctionNode.class))

        then: "UnsupportedOperationException is thrown"
        thrown(UnsupportedOperationException.class)
    }

    def "The list of children should be modified via 'addChild' method"() {
        given: "root function node"
        def rootNode = new FunctionNode()

        and: "child function node"
        def childNode = Mock(FunctionNode.class)

        when: "'addChild' method is executed"
        rootNode.addChild(childNode)

        then: "root node contains child node"
        assert rootNode.hasChildren()
        assert rootNode.getChildren() == [childNode]
    }
}
