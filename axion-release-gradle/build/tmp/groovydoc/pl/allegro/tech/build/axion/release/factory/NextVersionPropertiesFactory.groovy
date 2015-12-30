package pl.allegro.tech.build.axion.release.factory

import org.gradle.api.Project
import pl.allegro.tech.build.axion.release.config.NextVersionConfig
import pl.allegro.tech.build.axion.release.domain.properties.NextVersionProperties

class NextVersionPropertiesFactory {

    private static final String NEXT_VERSION_PROPERTY = "release.version"

    private static final String DEPRECATED_NEXT_VERSION_PROPERTY = "release.nextVersion"

    static NextVersionProperties create(Project project, NextVersionConfig config) {
        String nextVersion = project.hasProperty(NEXT_VERSION_PROPERTY) ? project.property(NEXT_VERSION_PROPERTY) : null
        if (nextVersion == null && project.hasProperty(DEPRECATED_NEXT_VERSION_PROPERTY)) {
            project.logger.warn("Using deprecated parameter: $DEPRECATED_NEXT_VERSION_PROPERTY! Use $NEXT_VERSION_PROPERTY instead.")
            nextVersion = project.property(DEPRECATED_NEXT_VERSION_PROPERTY)
        }

        return new NextVersionProperties(nextVersion: nextVersion, suffix: config.suffix, separator: config.separator,
                serializer: config.serializer, deserializer: config.deserializer)
    }

}
