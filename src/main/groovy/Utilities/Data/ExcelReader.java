package Utilities.Data;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.formula.functions.Column;
import org.apache.poi.ss.usermodel.Cell;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Calendar;


/**
 * Created by in02183 on 2/8/2016.
 */
public class ExcelReader{

    public String path;
    public FileInputStream fis = null;
    public FileOutputStream fileOut = null;

    private HSSFWorkbook workbook = null;
    private HSSFSheet sheet = null;
    private HSSFRow row = null;
    private Column col = null;
    private HSSFCell cell = null;
    private String sheetName;

    public ExcelReader(String path,String sheetName) {
        this.path = path;
        try {
            fis = new FileInputStream(path);
            workbook = new HSSFWorkbook(fis);
            sheet = workbook.getSheetAt(0);
            this.sheetName = sheetName;
            fis.close();
        } catch (Exception e) {
        }

    }

    /*Returns the row count in a sheet*/
    public int getRowCount(String sheetName) {
        System.out.println("Im in Exc elcel readder getrow count class");
        int index = workbook.getSheetIndex(sheetName);
        if (index == -1)
            return 0;
        else {
            sheet = workbook.getSheetAt(index);
            int number = sheet.getLastRowNum() + 1;
            return number;
        }
    }

    public String getData(String rowName,String colName) throws Throwable{
        int rowNum=-1;
        try{
            int index = workbook.getSheetIndex(sheetName);
            int rowNumber = -1;
            int colNumber = -1;
            boolean flag = false;
            if (index == -1)
                return "";
            sheet = workbook.getSheetAt(index);
            for (int i = 2; i < sheet.getPhysicalNumberOfRows(); ) {
                try
                {
                    row = sheet.getRow(i);
                    if (row.getCell(0).toString().equalsIgnoreCase(rowName)) {
                        rowNumber = i;
                        break;
                    }
                    i=i+2;
                }
                catch(NullPointerException e)
                {
                    continue;
                }
            }
            row = sheet.getRow(rowNumber-1);
            for (int j = 0; j <=row.getPhysicalNumberOfCells(); j++) {
                try
                {
                    if (row.getCell(j).toString().equalsIgnoreCase(colName)) {
                        colNumber = j;
                        break;
                    }
                }
                catch(NullPointerException e)
                {
                    continue;
                }
            }
            if(colNumber==-1)
            {
            }
            row = sheet.getRow(rowNumber);
            if (row == null)
                return "";
            cell = row.getCell(colNumber);
            if (cell == null)
                return "";

            if (cell.getCellType() == Cell.CELL_TYPE_STRING)
                return cell.getStringCellValue().trim();
            else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC
                    || cell.getCellType() == Cell.CELL_TYPE_FORMULA) {

                String cellText = String.valueOf(cell.getNumericCellValue())
                        .replaceFirst(".0", "");

                if (HSSFDateUtil.isCellDateFormatted(cell)) {
                    double d = cell.getNumericCellValue();
                    Calendar cal = Calendar.getInstance();
                    cal.setTime(HSSFDateUtil.getJavaDate(d));
                    cellText = (String.valueOf(cal.get(Calendar.YEAR))).substring(2);
                    cellText = cal.get(Calendar.MONTH) + 1 + "/" + cal.get(Calendar.DAY_OF_MONTH) + "/" + cellText;
                }
                return cellText;
            } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK)
                return "";
            else
                return String.valueOf(cell.getBooleanCellValue()).trim();
        }
        catch(Exception e){
            return "";
        }
    }

    public String[][] getDataArrayBySheet(String sheetName) throws Throwable{
        int rows=-1;
        int columns=-1;
        String[][] data=null;
        try{

            int index = workbook.getSheetIndex(sheetName);
            boolean flag = false;
            if (index == -1)
            {
                return null;
            }
            sheet = workbook.getSheetAt(index);
            rows=sheet.getPhysicalNumberOfRows();
            columns=sheet.getRow(0).getLastCellNum();
            data=new String[rows-1][columns];
            for (int i = 1; i < rows; i++) {
                for(int j=0;j<columns;j++)
                {
                    try
                    {
                        row = sheet.getRow(i);
                        data[i-1][j]=row.getCell(j).toString();
                    }
                    catch(NullPointerException e)
                    {
                        break;
                    }
                }
            }
            return data;
        }

        catch(Exception e)
        {
            return null;
        }
    }
}