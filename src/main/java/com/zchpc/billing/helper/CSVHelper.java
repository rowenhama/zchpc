package com.zchpc.billing.helper;

import com.zchpc.billing.model.Accounts;
import org.apache.commons.csv.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CSVHelper {
    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Account_Number", "Stand_Number", "House_Number", "Stand_Size", "Establishment_Year",
                                "Property_Value","Ownership_Form","Meter_Number","Sewer","Water","Meter_Status"};

    public static boolean hasCSVFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<Accounts> csvAccounts(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

            List<Accounts> tutorials = new ArrayList<Accounts>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                Accounts accounts = new Accounts(
                        Long.parseLong(csvRecord.get("Id")),
                        csvRecord.get("Account_Number"),
                        csvRecord.get("Stand_Number"),
                        csvRecord.get("House_Number"),
                        csvRecord.get("Stand_Size"),
                        csvRecord.get("Establishment_Year"),
                        csvRecord.get("Property_Value"),
                        csvRecord.get("Ownership_Form"),
                        csvRecord.get("Meter_Number"),
                        csvRecord.get("Sewer"),
                        csvRecord.get("Water"),
                        csvRecord.get("Meter_Status")
                );

                tutorials.add(accounts);
            }

            return tutorials;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }

    public static ByteArrayInputStream tutorialsToCSV(List<Accounts> accounts1) {
        final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
            for (Accounts accounts : accounts1) {

                List<String> data = Arrays.asList(
                        String.valueOf(accounts.getId()),
                        String.valueOf(accounts.getAccount_number()),
                        String.valueOf(accounts.getStand_number()),
                        String.valueOf(accounts.getHouse_number()),
                        String.valueOf(accounts.getStand_size()),
                        String.valueOf(accounts.getEstablishment_year()),
                        String.valueOf(accounts.getProperty_value()),
                        String.valueOf(accounts.getOwnership_form()),
                        String.valueOf(accounts.getMeter_number()),
                        String.valueOf(accounts.getSewer()),
                        String.valueOf(accounts.getWater()),
                        String.valueOf(accounts.getMeter_status())
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
