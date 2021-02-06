package com.proapps.mobile.support;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//Features is the folder that contains your feature files
//Glue is the package that contains your step definition classes
//Pretty is the report format, you can add or edit
@CucumberOptions(
      strict = true,
      plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
      features = "src/test/resources/features",
      glue = {"com.proapps.mobile.steps", "com.proapps.mobile.support"}
)

public class RunnerTest extends AbstractTestNGCucumberTests {

}
