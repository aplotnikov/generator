package org.home.generator

import spock.lang.Specification
import spock.lang.Title

@Title("Validation of main functionality of generator")
class GeneratorTest extends Specification {
    def "generator should generate four functions with input parameters: x and y and sing '+'"() {

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

    def "generator should generate one function for input parameters: x and y and sing '-'"() {

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
