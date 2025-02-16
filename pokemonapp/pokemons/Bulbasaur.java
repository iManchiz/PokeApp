/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemonapp.pokemons;

import java.util.Random;
import pokemonapp.ui.frameCombate;

/**
 *
 * @author SergioJimenezDev
 */
public class Bulbasaur extends Pokemon {

    private Integer probabilidad;

    public Bulbasaur() {
        super(100, 10, 5);
    }

    public Integer getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Integer probabilidad) {
        this.probabilidad = probabilidad;
    }

    public void latigoCepa(Oak oak) {
        oak.setVida(oak.getVida() + oak.getDefensa() - getAtaque());
    }

    public void defensaFerrea() {
        Random random = new Random();
        probabilidad = random.nextInt(1, 3);
    }

    public void drenaje() {
        Random random = new Random();
        probabilidad = random.nextInt(1, 11);
    }

}
