/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

/**
 *
 * @author Enforcer
 */
public class Woord {
    private final String woord;
    private final String definitie;
    private final String vertaling;

    public Woord(String woord, String definitie, String vertaling) {
        this.woord = woord;
        this.definitie = definitie;
        this.vertaling = vertaling;
    }

    public String getWoord() {
        return woord;
    }

    public String getDefinitie() {
        return definitie;
    }

    public String getVertaling() {
        return vertaling;
    }
    
}
