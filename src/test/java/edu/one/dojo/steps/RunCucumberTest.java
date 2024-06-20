package edu.one.dojo.steps;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import net.masterthought.cucumber.Reportable;
import org.junit.AfterClass;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/edu/one/dojo/features",
        glue = "edu.one.dojo.steps",
        plugin = {"json:target/cucumber.json", "pretty", "html:target/site/cucumber.html"},
        dryRun = true,
        stepNotifications = true,
        publish = false,
        name = "order",
        tags = "@message or @order",
        monochrome = true,
        snippets = CucumberOptions.SnippetType.CAMELCASE
)
public class RunCucumberTest {

    @AfterClass
    public static void generateReport() {
        File reportOutputDirectory = new File("target");
        List<String> jsonFiles = new ArrayList<>();
        jsonFiles.add("target/cucumber.json");

        String buildNumber = "1";
        String projectName = "cucumberProject";

        Configuration configuration = new Configuration(reportOutputDirectory, projectName);
        // optional configuration - check javadoc
        configuration.setBuildNumber(buildNumber);


        // addidtional metadata presented on main page
        configuration.addClassifications("Platform", "Windows");
        configuration.addClassifications("Browser", "Firefox");
        configuration.addClassifications("Branch", "release/1.0");

        ReportBuilder reportBuilder = new ReportBuilder(jsonFiles, configuration);
        Reportable result = reportBuilder.generateReports();
        // and here validate 'result' to decide what to do
        // if report has failed features, undefined steps etc

    }
}