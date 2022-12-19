package com.miracle.poc.config;


import com.miracle.poc.model.EmployeeInfo;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {

    public static String TYPE ="text/csv";
    static String[] HEADERs = { "empid","empName","empPhone","empEmail","empSatus","empaddress","empRole","empSkill"};

    public static boolean hasCSVFormat(MultipartFile file){

        if(TYPE.equals(file.getContentType()) || file.getContentType().equals("application/vnd.ms-excel")) {

            return true;
        }
        return false;
    }

    public static List<EmployeeInfo> csvToTutorials(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<EmployeeInfo> employeeInfoList = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                EmployeeInfo employeeInfo = new EmployeeInfo(
                        Integer.parseInt(csvRecord.get("empid")),
                        csvRecord.get("empname"),
                        csvRecord.get("empphone"),
                        csvRecord.get("empemail"),
                        csvRecord.get("empstatus"),
                        csvRecord.get("empaddress"),
                        csvRecord.get("emprole"),
                        csvRecord.get("empskill")
                );

                employeeInfoList.add(employeeInfo);
            }

            return employeeInfoList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream tutorialsToCSV(List<EmployeeInfo> employeeInfoList) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (EmployeeInfo employeeInfo : employeeInfoList) {
                List<String> data = Arrays.asList(
                        String.valueOf(employeeInfo.getEmpid()),
                        employeeInfo.getEmpname(),
                        employeeInfo.getEmpphone(),
                        employeeInfo.getEmpemail(),
                        employeeInfo.getEmpaddress(),
                        employeeInfo.getEmprole(),
                        employeeInfo.getEmpskill()
                        );



                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
        }
    }
}