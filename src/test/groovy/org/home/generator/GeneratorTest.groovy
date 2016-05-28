package org.home.generator

import spock.lang.Specification
import spock.lang.Title

@Title("Validation of main functionality of generator")
class GeneratorTest extends Specification {
    def "IllegalArgumentException should be thrown when no parameters are configured"() {
        given: "The generator configuration with empty parameters configuration"
        List parameters = Mock(List.class)
        parameters.isEmpty() >> true

        when: "The generator is instancing"
        new Generator(parameters, [], 0)

        then: "IllegalArgumentException is thrown"
        def exception = thrown(IllegalArgumentException.class)

        and: "The exception message should notify about a problem with configuration with parameters"
        assert exception.message == '\'parameters\' argument has to be not empty'
    }

    def "IllegalArgumentException should be thrown when some parameters are configured and no signs are configured"() {
        given: "The generator configuration with no empty parameters configuration and empty sings configuration"
        List parameters = Mock(List.class)
        parameters.isEmpty() >> false

        List sings = Mock(List.class)
        sings.isEmpty() >> true

        when: "The generator is instancing"
        new Generator(parameters, sings, 0)

        then: "IllegalArgumentException is thrown"
        def exception = thrown(IllegalArgumentException.class)

        and: "The exception message should notify about a problem with configuration with signs"
        assert exception.message == '\'signs\' argument has to be not empty'
    }

    def "IllegalArgumentException should be thrown when some parameters and signs are configured and generated signs amount param is invalid"() {
        given: "The generator configuration with given amount of parameters and sings"
        List parameters = Mock(List.class)
        parameters.isEmpty() >> false

        List sings = Mock(List.class)
        sings.isEmpty() >> false

        when: "The generator is instancing"
        new Generator(parameters, sings, 0)

        then: "IllegalArgumentException is thrown"
        def exception = thrown(IllegalArgumentException.class)

        and: "The exception message should notify about a problem with configuration with signs"
        assert exception.message == '\'generatedSignsAmount\' argument has to be not empty'
    }


    def "The generator should generate four functions with input parameters: x and y and sing '+'"() {
        given: "The generator with configuration for parameters: x and y and sing '+'"
        def generator = new Generator(
                ['x', 'y'],
                ['+'],
                1
        )

        when: "The generation process is launched"
        generator.generate()

        then: "Four functions should be generated"
        assert generator.functions().size() == 4

        and: "The generator should generate the following list of functions: 'x+x', 'x+y', 'y+x', 'y+y'"
        assert generator.functions() == ['x+x', 'x+y', 'y+x', 'y+y']
    }

    def "The generator should generate four functions with input parameters: x and y and sing '-'"() {
        given: "The generator with configuration for parameters: x and y and sing '-'"
        def generator = new Generator(
                ['x', 'y'],
                ['-'],
                1
        )

        when: "The generation process is launched"
        generator.generate()

        then: "Four functions should be generated"
        assert generator.functions().size() == 4

        and: "The generator should generate the following list of functions: 'x-x', 'x-y', 'y-x', 'y-y'"
        assert generator.functions() == ['x-x', 'x-y', 'y-x', 'y-y']
    }

    def "The generator should generate 32 functions with input parameters: x, y and sings: '+', '-'"() {
        given: "The generator with configuration for parameters: x, y and sings: '+', '-'"
        def generator = new Generator(
                ['x', 'y'],
                ['+', '-'],
                2
        )

        when: "The generation process is launched"
        generator.generate()

        then: "Four functions should be generated"
        assert generator.functions().size() == 32
    }

    def "The generator should generate 8 functions with input parameter x and sings: '+', '-'"() {
        given: "The generator with configuration for parameter x and sings: '+', '-'"
        def generator = new Generator(
                ['x'],
                ['+', '-'],
                2
        )

        when: "The generation process is launched"
        generator.generate()

        then: "Four functions should be generated"
        assert generator.functions().size() == 4

        and: "The generator should generate the following list of functions: 'x+x+x', 'x+x-x', 'x-x+x', 'x-x-x'"
        assert generator.functions() == ['x+x+x', 'x+x-x', 'x-x+x', 'x-x-x']
    }
}
