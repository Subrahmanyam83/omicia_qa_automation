package Specs.Utilities

import org.testng.TestNG
import org.testng.xml.*

/**
 * Created by E002183 on 5/7/2016.
 */
class TestNGXMLCreator {

    public static void main(String[] args) {

        String rootDir = new File(".").getCanonicalPath();
        convertPropertiesToSystemProperties()
        String PACKAGES = System.getProperty("package.name")
        String GROUPS = System.getProperty("group.name")
        String CLASSES = System.getProperty("class.name")
        String METHODS = System.getProperty("method.name")
        String testngXMLFilePath = rootDir + System.getProperty("testng.xml.file.path").replace("/", File.separator)
        int thread_count = System.getProperty("threadCount").toInteger();

        TestNG myTestNG = new TestNG();
        List<XmlSuite> mySuites = new ArrayList<XmlSuite>();

        XmlSuite suite = new XmlSuite();
        suite.setName("Omicia OPAL Suite");
        suite.setParallel("classes");
        suite.setThreadCount(thread_count)
        suite.addListener("Utilities.Class.BaseSpec")

        XmlTest test = new XmlTest(suite);
        test.setName("Omicia OPAL Test");
        test.setPreserveOrder("true");

        /*Create a GROUP*/
        List<String> groupList = new ArrayList<String>();
        if (!GROUPS.equals("")) {
            GROUPS.split(",").each {
                groups -> groupList.add(groups);
            }
            test.setIncludedGroups(groupList);
        }

        /*Create a PACKAGE*/
        List<XmlPackage> packageList = new ArrayList<XmlPackage>();
        if (!PACKAGES.equals("")) {
            PACKAGES.split(",").each {
                packages -> packageList.add(new XmlPackage(packages));
            }
            test.setPackages(packageList);
        }

        /*Create a CLASS and METHODS*/
        XmlClass testClass = new XmlClass();
        if (!CLASSES.equals("") && (!METHODS.equals(""))) {
            List<XmlClass> classList = new ArrayList<XmlClass>();
            CLASSES.split(",").each {
                classes -> testClass.setName(classes)
            }
            /*Include METHODS*/
            if (!METHODS.equals("")) {
                ArrayList<XmlInclude> methodsToRun = new ArrayList<XmlInclude>();
                METHODS.split(",").each {
                    methods -> methodsToRun.add(new XmlInclude(methods));
                }
                testClass.setIncludedMethods(methodsToRun)
            }
            classList.add(testClass);
            test.setXmlClasses(classList)
        }

        /*Create only CLASSES*/
        if (!CLASSES.equals("") && (METHODS.equals(""))) {
            List<XmlClass> classList = new ArrayList<XmlClass>();
            CLASSES.split(",").each {
                classes -> classList.add(new XmlClass(classes));
            }
            test.setXmlClasses(classList);
        }


        File file = new File(testngXMLFilePath);
        FileWriter writer = new FileWriter(file);
        writer.write(suite.toXml());
        writer.close();
        mySuites.add(suite);

        myTestNG.setXmlSuites(mySuites);
        myTestNG.run();
    }

    public static void convertPropertiesToSystemProperties() {
        String project_properties_path = "/src/main/resources/project.properties".replace('/', File.separator)
        String projectPropertiesPath = new File(".").getCanonicalPath() + project_properties_path;
        Properties properties = new Properties()
        properties.load(new FileInputStream(projectPropertiesPath))
        properties.each { key, value ->
            if (System.getProperty(key) == null) {
                System.setProperty(key, value);
            }
        }
        System.setProperty("geb.env", System.getProperty("browser"))
    }
}
