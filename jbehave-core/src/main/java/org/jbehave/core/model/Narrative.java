package org.jbehave.core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.jbehave.core.configuration.Keywords;

public class Narrative {

    public static final Narrative EMPTY = new Narrative("", "", "");

    private static final String NL = "\n";
    private static final String SPACE = " ";

    private final String inOrderTo;
    private final String asA;
    private final String iWantTo;
    private final String soThat;

    public Narrative(String inOrderTo, String asA, String iWantTo) {
        this(inOrderTo, asA, iWantTo, "");
    }

    public Narrative(String inOrderTo, String asA, String iWantTo, String soThat) {
        this.inOrderTo = inOrderTo;
        this.asA = asA;
        this.iWantTo = iWantTo;
        this.soThat = soThat;
    }

    public String inOrderTo() {
        return this.inOrderTo;
    }

    public String asA() {
        return this.asA;
    }

    public String iWantTo() {
        return this.iWantTo;
    }

    public String soThat() {
        return this.soThat;
    }

    public boolean isEmpty() {
        return EMPTY == this;
    }

    public boolean isAlternative() {
        return this.inOrderTo.isEmpty();
    }

    public String asString(Keywords keywords) {
        StringBuffer sb = new StringBuffer();
        if (isAlternative()) {
            sb.append(keywords.asA()).append(SPACE).append(this.asA).append(NL);
            sb.append(keywords.iWantTo()).append(SPACE).append(this.iWantTo)
                    .append(NL);
            sb.append(keywords.soThat()).append(SPACE).append(this.soThat);
        } else {
            sb.append(keywords.inOrderTo()).append(SPACE).append(this.inOrderTo)
                    .append(NL);
            sb.append(keywords.asA()).append(SPACE).append(this.asA).append(NL);
            sb.append(keywords.iWantTo()).append(SPACE).append(this.iWantTo);
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }


}
