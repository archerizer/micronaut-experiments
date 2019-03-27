package com.archerizer.experiments.micronaut;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Greeting Tests")
class GreetingTest {

    @Test
    @DisplayName("Returns the message as specified to the constructor")
    void returnsMessage() {
        Greeting greeting = new Greeting("hi");
        assertThat(greeting.getMessage()).isEqualTo("hi");
    }
}
