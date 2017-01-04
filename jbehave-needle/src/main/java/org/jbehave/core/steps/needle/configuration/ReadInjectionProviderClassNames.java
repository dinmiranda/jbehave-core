package org.jbehave.core.steps.needle.configuration;

import java.util.LinkedHashSet;
import java.util.ResourceBundle;
import java.util.Set;

/**
 * Read ClassNames from properties.
 *
 * @author Jan Galinski, Holisticon AG (jan.galinski@holisticon.de)
 * @author Simon Zambrovski, Holisticon AG (simon.zambrovski@holisticon.de)
 */
public enum ReadInjectionProviderClassNames {
    /**
     * Singleton Instance
     */
    INSTANCE;

    private static final String CUSTOM_INJECTION_PROVIDER_CLASSES = "custom.injection.provider.classes";

    public final Set<String> apply(final ResourceBundle resourceBundle) {
        final LinkedHashSet<String> result = new LinkedHashSet<String>();

        if (resourceBundle != null && resourceBundle.containsKey(CUSTOM_INJECTION_PROVIDER_CLASSES)) {
            final String csvProperty = resourceBundle.getString(CUSTOM_INJECTION_PROVIDER_CLASSES);
            for (final String className : csvProperty.split(",")) {
                if (className != null) {
                    final String trim = className.trim();
                    if (!"".equals(trim)) {
                        result.add(trim);
                    }
                }
            }
        }

        return result;
    }

}
