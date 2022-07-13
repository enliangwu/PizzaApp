import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

/**
 * PizzaApp.java
 *
 * This program can let customer to make pizza orders
 *
 * @author Enliang Wu
 */

public class PizzaApp extends Application {
    private Label pizzeriaName;
    private Label sizeLabel;
    private Label veggiesLabel;
    private Label meatsLabel;

    private RadioButton sizeSmall;
    private RadioButton sizeMedium;
    private RadioButton sizeLarge;

    private CheckBox peppers;
    private CheckBox onions;
    private CheckBox mushrooms;
    private CheckBox olives;
    private CheckBox bananaPeppers;

    private CheckBox ham;
    private CheckBox peperoni;
    private CheckBox sausage;
    private CheckBox salami;
    private CheckBox chicken;
    private CheckBox steak;

    private int num = 1;
    private double totalPrice;
    private double price = 0;
    private int count = 0;
    private double tax = 0;
    private ArrayList<String> choice = new ArrayList<>();

    @Override
    public void start( Stage primaryStage ) throws Exception {

        primaryStage.setTitle("PizzaApp");
        this.pizzeriaName = new Label("Leo's Pizza");
        this.sizeLabel = new Label("Size");
        this.veggiesLabel = new Label("Veggies");
        this.meatsLabel = new Label("Meats");

        this.sizeSmall = new RadioButton( "Small" );
        this.sizeMedium = new RadioButton( "Medium" );
        this.sizeLarge = new RadioButton( "Large" );

        this.peppers = new CheckBox( "Peppers" );
        this.onions = new CheckBox( "Onions" );
        this.mushrooms = new CheckBox( "Mushrooms" );
        this.olives = new CheckBox( "Olives" );
        this.bananaPeppers = new CheckBox( "BananaPeppers" );

        this.ham = new CheckBox( "Ham" );
        this.peperoni = new CheckBox( "Peperoni" );
        this.sausage = new CheckBox( "Sausage" );
        this.salami = new CheckBox( "Salami" );
        this.chicken = new CheckBox( "Chicken" );
        this.steak = new CheckBox( "Steak" );

        ToggleGroup radioGroupSize = new ToggleGroup();
        this.sizeSmall.setToggleGroup( radioGroupSize );
        this.sizeMedium.setToggleGroup( radioGroupSize );
        this.sizeLarge.setToggleGroup( radioGroupSize );

        Button ATCButton = new Button( "Add to Cart" );
        ATCButton.setOnAction( new ATCButtonHandler() );
        Button doneButton = new Button( "Done" );
        doneButton.setOnAction( new doneButtonHandler() );

        VBox columnSize = new VBox(10, sizeLabel, sizeSmall, sizeMedium, sizeLarge);
        VBox columnVeggies = new VBox(10, veggiesLabel, peppers, onions,
                mushrooms, olives, bananaPeppers);
        VBox columnMeats = new VBox(10, meatsLabel, ham, peperoni, sausage, salami,
                chicken, steak);

        HBox radioBox = new HBox( 20, columnSize, columnVeggies, columnMeats );

        VBox mainVBox = new VBox( 10, pizzeriaName, radioBox, ATCButton, doneButton );

        mainVBox.setAlignment( Pos.CENTER );
        mainVBox.setPadding( new Insets( 50 ) );
        Scene scene = new Scene( mainVBox );
        primaryStage.setScene( scene );
        primaryStage.show();
    }

    static double priceMeats(double price){
        price += 2;
        return price;
    }
    static double priceVeggies(double price){
        price += 0.5;
        return price;
    }
    static ArrayList<String> addChoice(ArrayList<String> choice,String string){
        choice.add(string);
        return choice;
    }
    private class ATCButtonHandler implements EventHandler<ActionEvent >
    {
        @Override public void handle( ActionEvent event ) {
            if (sizeSmall.isSelected())
            {
                price = 10;
                addChoice(choice,"Small Pizza");
            }
            if (sizeMedium.isSelected())
            {
                price = 11;
                addChoice(choice,"Medium Pizza");
            }
            if (sizeLarge.isSelected())
            {
                price = 12;
                addChoice(choice,"Large Pizza");
            }
            if (peppers.isSelected())
            {
                price = priceVeggies(price);
                addChoice(choice,"Add Peppers");
            }
            if (onions.isSelected())
            {
                price = priceVeggies(price);
                addChoice(choice,"Add Onions");
            }
            if (mushrooms.isSelected())
            {
                price = priceVeggies(price);
                addChoice(choice,"Add Mushrooms");
            }
            if (olives.isSelected())
            {
                price = priceVeggies(price);
                addChoice(choice,"Add Peppers");
            }
            if (bananaPeppers.isSelected())
            {
                price = priceVeggies(price);
                addChoice(choice,"Add Banana Peppers");
            }

            if (ham.isSelected())
            {
                count++;
                price = priceMeats(price);
                addChoice(choice,"Add Ham");
            }
            if (peperoni.isSelected())
            {
                count++;
                price = priceMeats(price);
                addChoice(choice,"Add Peperoni");
            }
            if (sausage.isSelected())
            {
                count++;
                price = priceMeats(price);
                addChoice(choice,"Add Sausage");
            }
            if (salami.isSelected())
            {
                count++;
                price = priceMeats(price);
                addChoice(choice,"Add Salami");
            }
            if (chicken.isSelected())
            {
                count++;
                price = priceMeats(price);
                addChoice(choice,"Add Chicken");
            }
            if (steak.isSelected())
            {
                count++;
                price = priceMeats(price);
                addChoice(choice,"Add Steak");
            }

            if (count > 2)
            {
                price -= count * 0.5;
            }
            //price = Math.round(price * 100.0) / 100.0;
            tax = price * 0.06;
            tax = Math.round(tax * 100.0) / 100.0;
            totalPrice = price + tax;

            //reset
            sizeSmall.setSelected( false );
            sizeMedium.setSelected( false );
            sizeLarge.setSelected( false );
            peppers.setSelected( false );
            onions.setSelected( false );
            mushrooms.setSelected( false );
            olives.setSelected( false );
            bananaPeppers.setSelected( false );
            ham.setSelected( false );
            peperoni.setSelected( false );
            sausage.setSelected( false );
            salami.setSelected( false );
            chicken.setSelected( false );
            steak.setSelected( false );
        }
    }
    private class doneButtonHandler implements EventHandler<ActionEvent > {
        @Override public void handle( ActionEvent event ){
            System.out.print( num );
            num++;
            for (int i = 0; i < choice.size(); i++)
            {
                System.out.println(choice.get(i));
            }
            System.out.printf("Food Total: $" + "%.2f", price);
            System.out.println();
            System.out.println("Tax: $" + tax);
            System.out.println("Final Total: $" + totalPrice);
            System.out.println();
            choice.removeAll(choice);
            price = 0;
            count = 0;
            tax = 0;
        }
    }
    public static void main(String[] args) {
        Application.launch(args);
    }
}

