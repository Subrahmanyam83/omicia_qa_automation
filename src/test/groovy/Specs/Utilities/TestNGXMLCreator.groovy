package Specs.Utilities

import org.testng.TestNG
import org.testng.xml.*

/**
 * Created by E002183 on 5/7/2016.
 */
class TestNGXMLCreator {

    public static void main(String[] args) {
        /* TestNGXMLCreator t = new TestNGXMLCreator();
         t.runTestNGTest()*/

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
        suite.setParallel("Tests");
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

        /*Create a CLASS*/
        XmlClass testClass = new XmlClass();
        if (!CLASSES.equals("")) {
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


        File file = new File(testngXMLFilePath);
        FileWriter writer = new FileWriter(file);
        writer.write(suite.toXml());
        writer.close();
        mySuites.add(suite);

        myTestNG.setXmlSuites(mySuites);
        myTestNG.run();
    }

    public void runTestNGTest() {

        convertPropertiesToSystemProperties()
        String PACKAGES = System.getProperty("package.name")
        String GROUPS = System.getProperty("group.name")
        String CLASSES = System.getProperty("class.name")
        String METHODS = System.getProperty("method.name")

        //Create an instance on TestNG
        TestNG myTestNG = new TestNG();

        //Create an instance of XML Suite and assign a name for it.
        XmlSuite mySuite = new XmlSuite();
        mySuite.setName("OPAL Suite");
        mySuite.setParallel(XmlSuite.ParallelMode.CLASSES);
        mySuite.setThreadCount(10);
        mySuite.addListener("Utilities.Class.BaseSpec")

        //Create an instance of XmlTest and assign a name for it.
        XmlTest myTest = new XmlTest(mySuite);
        myTest.setName("Omicia Test");

        /*Create a GROUP*/
        List<String> groupList = new ArrayList<String>();
        if (!GROUPS.equals("")) {
            GROUPS.split(",").each {
                groups -> groupList.add(groups);
            }
            myTest.setIncludedGroups(groupList);
        }

        /*Create a PACKAGE*/
        List<XmlPackage> packageList = new ArrayList<XmlPackage>();
        if (!PACKAGES.equals("")) {
            PACKAGES.split(",").each {
                packages -> packageList.add(new XmlPackage(packages));
            }
            myTest.setPackages(packageList);
        }

        /*Create a CLASS*/
        if (!CLASSES.equals("")) {
            List<XmlClass> classList = new ArrayList<XmlClass>();
            CLASSES.split(",").each {
                classes -> classList.add(new XmlClass(classes));
            }
            myTest.setXmlClasses(classList);
        }


        List<XmlSuite> mySuites = new ArrayList<XmlSuite>();
        mySuites.add(mySuite);

//Set the list of Suites to the testNG object you created earlier.
        myTestNG.setXmlSuites(mySuites);


        File file = new File(new File(".").getCanonicalPath() + System.getProperty("testng.xml.file.path"));
        if (!file.exists()) {
            file.createNewFile()
        }
        FileWriter writer = new FileWriter(file);
        writer.write(mySuite.toXml());
        writer.close();
        // editXML();

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
