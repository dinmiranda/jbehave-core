package org.jbehave.core.io.rest.filesystem;

import org.apache.commons.lang3.StringUtils;
import org.jbehave.core.io.rest.Resource;

import java.io.File;

import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

public class FilesystemUtils {

    public static File asFile(Resource resource, String parentPath, String ext) {
        String childPath = (resource.hasBreadcrumbs() ? StringUtils.join(resource.getBreadcrumbs(), "/") : "") + "/" + resource.getName() + ext;
        return new File(parentPath, childPath);
    }

    public static String fileNameWithoutExt(File file) {
        return substringBeforeLast(file.getName(), ".");
    }

    public static String normalisedPathOf(File file) {
        return file.getPath().replace('\\', '/');
    }

}
