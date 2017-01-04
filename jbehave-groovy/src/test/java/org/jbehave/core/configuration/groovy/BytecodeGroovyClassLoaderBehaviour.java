package org.jbehave.core.configuration.groovy;

import groovy.lang.GroovyClassLoader;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static junit.framework.Assert.assertNotNull;

public class BytecodeGroovyClassLoaderBehaviour {

    @Test
    public void shouldCacheBytes() throws IOException {
        GroovyClassLoader classLoader = new BytecodeGroovyClassLoader();
        assertNotNull((Class<?>) classLoader.parseClass("class Hello { }"));
        InputStream bytecode = classLoader.getResourceAsStream("Hello.class");
        assertNotNull(bytecode);
        bytecode.close();
    }

}
