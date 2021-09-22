import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/resources/features/"
        , format = {"json:target/destination/cucumber.json"},
        tags = {"@Github"})
public class CucumberRunner {
}
