package org.jbehave.core.io.rest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import static org.apache.commons.lang3.StringUtils.substringAfterLast;

/**
 * Represents a resource retrieved from a REST API.
 */
public class Resource {

    private final String uri;
    private final String name;
    private final String parentName;
    private List<String> breadcrumbs;
    private String content;
    private String syntax;

    public Resource(String uri) {
        this(uri, substringAfterLast(uri, "/"));
    }

    public Resource(String uri, String name) {
        this(uri, name, null);
    }

    public Resource(String uri, String name, String parentName) {
        this.uri = uri;
        this.name = name;
        this.parentName = parentName;
    }

    public String getURI() {
        return this.uri;
    }

    public String getName() {
        return this.name;
    }

    public String getParentName() {
        return this.parentName;
    }

    public boolean hasParent() {
        return this.parentName != null;
    }

    public List<String> getBreadcrumbs() {
        return this.breadcrumbs;
    }

    public void setBreadcrumbs(List<String> breadcrumbs) {
        this.breadcrumbs = breadcrumbs;
    }

    public boolean hasBreadcrumbs() {
        return this.breadcrumbs != null && !this.breadcrumbs.isEmpty();
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean hasContent() {
        return isNotBlank(this.content);
    }

    public String getSyntax() {
        return this.syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public boolean hasSyntax() {
        return isNotBlank(this.syntax);
    }

    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }

}
