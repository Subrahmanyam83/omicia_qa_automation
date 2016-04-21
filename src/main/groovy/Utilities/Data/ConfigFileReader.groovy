package Utilities.Data

import org.apache.log4j.Logger

/**
 * Created by in02183 on 2/3/2016.
 */
class ConfigFileReader {

    private static final Logger LOG = Logger.getLogger(ConfigFileReader.class);
    private static baseDir = System.getProperty("user.dir");

    public synchronized static write(String keyName, String value) {
        OutputStream output = null;
        try {
            boolean isKeyPresent = false;
            Properties properties = new Properties();
            output = new FileOutputStream(System.getProperty(baseDir) + "\\src\\main\\resources\\project.properties");
         /*   properties.each { prop->
                if(prop.key.equals(keyName)){
                    System.setProperty()
                }
            }
            if(isKeyPresent.equals(false)){

            }*/
            properties.setProperty(keyName, value);
            properties.store(output);
        }
        catch (Exception e) {
            // TODO: Write Logs
        }
        finally {
            if (output != null) {
                try {
                    output.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    public synchronized static String read(String key){
        Properties properties = new Properties();
        InputStream input = null;

        try{
            input = new FileInputStream(System.getProperty(baseDir) + "\\src\\main\\resources\\project.properties");
            properties.leftShift(input);
            return properties.getProperties(key);
        }
        catch (Exception e){
            // TODO: Write Logs
        }
        finally {
            if (input != null) {
                try {
                    input.close();
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
