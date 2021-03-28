package jp.co.suyama.menu.deliver.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MenuComposition {

    private int compId;

    private String material;

    private int amount;

    private double waste;

    private double energy;

    private double protein;

    private double lipid;

    private double carbohydrate;

    private double calcium;

    private double iron;

    private double cholesterol;

    private double dietaryFiber;

    private double saltEquivalent;
}
