package org.example;
import com.zenrows.data.PokemonProduct;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        // Press Alt+Enter with your caret at the highlighted text to see how
        // IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        // Press Shift+F10 or click the green arrow button in the gutter to run the code.
        for (int i = 1; i <= 5; i++) {

            // Press Shift+F9 to start debugging your code. We have set one breakpoint
            // for you, but you can always add more by pressing Ctrl+F8.
            System.out.println("i = " + i);

        }
        // initializing the HTML Document page variable
        Document doc;
        try {
            //fetching the target website
            doc = Jsoup.connect("https://scrapeme.live/shop").get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try {
            doc = Jsoup
                    .connect("https://scrapeme.live/shop")
                    .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/108.0.0.0 Safari/537.36")
                    .header("Accept-Language", "*")
                    .get();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //initializing the list of Java object to store
        //the scraped data
        List<com.zenrows.data.PokemonProduct> pokemonProducts = new ArrayList<>();

        //retrieving the list of product HTML elements
        Elements products = doc. select("li.product");



        //iterating over the list of HTML products
        for (Element product: products) {
            com.zenrows.data.PokemonProduct pokemonProduct = new PokemonProduct();

            //extracting the data of interest from the product HTML element
            //and storing it in pokemonProduct
            pokemonProduct.setUrl(product.selectFirst("a").attr("href"));
            pokemonProduct.setImage(product.selectFirst("img").attr("src"));
            pokemonProduct.setName(product.selectFirst("h2").text());
            pokemonProduct.setPrice(product.selectFirst("span").text());

            //adding pokemonProduct to the list of the scraped products
            pokemonProducts.add(pokemonProduct);
        }
        pokemonProducts.toString();
        System.out.println(pokemonProducts);
    }
}