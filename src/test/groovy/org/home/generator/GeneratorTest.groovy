package org.home.generator

import spock.lang.Specification
import spock.lang.Title
import spock.lang.Unroll

@Title("Validation of main functionality of generator")
class GeneratorTest extends Specification {
    @Unroll
    def "IllegalArgumentException should be thrown when configuration contains parameters (#containsParameters) and sings (#containsSigns)"() {
        given: "The generator configuration with given amount of parameters and sings"
        List parameters = Mock(List.class)
        parameters.isEmpty() >> !containsParameters

        List sings = Mock(List.class)
        sings.isEmpty() >> !containsSigns

        when: "The generator is instancing"
        new Generator(parameters, sings)

        then: "IllegalArgumentException is thrown"
        def exception = thrown(IllegalArgumentException.class)

        and: "The exception message equals to expectedExceptionMessage parameter"
        assert exception.message == expectedExceptionMessage

        where:
        containsParameters | containsSigns | expectedExceptionMessage
        false              | true          | '\'parameters\' argument has to be not empty'
        true               | false         | '\'signs\' argument has to be not empty'
    }

    def "The generator should generate four functions with input parameters: x and y and sing '+'"() {

        given: "The generator with configuration for parameters: x and y and sing '+'"
        def generator = new Generator(
                ['x', 'y'],
                ['+']
        )

        when: "The generation process is launched"
        generator.generate()

        then: "Four functions should be generated"
        assert generator.functions().size() == 4

        and: "The generator should generate the following list of functions: 'x+x', 'x+y', 'y+x', 'y+y'"
        assert generator.functions() == ['x+x', 'x+y', 'y+x', 'y+y']
    }

    def "The generator should generate one function with input parameters: x and y and sing '-'"() {

        given: "The generator with configuration for parameters: x and y and sing '-'"
        def generator = new Generator(
                ['x', 'y'],
                ['-']
        )

        when: "The generation process is launched"
        generator.generate()

        then: "Four functions should be generated"
        assert generator.functions().size() == 4

        and: "The generator should generate the following list of functions: 'x-x', 'x-y', 'y-x', 'y-y'"
        assert generator.functions() == ['x-x', 'x-y', 'y-x', 'y-y']
    }
}
