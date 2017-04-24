package com.swedbank.decathlon;

import com.swedbank.decathlon.commandline.Parameter;
import com.swedbank.decathlon.data.Decathlon;
import com.swedbank.decathlon.file.FileType;
import com.swedbank.decathlon.file.FileUtil;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.Console;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import static com.swedbank.decathlon.commandline.Parameter.INPUT_FILE;
import static com.swedbank.decathlon.commandline.Parameter.OUTPUT_FILE;

/**
 * The Class DecathlonApp
 * <p>
 * DecathlonApp singleton app with lazy loading.
 * <p>
 *
 * @author <a href="mailto:sauliuxx@gmail.com?Subject=Regarding%20code%20(Project: Decathlon com.swedbank.decathlon.DecathlonApp)" target="_top">Saulius Bira</a>
 *         Date: 4/19/2017
 */
public class DecathlonApp {

    /**
     * The logger
     */
    private static final Logger logger =
            Logger.getLogger(DecathlonApp.class.getName());
    /**
     * Log file name
     */
    private static final String LOG_FILE_NAME = DecathlonApp.class.getSimpleName() + ".log";

    /**
     * Result output xsl transformation.
     */
    private static final String XSL_FILE_NAME = "decatlon.xsl";

    /**
     * Application parameter context
     */
    private HashMap<Parameter, String> parameterContext;

    /**
     * DecathlonApp constructor
     */
    private DecathlonApp() {

    }

    /**
     * Entry point.
     *
     * @param args
     */
    public static void main(String... args) {

        try {
            //initializing loggin for warnings with disabled console print.
            initLogging(Level.WARNING, false);
        } catch (IOException e) {
            logger.warning("Cannot create log file(" + LOG_FILE_NAME + ").");
            e.printStackTrace();
        }

        try {


            logger.info("Application started.");

            Console console = System.console();
            Scanner in = null;
            //If console isn't supported
            if (console == null) {
                in = new Scanner(System.in, StandardCharsets.UTF_8.name());
            }
            HashMap<Parameter, String> appParameters = new HashMap<>(Parameter.values().length);
            //Parameter read
            for (Parameter p : EnumSet.of(INPUT_FILE, OUTPUT_FILE)) {
                boolean valid = false;
                while (!valid) {
                    System.out.println(p.getInputDescription());
                    String input = console != null ? console.readLine() : in.nextLine();
                    appParameters.put(p, input);
                    valid = p.isValid(input);
                    if (!p.getErrorMessage().isEmpty() || !p.getInfoMessage().isEmpty()) {
                        System.out.println(p.getErrorMessage() + p.getInfoMessage());
                    }
                }
            }

            //Init app lazy instantiation
            DecathlonApp app = DecathlonApp.getInstance();

            app.setParameterContext(appParameters);

            Decathlon d = app.parseData();

            app.storeResults(d, OUTPUT_FILE);

            logger.info("Application stopped.");

        } catch (IOException | JAXBException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            System.err.print("Error occurred for details please check: " + Paths.get("").toAbsolutePath().toString() + File.separator + LOG_FILE_NAME);
        }
    }

    /**
     * Parse data from input file.
     *
     * @return
     * @throws IOException
     */
    public Decathlon parseData() throws IOException {
        String ext = FileUtil.getExtension(INPUT_FILE.getValue());
        return FileType.getFileTypeByExtension(ext).getDataParser().parse();
    }

    /**
     * Store data to output file.
     *
     * @param decathlon   Decathlon object which should be stored to the output file.
     * @param fileToStore output parameter.
     */
    public void storeResults(final Decathlon decathlon, final Parameter fileToStore) throws JAXBException, IOException {

        copyResourceToOutputDir(fileToStore.getValue(), XSL_FILE_NAME);

        JAXBContext context = JAXBContext.newInstance(Decathlon.class);
        Marshaller m = context.createMarshaller();
        //inject xsl for beautiful output.
        m.setProperty("com.sun.xml.internal.bind.xmlHeaders",
                "<?xml-stylesheet type='text/xsl' href='" + XSL_FILE_NAME + "' ?>");
        //for pretty-print XML in JAXB
        m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

        // Write to System.out for debugging
        //m.marshal(decathlon, System.out);

        File f = new File(OUTPUT_FILE.getValue());
        // Write to File
        m.marshal(decathlon, f);

        //Display result in IE
        Runtime runtime = Runtime.getRuntime();
        Process process = runtime.exec("C:\\Program Files\\Internet Explorer\\iexplore.exe " + f.getAbsolutePath());
    }

    /**
     * Copy resource file to specified folder.
     *
     * @param outputPath
     * @param resouceName
     * @return
     * @throws IOException
     */
    private Path copyResourceToOutputDir(String outputPath, String resouceName) throws IOException {

        File xsl = new File(getClass().getClassLoader().getResource(resouceName).getFile());

        Path out = Paths.get(outputPath);
        //get output directory
        Path outputDir = Files.isDirectory(out) ? out : out.getParent();

        Path resourceDestination = outputDir.resolve(xsl.getName());

        if (!Files.exists(resourceDestination)) {
            Files.copy(xsl.toPath(), resourceDestination);
        }
        return resourceDestination;
    }

    /**
     * Initialize logging options
     *
     * @param level
     * @param printToConsole
     * @throws IOException
     */
    private static void initLogging(final Level level, final boolean printToConsole) throws IOException {
        //Log file handler
        final FileHandler fileHandler = new FileHandler(LOG_FILE_NAME, true);
        logger.setUseParentHandlers(printToConsole);
        fileHandler.setEncoding(StandardCharsets.UTF_8.name());
        fileHandler.setFormatter(new SimpleFormatter());
        logger.addHandler(fileHandler);
        logger.setLevel(level);
    }

    /**
     * inner static class to holds DecathlonApp instance (singleton with lazy loading).
     */
    private static class DecathlonAppHelper {
        /**
         * DecathlonApp instance
         */
        private static final DecathlonApp INSTANCE = new DecathlonApp();
    }

    /**
     * Get DecathlonApp application instance.
     *
     * @return
     */
    public static DecathlonApp getInstance() {
        return DecathlonAppHelper.INSTANCE;
    }

    /**
     * Get application parameter context
     *
     * @return
     */
    public HashMap<Parameter, String> getParameterContext() {
        return parameterContext;
    }

    /**
     * Set application parameter context.
     *
     * @param parameterContext
     */
    public void setParameterContext(final HashMap<Parameter, String> parameterContext) {
        this.parameterContext = parameterContext;
    }
}
