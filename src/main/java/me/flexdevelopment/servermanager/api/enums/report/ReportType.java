package me.flexdevelopment.servermanager.api.enums.report;

public enum ReportType {

    HACKES("Hackes", "Report een speler voor hackes", null, "speed", "killaura");


    private String reportTypeName, reportDescription, reportFormat;
    private String[] types;

    ReportType(String reportTypeName, String reportDescription, String reportFormat, String... types) {
        this.reportTypeName = reportTypeName;
        this.reportDescription = reportDescription;
        this.reportFormat = reportFormat;
        this.types = types;
    }

    public String getReportTypeName() {
        return reportTypeName;
    }

    public String getReportDescription() {
        return reportDescription;
    }

    public String getReportFormat() {
        return reportFormat;
    }

    public String[] getTypes() {
        return types;
    }
}
