package org.home.generator.configuration

import spock.lang.Specification
import spock.lang.Unroll

import static java.math.BigDecimal.ONE
import static java.math.BigDecimal.TEN
import static org.home.generator.configuration.BinaryOperations.MINUS
import static org.home.generator.configuration.BinaryOperations.PLUS

class FunctionGeneratorConfigurationTest extends Specification {
    def configuration

    void setup() {
        configuration = new FunctionGeneratorConfiguration()
    }

    def "Parameter's list should be configured via 'addParameter' method"() {
        when:
        configuration.addParameter('x', ONE)

        then:
        assert new Parameter('x', ONE) in configuration.getParameters()
        assert configuration.getParameters().size() == 1
    }

    def "Parameter's list should be unmodifiable"() {
        when:
        configuration.getParameters().add(new Parameter('x', ONE))

        then:
        thrown(UnsupportedOperationException.class)
    }

    def "Binary operation's list should be configured via 'addParameter method'"() {
        when:
        configuration.addBinaryOperation(MINUS)

        then:
        assert MINUS in configuration.getBinaryOperations()
        assert configuration.getBinaryOperations().size() == 1
    }

    def "Binary operation's list should be unmodifiable"() {
        when:
        configuration.getBinaryOperations().add(MINUS)

        then:
        thrown(UnsupportedOperationException.class)
    }

    def "Available element's amount should be configured via constructor with default value three"() {
        when: "configuration instance is created"
        then:
        assert configuration.getAvailableElementsAmount() == 3
    }

    def "Available element's amount should be possible to change to any value"() {
        when:
        configuration.setAvailableElementsAmount(4)

        then:
        assert configuration.getAvailableElementsAmount() == 4
    }

    @Unroll
    def "The configuration validation should be #expectedValidationResult when configuration contains #parametersAmount parameters, #binaryOperationsAmount binary operations, #availableElementsAmount available elements"() {
        given:
        if (parametersAmount == 1) {
            configuration.addParameter("parameterName", TEN)
        }

        if (binaryOperationsAmount == 1) {
            configuration.addBinaryOperation(MINUS)
        }

        configuration.setAvailableElementsAmount(availableElementsAmount)

        when:
        def valid = configuration.isValid()

        then:
        assert valid == expectedValidationResult

        where:
        parametersAmount || binaryOperationsAmount || availableElementsAmount || expectedValidationResult
        0                || 0                      || 0                       || false
        0                || 1                      || 1                       || false
        0                || 1                      || 0                       || false
        0                || 0                      || 1                       || false
        1                || 0                      || 0                       || false
        1                || 0                      || 1                       || false
        1                || 1                      || 0                       || false
        1                || 1                      || 1                       || true
    }

    def "'toString' method should return content which contains information about configured parameters, binary operations, available elements"() {
        given:
        configuration.addParameter("parameterName1", ONE)
        configuration.addParameter("parameterName2", TEN)

        and:
        configuration.addBinaryOperation(PLUS)
        configuration.addBinaryOperation(MINUS)

        when:
        def textConfigurationRepresentation = configuration.toString()

        then:
        assert textConfigurationRepresentation ==
                "Configuration[parameters=[Parameter[name='parameterName1', value=1], Parameter[name='parameterName2', value=10]], binary operations=[+, -], available elements=3]"
    }
}
