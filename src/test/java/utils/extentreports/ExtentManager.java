package utils.extentreports;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {
    public static final ExtentReports extentReports = new ExtentReports();

    public synchronized static ExtentReports createExtentReports() {
    	String timestamp = new SimpleDateFormat("yyyy.MM.dd.hh.mm.ss").format(new Date());
		String reportName = "Test-report-" + timestamp + ".html";
//		sparkReporter = new ExtentSparkReporter(".\\reports\\" + reportName);
//		sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir")+"/reports/" + reportName);
		ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/"+ reportName);
//      ExtentSparkReporter reporter = new ExtentSparkReporter("./extent-reports/extent-report.html");
        reporter.config().setReportName("Sample Extent Report");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("Computer Name", "Mac");
        extentReports.setSystemInfo("Application", "SampleWeb Application");
        extentReports.setSystemInfo("Environment", "QA");
        extentReports.setSystemInfo("Tester Name", "Satikant");
        extentReports.setSystemInfo("Operating System", System.getProperty("os.name"));
        extentReports.setSystemInfo("User Name", System.getProperty("user.name"));
        extentReports.setSystemInfo("Application name", "PayPlus");
        return extentReports;
    }
}
