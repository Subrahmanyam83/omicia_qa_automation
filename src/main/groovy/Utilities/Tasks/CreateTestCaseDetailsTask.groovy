package Utilities.Tasks

import org.apache.poi.ss.usermodel.*
import org.apache.poi.xssf.usermodel.XSSFColor
import org.apache.poi.xssf.usermodel.XSSFWorkbook
import org.testng.ITestContext
import org.testng.ITestListener
import org.testng.ITestResult
import org.testng.SkipException

/**
 * Created by E002183 on 5/21/2016.
 */
class CreateTestCaseDetailsTask implements ITestListener {

    private final String FILE_PATH = "./opal-test-cases-details.xlsx";
    List testCases = new ArrayList();

    private String testCaseName;
    private String[] group;
    private String className;
    private String description;

    public CreateTestCaseDetailsTask(String className, String testCaseName, String description, String[] group) {
        this.className = className;
        this.testCaseName = testCaseName;
        this.description = description;
        this.group = group;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTestCaseName() {
        return testCaseName;
    }

    public void setTestCaseName(String className) {
        this.testCaseName = testCaseName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String[] getGroup() {
        return group;
    }

    public void setGroup(String[] group) {
        this.group = group;
    }

    @Override
    void onTestStart(ITestResult result) {
        if (result.getMethod().description.equals(null)) {
            result.getMethod().description = ""
        }

        testCases.add(new CreateTestCaseDetailsTask(result.getMethod().getRealClass().name,
                result.getName(),
                result.getMethod().description,
                result.getMethod().groups));
        throw new SkipException("Testing skip.");
    }

    @Override
    void onTestSuccess(ITestResult result) {
    }

    @Override
    void onTestFailure(ITestResult result) {
    }

    @Override
    void onTestSkipped(ITestResult result) {
    }

    @Override
    void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    void onStart(ITestContext context) {
    }

    @Override
    void onFinish(ITestContext context) {
        writeStudentsListToExcel(testCases);
    }

    public void writeStudentsListToExcel(List<CreateTestCaseDetailsTask> tCases) {

        Workbook workbook = new XSSFWorkbook();
        Sheet testCasesSheet = (Sheet) workbook.createSheet("Students");

        testCasesSheet.createRow(0).createCell(0).setCellValue("SNo");
        testCasesSheet.getRow(0).createCell(1).setCellValue("PACKAGE-NAME")
        testCasesSheet.getRow(0).createCell(2).setCellValue("CLASS-NAME")
        testCasesSheet.getRow(0).createCell(3).setCellValue("TEST-CASE-NAME")
        testCasesSheet.getRow(0).createCell(4).setCellValue("TEST-CASE-DESCRIPTION")
        testCasesSheet.getRow(0).createCell(5).setCellValue("TEST-CASE-GROUP")

        /*Set the Row Headers Text as BOLD*/
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style.setFont(font);

        /*Set the Foreground Color of the First Header Row and Alignment*/
        XSSFColor grey = new XSSFColor(new java.awt.Color(176, 224, 230));
        style.setFillForegroundColor(grey)
        style.setFillPattern(CellStyle.SOLID_FOREGROUND)
        style.setAlignment(CellStyle.ALIGN_CENTER)
        style.setWrapText(true);

        /*Apply the formatting to each cell of the First ow*/
        for (int i = 0; i < testCasesSheet.getRow(0).getLastCellNum(); i++) {
            testCasesSheet.getRow(0).getCell(i).setCellStyle(style);
        }

        int rowIndex = 1;
        for (CreateTestCaseDetailsTask testCase : tCases) {
            Row row = testCasesSheet.createRow(rowIndex++);
            int cellIndex = 0;


            String className = testCase.getClassName().split("\\.")[(testCase.getClassName().split("\\.").size()) - 1];
            String packageName = testCase.getClassName().split("\\." + className)[0]

            row.createCell(cellIndex++).setCellValue(rowIndex - 1);
            row.createCell(cellIndex++).setCellValue(packageName);
            row.createCell(cellIndex++).setCellValue(className);
            row.createCell(cellIndex++).setCellValue(testCase.getTestCaseName());
            row.createCell(cellIndex++).setCellValue(testCase.getDescription());
            row.createCell(cellIndex++).setCellValue(testCase.getGroup().toArrayString());

            /*Wrap Text in the Desciption Column*/
            CellStyle cs = workbook.createCellStyle();
            cs.setWrapText(true);
            row.getCell(4).setCellStyle(cs);

            /*Setting the Column Sno Values Center Alligned*/
            CellStyle firstCellStyle = workbook.createCellStyle();
            firstCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
            row.getCell(0).setCellStyle(firstCellStyle);
        }
        try {
            /*Setting the Width of each cell*/
            testCasesSheet.setColumnWidth(0, 2000)
            testCasesSheet.setColumnWidth(1, 6000)
            testCasesSheet.setColumnWidth(2, 7000)
            testCasesSheet.setColumnWidth(3, 10000)
            testCasesSheet.setColumnWidth(4, 12000)
            testCasesSheet.setColumnWidth(5, 8000)

            FileOutputStream fos = new FileOutputStream(FILE_PATH);
            workbook.write(fos);
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
