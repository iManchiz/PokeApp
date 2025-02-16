/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package pokemonapp.pokemons;

import pokemonapp.ui.frameCombate;


/**
 *
 * @author SergioJimenezDev
 */
public class Pokemon {
    private Integer vida;
    private Integer ataque;
    private Integer defensa;

    public Pokemon(Integer vida, Integer ataque, Integer defensa) {
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

    public void setAtaque(Integer fuerza) {
        this.ataque = fuerza;
    }

    public Integer getDefensa() {
        return defensa;
    }

    public void setDefensa(Integer defensa) {
        this.defensa = defensa;
    }
    
    
}
