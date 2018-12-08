package common.util;

import bulletin.entity.Bulletin;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import student.entity.Student;
import teacher.entity.Teacher;

import java.io.*;
import java.util.Iterator;
import java.util.List;

public class ExpertUtil {

    /**
     * 将JSON数据转为Excel
     * @param name
     * @param jsonArray
     * @param tbName
     */
    public File exportExcel(String name,JSONArray jsonArray,String tbName) {

        // 创建一个新的工作簿
        XSSFWorkbook workbook = new XSSFWorkbook();
        // 新建表名
        XSSFSheet sheet = workbook.createSheet(name);
        // 创建第一行
        XSSFRow row = sheet.createRow(0);
        int index = 0;
        if (tbName.equals("")||tbName == null) {
            tbName = "excel.xls";
        }

        // 打开要目标Excel文件
        File file = new File(tbName);
        if (!file.exists()) {
            file.mkdir();
        }

        JSONObject first = jsonArray.getJSONObject(0);

        // 得到第一项的key集合
        Iterator<String> iterator = first.keys();

        // 遍历key集合
        while (iterator.hasNext()) {

            // 得到key
            String key = iterator.next();
            String value = first.getString(key);

            // 创建列名
            XSSFCell cell = row.createCell(index);
            cell.setCellValue(key);
            index++;
        }

        for (int i = 0; i < jsonArray.size(); i++) {
            row = sheet.createRow(i + 1);

            JSONObject jsonObject = jsonArray.getJSONObject(i);
            iterator = jsonObject.keys();

            index = 0;
            while (iterator.hasNext()) { // 遍历key集合
                String key1 = iterator.next(); // 得到key
                String value = jsonObject.getString(key1);
                System.out.println(value);
                XSSFCell cell = row.createCell(index);
                cell.setCellValue(value);
                index++;
            }
        }

        try {
            // 目标文件存储
            String tmpPath = tbName + "\\" + name + ".xlsx";

            OutputStream outputStream = new FileOutputStream(tmpPath);
            workbook.write(outputStream);
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return file;
    }

    /**
     * 写入csv文件
     * @param allKeyNames
     * @param allValues
     * @param fileName
     * @return
     */
    public File getCsvFile(String allKeyNames, String allValues, String fileName){

        File file = new File(fileName);

        FileOutputStream fileOutputStream = null;
        OutputStreamWriter outputStreamWriter = null;
        BufferedWriter bufferedWriter = null;

        try {
            fileOutputStream = new FileOutputStream(file);
            outputStreamWriter = new OutputStreamWriter(fileOutputStream);
            bufferedWriter = new BufferedWriter(outputStreamWriter);
            // 先输出列名
            bufferedWriter.write(allKeyNames+"\r"+allValues);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bufferedWriter != null){
                    bufferedWriter.close();
                }
                if (outputStreamWriter != null){
                    outputStreamWriter.close();
                }
                if (fileOutputStream != null){
                    fileOutputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        return file;
    }
}
