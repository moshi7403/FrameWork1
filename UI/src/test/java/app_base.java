import org.apache.xpath.operations.String;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class app_base extends Commons {
    public void addToCartbyCoupon(){
        clickByCss("#post-2307 > div > section > div.vc_row.wpb_row.vc_row-fluid.wpb_animate_when_almost_visible.wpb_fadeIn.fadeIn.wpb_start_animation.animated > div > div > div > div.woocommerce.columns-4 > ul > li.post-6225.product.type-product.status-publish.has-post-thumbnail.product_cat-it-courses.user-has-not-earned.instock.featured.virtual.shipping-taxable.purchasable.product-type-simple > a.woocommerce-LoopProduct-link > img");
        clickByCss(".single_add_to_cart_button.button.alt");
        clickByCss("#woo-content > div.woocommerce-message > a");
    }
    public void navitageToStoreFromHome(){
        clickByCss("#menu-item-2348 > a");
        verifyTitle("https://www.iconacademe.com/store/");

    }



    public void listOfElements (){

        List<WebElement> links = driver.findElements(By.tagName("a"));

        System.out.println(links.size());

        for (int i = 1; i<=links.size(); i=i+1)

        {

            System.out.println(links.get(i).getText());

        }
    }
}
