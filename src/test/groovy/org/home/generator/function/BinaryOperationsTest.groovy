package org.home.generator.function

import spock.lang.Specification
import spock.lang.Unroll

class BinaryOperationsTest extends Specification {
    @Unroll
    def "'toString' method for #operation should return #expectedToStringContent"() {
        given: "binary operation"
        def binaryOperation = BinaryOperations.valueOf(operation)

        when:
        def actualToStringContent = binaryOperation.toString()

        then:
        assert actualToStringContent == expectedToStringContent

        where:
        operation  || expectedToStringContent
        'PLUS'     || '+'
        'MINUS'    || '-'
        'MULTIPLY' || '*'
        'DIVIDE'   || '/'
    }
}
