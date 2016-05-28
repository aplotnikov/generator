package org.home.generator.function.nodes

import spock.lang.Specification
import spock.lang.Unroll

import static java.math.BigDecimal.TEN

class ParameterTest extends Specification {
    def parameter

    void setup() {
        parameter = new Parameter('x', TEN)
    }

    def "The parameter should contain information provided via constructor"() {
        when: "parameter is created with name='x' and value='10'"
        then:
        assert parameter.getName() == 'x'

        and:
        assert parameter.getValue() == TEN
    }

    @Unroll
    def "The parameter's method equal and hashcode should be equaled(#expectedResult) when another parameter name is '#name' and value is #value"() {
        when: "parameter is created with name='x' and value='10'"
        and:
        def anotherParameter = new Parameter(name, BigDecimal.valueOf(value))

        then:
        assert parameter.equals(anotherParameter) == expectedResult

        and:
        assert parameter.hashCode().equals(anotherParameter.hashCode()) == expectedResult

        where:
        name || value || expectedResult
        'x'  || 10    || true
        'x'  || 11    || true
        'y'  || 10    || false

    }

    def "'toString' method should return information about parameter name and value"() {
        when: "parameter is created with name='x' and value='10'"
        then:
        assert parameter.toString() == "Parameter[name='x', value=10]"
    }
}
