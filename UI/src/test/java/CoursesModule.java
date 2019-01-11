import org.testng.annotations.Test;

public class CoursesModule extends Commons {


    @Test
    public void moduleCheck() throws InterruptedException {

        clickByCss("a.fa-book");
        verifyTitle("Browse All Courses | IconACADEME" );
        verifyURL("https://www.iconacademe.com/courses/");
        sleepFor(2);
        goBackToHomeWindow();



    }
}
