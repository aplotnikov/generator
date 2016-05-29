package org.home.generator.configuration

import spock.lang.Specification
import spock.lang.Unroll

class BinaryOperationsTest extends Specification {
    @Unroll
    def "'toString' method for #operation should return #expectedTextOperationRepresentation"() {
        given:
        def binaryOperation = BinaryOperations.valueOf(operation)

        when:
        def actualTextOperationRepresentation = binaryOperation.toString()

        then:
        assert actualTextOperationRepresentation == expectedTextOperationRepresentation

        where:
        operation  || expectedTextOperationRepresentation
        'PLUS'     || '+'
        'MINUS'    || '-'
        'MULTIPLY' || '*'
        'DIVIDE'   || '/'
    }
}
