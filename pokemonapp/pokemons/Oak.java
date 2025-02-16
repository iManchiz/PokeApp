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
public class Oak {

    private Integer vida;
    private Integer ataque;
    private Integer defensa;
    private Integer probabilidad;

    public Oak(Integer vida, Integer ataque, Integer defensa) {
        this.vida = vida;
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public Integer getVida() {
        return vida;
    }

    public void setVida(Integer vida) {
        this.vida = vida;
    }

    public Integer getAtaque() {
        return ataque;
    }

    public void setAtaque(Integer ataque) {
        this.ataque = ataque;
    }

    public Integer getDefensa() {
        return defensa;
    }

    public void setDefensa(Integer defensa) {
        this.defensa = defensa;
    }

    public Integer getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(Integer probabilidad) {
        this.probabilidad = probabilidad;
    }

    public void probabilidad() {
        Random random = new Random();
        probabilidad = random.nextInt(1, 11);
    }
}
