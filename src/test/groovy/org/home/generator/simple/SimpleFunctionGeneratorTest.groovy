package org.home.generator.simple

import org.home.generator.configuration.FunctionGeneratorConfiguration
import org.home.generator.configuration.Parameter
import spock.lang.Specification

import static java.math.BigDecimal.ONE
import static org.home.generator.configuration.BinaryOperations.MINUS
import static org.home.generator.configuration.BinaryOperations.PLUS

class SimpleFunctionGeneratorTest extends Specification {
    def "IllegalStateException should be thrown when generator configuration is configured improperly"() {
        given:
        def functionGeneratorConfiguration = Mock(FunctionGeneratorConfiguration.class)
        functionGeneratorConfiguration.isValid() >> false

        when:
        new SimpleFunctionGenerator(functionGeneratorConfiguration)

        then:
        def exception = thrown(IllegalStateException.class)

        and:
        assert exception.message == 'Function generator configuration has to be properly configured.'
    }

    def "The generator should generate four functions with input parameters: x and y and sing '+'"() {
        given:
        def functionGeneratorConfiguration = Mock(FunctionGeneratorConfiguration.class)
        functionGeneratorConfiguration.isValid() >> true
        functionGeneratorConfiguration.getParameters() >> [new Parameter('x', ONE), new Parameter('y', ONE)]
        functionGeneratorConfiguration.getBinaryOperations() >> [PLUS]
        functionGeneratorConfiguration.getAvailableElementsAmount() >> 1

        and:
        def generator = new SimpleFunctionGenerator(functionGeneratorConfiguration)

        when:
        def functions = generator.generate()

        then:
        assert functions == ['x+x', 'x+y', 'y+x', 'y+y']
    }

    def "The generator should generate 32 functions with input parameters: x, y and sings: '+', '-'"() {
        given:
        def functionGeneratorConfiguration = Mock(FunctionGeneratorConfiguration.class)
        functionGeneratorConfiguration.isValid() >> true
        functionGeneratorConfiguration.getParameters() >> [new Parameter('x', ONE), new Parameter('y', ONE)]
        functionGeneratorConfiguration.getBinaryOperations() >> [PLUS, MINUS]
        functionGeneratorConfiguration.getAvailableElementsAmount() >> 2

        and:
        def generator = new SimpleFunctionGenerator(functionGeneratorConfiguration)

        when:
        def functions = generator.generate()

        then:
        assert functions.size() == 32
    }

    def "The generator should generate 8 functions with input parameter x and sings: '+', '-'"() {
        given:
        def functionGeneratorConfiguration = Mock(FunctionGeneratorConfiguration.class)
        functionGeneratorConfiguration.isValid() >> true
        functionGeneratorConfiguration.getParameters() >> [new Parameter('x', ONE)]
        functionGeneratorConfiguration.getBinaryOperations() >> [PLUS, MINUS]
        functionGeneratorConfiguration.getAvailableElementsAmount() >> 2

        and:
        def generator = new SimpleFunctionGenerator(functionGeneratorConfiguration)

        when:
        def functions = generator.generate()

        then:
        assert functions == ['x+x+x', 'x+x-x', 'x-x+x', 'x-x-x']
    }
}
