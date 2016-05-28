package org.home.generator.simple

import spock.lang.Specification

class SimpleFunctionGeneratorTest extends Specification {
    def "IllegalArgumentException should be thrown when no parameters are configured"() {
        given:
        List parameters = Mock(List.class)
        parameters.isEmpty() >> true

        when:
        new SimpleFunctionGenerator(parameters, [], 0)

        then:
        def exception = thrown(IllegalArgumentException.class)

        and: "The exception message should notify about a problem with configuration with parameters"
        assert exception.message == '\'parameters\' argument has to be not empty'
    }

    def "IllegalArgumentException should be thrown when some parameters are configured and no signs are configured"() {
        given:
        List parameters = Mock(List.class)
        parameters.isEmpty() >> false

        List sings = Mock(List.class)
        sings.isEmpty() >> true

        when:
        new SimpleFunctionGenerator(parameters, sings, 0)

        then:
        def exception = thrown(IllegalArgumentException.class)

        and:
        assert exception.message == '\'signs\' argument has to be not empty'
    }

    def "IllegalArgumentException should be thrown when some parameters and signs are configured and generated signs amount param is invalid"() {
        given:
        List parameters = Mock(List.class)
        parameters.isEmpty() >> false

        List sings = Mock(List.class)
        sings.isEmpty() >> false

        when:
        new SimpleFunctionGenerator(parameters, sings, 0)

        then:
        def exception = thrown(IllegalArgumentException.class)

        and:
        assert exception.message == '\'generatedSignsAmount\' argument has to be not empty'
    }


    def "The generator should generate four functions with input parameters: x and y and sing '+'"() {
        given:
        def generator = new SimpleFunctionGenerator(
                ['x', 'y'],
                ['+'],
                1
        )

        when:
        def functions = generator.generate()

        then:
        assert functions == ['x+x', 'x+y', 'y+x', 'y+y']
    }

    def "The generator should generate four functions with input parameters: x and y and sing '-'"() {
        given:
        def generator = new SimpleFunctionGenerator(
                ['x', 'y'],
                ['-'],
                1
        )

        when:
        def functions = generator.generate()

        then:
        assert functions == ['x-x', 'x-y', 'y-x', 'y-y']
    }

    def "The generator should generate 32 functions with input parameters: x, y and sings: '+', '-'"() {
        given:
        def generator = new SimpleFunctionGenerator(
                ['x', 'y'],
                ['+', '-'],
                2
        )

        when:
        def functions = generator.generate()

        then:
        assert functions.size() == 32
    }

    def "The generator should generate 8 functions with input parameter x and sings: '+', '-'"() {
        given:
        def generator = new SimpleFunctionGenerator(
                ['x'],
                ['+', '-'],
                2
        )

        when:
        def functions = generator.generate()

        then:
        assert functions == ['x+x+x', 'x+x-x', 'x-x+x', 'x-x-x']
    }
}
