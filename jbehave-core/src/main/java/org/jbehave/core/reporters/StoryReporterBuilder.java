package org.jbehave.core.reporters;

import org.jbehave.core.configuration.Configuration;
import org.jbehave.core.configuration.Keywords;
import org.jbehave.core.i18n.LocalizedKeywords;
import org.jbehave.core.io.CodeLocations;
import org.jbehave.core.io.StoryLocation;
import org.jbehave.core.reporters.FilePrintStreamFactory.FileConfiguration;
import org.jbehave.core.reporters.FilePrintStreamFactory.FilePathResolver;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import static java.util.Arrays.asList;

/**
 * <p>
 * A <a href="http://en.wikipedia.org/wiki/Builder_pattern">Builder</a> for
 * {@link StoryReporter}s. It builds a {@link DelegatingStoryReporter} with
 * delegates for a number of formats - mostly file-based ones except
 * {@Format.CONSOLE}. It requires a
 * {@link FilePrintStreamFactory} and provides default delegate instances for
 * each format.
 * </p>
 * <p>
 * To build a reporter for a single story path with default and given formats:
 * <p>
 * <pre>
 * Class&lt;MyStory&gt; storyClass = MyStory.class;
 * StoryPathResolver resolver = new UnderscoredCamelCaseResolver();
 * String storyPath = resolver.resolve(storyClass);
 * StoryReporter reporter = new StoryReporterBuilder()
 *         .withCodeLocation(CodeLocations.codeLocationFromClass(storyClass))
 *         .withDefaultFormats().withFormats(TXT, HTML, XML).build(storyPath);
 * </pre>
 * <p>
 * </p>
 * <p>
 * The builder is configured to build with the {@link Format#STATS} as default
 * format. To change the default formats the user can override the method:
 * <p>
 * <pre>
 * new StoryReporterBuilder() {
 *     protected StoryReporterBuilder withDefaultFormats() {
 *         return withFormats(STATS);
 *     }
 * }
 * </pre>
 * <p>
 * </p>
 * <p>
 * The builder configures the file-based reporters to output to the default file
 * directory {@link FileConfiguration#DIRECTORY} as relative to the code
 * location. In some case, e.g. with Ant class loader, the code source location
 * from class may not be properly set. In this case, we may specify it from a
 * file:
 * <p>
 * <pre>
 * new StoryReporterBuilder()
 *         .withCodeLocation(
 *                 CodeLocations.codeLocationFromFile(new File(&quot;target/classes&quot;)))
 *         .withDefaultFormats().withFormats(TXT, HTML, XML).build(storyPath);
 * </pre>
 * <p>
 * </p>
 * <p>
 * By default, the reporters will output minimal failure information, the single
 * line describing the failure cause and the outcomes if failures occur. To
 * configure the failure trace to be reported as well:
 * <p>
 * <pre>
 * new StoryReporterBuilder().withFailureTrace(true)
 * </pre>
 * <p>
 * </p>
 * <p>
 * If failure trace is reported, it is with the full stack trace. In some cases,
 * it's useful to have it compressed, eliminating unnecessary lines that are not
 * very informative:
 * <p>
 * <pre>
 * new StoryReporterBuilder().withFailureTraceCompression(true)
 * </pre>
 * <p>
 * </p>
 * <p>
 * <p>
 * To specify the use of keywords for a given locale:
 * <p>
 * <pre>
 * new StoryReporterBuilder().withKeywords(new LocalisedKeywords(Locale.IT)
 * </pre>
 * <p>
 * </p>
 * <p>
 * <p>
 * The builder provides default instances for all reporters, using the default
 * output patterns. To change the reporter for a specific instance, e.g. to
 * report format <b>TXT</b> to <b>.text</b> files and to inject other
 * non-default parameters, such as the custom output patterns:
 * <p>
 * <pre>
 * new StoryReporterBuilder(){
 *   public StoryReporter reporterFor(String storyPath, Format format){
 *       FilePrintStreamFactory factory = new FilePrintStreamFactory(new StoryLocation(storyPath, codeLocation));
 *       switch (format) {
 *           case TXT:
 *               factory.useConfiguration(new FileConfiguration("text"));
 *               Properties customPatterns = new Properties();
 *               customPatterns.setProperty("successful", "{0}(YEAH!!!)\n");
 *               return new TxtOutput(factory.createPrintStream(), customPatterns, keywords);
 *            default:
 *               return super.reporterFor(format);
 *   }
 * }
 * </pre>
 * <p>
 * </p>
 */
public class StoryReporterBuilder {

    protected String relativeDirectory;
    protected FilePathResolver pathResolver;
    protected URL codeLocation;
    protected Properties viewResources;
    protected boolean reportFailureTrace = false;
    protected boolean compressFailureTrace = false;
    protected Keywords keywords;
    protected CrossReference crossReference;
    protected boolean multiThreading;
    protected Configuration configuration;
    private List<org.jbehave.core.reporters.Format> formats = new ArrayList<org.jbehave.core.reporters.Format>();
    private FileConfiguration defaultFileConfiguration = new FileConfiguration();

    public StoryReporterBuilder() {
    }

    public StoryReporterBuilder(Configuration configuration) {
        this.configuration = configuration;
    }

    public File outputDirectory() {
        return filePrintStreamFactory("").outputDirectory();
    }

    public String relativeDirectory() {
        if (this.relativeDirectory == null) {
            this.relativeDirectory = this.defaultFileConfiguration.getRelativeDirectory();
        }
        return this.relativeDirectory;
    }

    public FilePathResolver pathResolver() {
        if (this.pathResolver == null) {
            this.pathResolver = this.defaultFileConfiguration.getPathResolver();
        }
        return this.pathResolver;
    }

    public URL codeLocation() {
        if (this.codeLocation == null) {
            this.codeLocation = CodeLocations.codeLocationFromPath("target/classes");
        }
        return this.codeLocation;
    }

    public List<org.jbehave.core.reporters.Format> formats() {
        return this.formats;
    }

    public List<String> formatNames(boolean toLowerCase) {
        Locale locale = Locale.getDefault();
        if (this.keywords instanceof LocalizedKeywords) {
            locale = ((LocalizedKeywords) this.keywords).getLocale();
        }
        List<String> names = new ArrayList<String>();
        for (org.jbehave.core.reporters.Format format : this.formats) {
            String name = format.name();
            if (toLowerCase) {
                name = name.toLowerCase(locale);
            }
            names.add(name);
        }
        return names;
    }

    public Keywords keywords() {
        if (this.keywords == null) {
            if (this.configuration != null) {
                this.keywords = this.configuration.keywords();
            }
            this.keywords = new LocalizedKeywords();
        }
        return this.keywords;
    }

    public boolean multiThreading() {
        return this.multiThreading;
    }

    public boolean reportFailureTrace() {
        return this.reportFailureTrace;
    }

    public boolean compressFailureTrace() {
        return this.compressFailureTrace;
    }

    public Properties viewResources() {
        if (this.viewResources == null) {
            if (this.configuration != null) {
                this.viewResources = this.configuration.viewGenerator()
                        .defaultViewProperties();
            } else {
                this.viewResources = new FreemarkerViewGenerator()
                        .defaultViewProperties();
            }
        }
        return this.viewResources;
    }

    public StoryReporterBuilder withRelativeDirectory(String relativeDirectory) {
        this.relativeDirectory = relativeDirectory;
        return this;
    }

    public StoryReporterBuilder withPathResolver(FilePathResolver pathResolver) {
        this.pathResolver = pathResolver;
        return this;
    }

    public StoryReporterBuilder withCodeLocation(URL codeLocation) {
        this.codeLocation = codeLocation;
        return this;
    }

    public CrossReference crossReference() {
        return this.crossReference;
    }

    public boolean hasCrossReference() {
        return this.crossReference != null;
    }

    public StoryReporterBuilder withCrossReference(CrossReference crossReference) {
        this.crossReference = crossReference;
        return this;
    }

    public StoryReporterBuilder withDefaultFormats() {
        return withFormats(Format.STATS);
    }

    /**
     * @deprecated Use {@link withFormats(org.jbehave.core.reporters.Format...
     * formats)}
     */
    @Deprecated
    public StoryReporterBuilder withFormats(Format... formats) {
        List<org.jbehave.core.reporters.Format> formatz = new ArrayList<org.jbehave.core.reporters.Format>();
        for (Format format : formats) {
            formatz.add(format.realFormat);
        }
        this.formats.addAll(formatz);
        return this;
    }

    public StoryReporterBuilder withFormats(
            org.jbehave.core.reporters.Format... formats) {
        this.formats.addAll(asList(formats));
        return this;
    }

    public StoryReporterBuilder withReporters(StoryReporter... reporters) {
        for (StoryReporter reporter : reporters) {
            this.formats.add(new ProvidedFormat(reporter));
        }
        return this;
    }

    public StoryReporterBuilder withFailureTrace(boolean reportFailureTrace) {
        this.reportFailureTrace = reportFailureTrace;
        return this;
    }

    public StoryReporterBuilder withFailureTraceCompression(
            boolean compressFailureTrace) {
        this.compressFailureTrace = compressFailureTrace;
        return this;
    }

    public StoryReporterBuilder withKeywords(Keywords keywords) {
        this.keywords = keywords;
        return this;
    }

    public StoryReporterBuilder withMultiThreading(boolean multiThreading) {
        this.multiThreading = multiThreading;
        return this;
    }

    public StoryReporterBuilder withViewResources(Properties resources) {
        this.viewResources = resources;
        return this;
    }

    public StoryReporter build(String storyPath) {
        Map<org.jbehave.core.reporters.Format, StoryReporter> delegates = new HashMap<org.jbehave.core.reporters.Format, StoryReporter>();
        for (org.jbehave.core.reporters.Format format : this.formats) {
            delegates.put(format, reporterFor(storyPath, format));
        }

        DelegatingStoryReporter delegate = new DelegatingStoryReporter(
                delegates.values());
        return new ConcurrentStoryReporter(new NullStoryReporter(), delegate,
                multiThreading());
    }

    public Map<String, StoryReporter> build(List<String> storyPaths) {
        Map<String, StoryReporter> reporters = new HashMap<String, StoryReporter>();
        for (String storyPath : storyPaths) {
            reporters.put(storyPath, build(storyPath));
        }
        reporters.put("*", build("*"));
        return reporters;
    }

    public StoryReporter reporterFor(String storyPath, Format format) {
        return reporterFor(storyPath, format.realFormat);
    }

    public StoryReporter reporterFor(String storyPath,
                                     org.jbehave.core.reporters.Format format) {
        FilePrintStreamFactory factory = filePrintStreamFactory(storyPath);
        return format.createStoryReporter(factory, this);
    }

    protected FilePrintStreamFactory filePrintStreamFactory(String storyPath) {
        return new FilePrintStreamFactory(new StoryLocation(codeLocation(),
                storyPath), fileConfiguration(""));
    }

    public FileConfiguration fileConfiguration(String extension) {
        return new FileConfiguration(relativeDirectory(), extension,
                pathResolver());
    }

    public enum Format {
        CONSOLE(org.jbehave.core.reporters.Format.CONSOLE), IDE_CONSOLE(
                org.jbehave.core.reporters.Format.IDE_CONSOLE), TXT(
                org.jbehave.core.reporters.Format.TXT), HTML(
                org.jbehave.core.reporters.Format.HTML), XML(
                org.jbehave.core.reporters.Format.XML), STATS(
                org.jbehave.core.reporters.Format.STATS);

        private org.jbehave.core.reporters.Format realFormat;

        Format(org.jbehave.core.reporters.Format realFormat) {
            this.realFormat = realFormat;
        }

    }

    /**
     * A Format that wraps a StoryReporter instance provided.
     */
    public static class ProvidedFormat extends
            org.jbehave.core.reporters.Format {

        private final StoryReporter reporter;

        public ProvidedFormat(StoryReporter reporter) {
            super(reporter.getClass().getSimpleName());
            this.reporter = reporter;
        }

        @Override
        public StoryReporter createStoryReporter(
                FilePrintStreamFactory factory,
                StoryReporterBuilder storyReporterBuilder) {
            return this.reporter;
        }

    }
}
