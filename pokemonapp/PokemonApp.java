package pokemonapp;

import java.util.Random;
import javax.swing.JOptionPane;
import pokemonapp.ui.frameCombate;
import pokemonapp.pokemons.*;
import java.lang.Math;

import javax.swing.SwingUtilities;

public class PokemonApp {

    public static final Integer DATAQUEBULBASAUR = 10;
    public static final Integer DATAQUEOAK = 15;
    public static Integer TURNO = 1;
    public static Boolean DANZA_ESPADA_ACTIVADO = false;
    public static Integer DURACION_DANZA_ESPADA = 0;
    public static Boolean MAGIA_DE_ATAQUE_ACTIVADO = false;
    public static Integer DURACION_MAGIA_DE_ATAQUE = 0;
    public static Boolean ATAQUE_FALLADO = false;
    public static Boolean BLOQUEO = false;
    public static Boolean BULBASAUR_ATURDIDO = false;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            frameCombate panelInicial = new frameCombate();
            panelInicial.setSize(1100, 750);
            panelInicial.setVisible(true);
            panelInicial.setResizable(false);
            panelInicial.setLocationRelativeTo(null);

            Bulbasaur bulbasaur = new Bulbasaur();
            Oak oak = new Oak(100, 10, 5);
            bulbasaur.setProbabilidad(0);
            oak.setProbabilidad(0);

            // ------------------------- LÁTIGO CEPA -------------------------
            panelInicial.setBtnHabilidad1ActionListener(evt -> {
                iniciarTurno(panelInicial);
                panelInicial.getTxtArea().append("═════ BULBASAUR ═════ \n");
                sumarAtaqueBulbasaur(bulbasaur, DANZA_ESPADA_ACTIVADO, panelInicial);

                // BULBASAUR
                ATAQUE_FALLADO = fallarAtaque();
                if (!BULBASAUR_ATURDIDO) {
                    if (!ATAQUE_FALLADO) {
                        bulbasaur.latigoCepa(oak);
                        panelInicial.getTxtArea().append("¡Bulbasaur usó LÁTIGO CEPA!\n");
                        panelInicial.getTxtArea().append("¡Oak ha recibido " + (bulbasaur.getAtaque() - oak.getDefensa()) + "⚔️ de daño!\n");
                        comprobarVidaOak(oak, bulbasaur, panelInicial);
                    } else {
                        panelInicial.getTxtArea().append("¡Bulbasaur falló el ataque!\n");
                    }
                } else {
                    panelInicial.getTxtArea().append("¡Bulbasaur está aturdido!\n");
                    BULBASAUR_ATURDIDO = false;
                }

                // OAK
                OakAtaque(bulbasaur, oak, panelInicial);

                // SALTO DE LINEA
                panelInicial.getTxtArea().append("\n");

            });

            // ------------------------- DEFENSA FERREA -------------------------
            panelInicial.setBtnHabilidad2ActionListener(evt -> {
                iniciarTurno(panelInicial);
                panelInicial.getTxtArea().append("═════ BULBASAUR ═════ \n");
                sumarAtaqueBulbasaur(bulbasaur, DANZA_ESPADA_ACTIVADO, panelInicial);

                // BULBASAUR
                if (!BULBASAUR_ATURDIDO) {
                    bulbasaur.defensaFerrea();
                    panelInicial.getTxtArea().append("¡Bulbasaur usó BARREA FERREA!\n");
                    if (bulbasaur.getProbabilidad() == 1) {
                        panelInicial.getTxtArea().append("¡Defensa ferrea ha funcionado!, bloquearás el próximo ataque \n");
                        BLOQUEO = true;
                    } else {
                        panelInicial.getTxtArea().append("¡Has fallado defensa ferrea!\n");
                    }
                } else {
                    panelInicial.getTxtArea().append("¡Bulbasaur está aturdido!\n");
                    BULBASAUR_ATURDIDO = false;
                }

                comprobarVidaOak(oak, bulbasaur, panelInicial);

                // OAK
                OakAtaque(bulbasaur, oak, panelInicial);

                // SALTO DE LINEA
                panelInicial.getTxtArea().append("\n");
            });

            // ------------------------- DANZA ESPADA -------------------------
            panelInicial.setBtnHabilidad3ActionListener(evt -> {
                iniciarTurno(panelInicial);
                panelInicial.getTxtArea().append("═════ BULBASAUR ═════ \n");
                sumarAtaqueBulbasaur(bulbasaur, DANZA_ESPADA_ACTIVADO, panelInicial);

                // BULBASAUR
                ATAQUE_FALLADO = fallarAtaque();
                if (!BULBASAUR_ATURDIDO) {
                    if (!ATAQUE_FALLADO) {
                        DANZA_ESPADA_ACTIVADO = true;
                        panelInicial.getTxtArea().append("¡Bulbasaur usó DANZA ESPADA!\n");
                        panelInicial.getTxtArea().append("¡Su ataque aumentará en 3⚔ durante los próximos 3 turnos!\n");
                        comprobarVidaOak(oak, bulbasaur, panelInicial);
                        sumarAtaqueBulbasaur(bulbasaur, DANZA_ESPADA_ACTIVADO, panelInicial);
                    } else {
                        panelInicial.getTxtArea().append("¡Bulbasaur falló el ataque!\n");
                    }
                } else {
                    panelInicial.getTxtArea().append("¡Bulbasaur está aturdido!\n");
                    BULBASAUR_ATURDIDO = false;
                }

                // OAK
                OakAtaque(bulbasaur, oak, panelInicial);

                // SALTO DE LINEA
                panelInicial.getTxtArea().append("\n");
            });

            // ------------------------- DRENAJE -------------------------
            panelInicial.setBtnHabilidad4ActionListener(evt -> {
                iniciarTurno(panelInicial);
                panelInicial.getTxtArea().append("═════ BULBASAUR ═════ \n");
                sumarAtaqueBulbasaur(bulbasaur, DANZA_ESPADA_ACTIVADO, panelInicial);

                // BULBASAUR
                ATAQUE_FALLADO = fallarAtaque();
                if (!BULBASAUR_ATURDIDO) {
                    if (!ATAQUE_FALLADO) {
                        bulbasaur.drenaje();
                        panelInicial.getTxtArea().append("¡Bulbasaur usó DRENAJE!\n");
                        if (bulbasaur.getProbabilidad() >= 7 && bulbasaur.getProbabilidad() <= 10) {
                            bulbasaur.setVida(bulbasaur.getVida() + (bulbasaur.getAtaque() / 2));
                            ajustarVidaSuperior100(bulbasaur, oak);
                            panelInicial.getTxtArea().append("¡Te has curado el 50% de tu daño de ataque! " + "(❤️" + (bulbasaur.getAtaque() / 2) + ")" + "\n");
                        } else if (bulbasaur.getProbabilidad() >= 4 && bulbasaur.getProbabilidad() <= 6) {
                            panelInicial.getTxtArea().append("¡Has fallado el ataque!\n");
                        } else if (bulbasaur.getProbabilidad() >= 2 && bulbasaur.getProbabilidad() <= 3) {
                            bulbasaur.setVida(bulbasaur.getVida() + (bulbasaur.getAtaque()));
                            ajustarVidaSuperior100(bulbasaur, oak);
                            panelInicial.getTxtArea().append("¡Te has curado el 100% de tu daño de ataque! " + "(❤️" + bulbasaur.getAtaque() + ")" + "\n");
                        } else if (bulbasaur.getProbabilidad() == 1) {
                            panelInicial.getTxtArea().append("¡Te has curado el 100% de tu daño de ataque! " + "(❤️" + bulbasaur.getAtaque() + ") y además haces 10⚔️ de daño!" + "\n");
                            bulbasaur.setVida(bulbasaur.getVida() + (bulbasaur.getAtaque()));
                            ajustarVidaSuperior100(bulbasaur, oak);
                            oak.setVida(oak.getVida() + oak.getDefensa() - 10);
                        }
                        comprobarVidaOak(oak, bulbasaur, panelInicial);
                    } else {
                        panelInicial.getTxtArea().append("¡Bulbasaur falló el ataque!\n");
                    }
                } else {
                    panelInicial.getTxtArea().append("¡Bulbasaur está aturdido!\n");
                    BULBASAUR_ATURDIDO = false;
                }

                // OAK
                OakAtaque(bulbasaur, oak, panelInicial);

                // SALTO DE LINEA
                panelInicial.getTxtArea().append("\n");

            });

        });
    }

    // ------------------------- MÉTODOS -------------------------
    public static void OakAtaque(Bulbasaur bulbasaur, Oak oak, frameCombate panelInicial) {

        panelInicial.getTxtArea().append("\n═════ OAK ═════ \n");

        // OBTENER LA PROBABILIDAD
        oak.probabilidad();

        if (!BLOQUEO) {
            // ATAQUE PROBABILIDAD 40% (ATAQUE BÁSICO)
            if (oak.getProbabilidad() >= 7 && oak.getProbabilidad() <= 10) {
                ATAQUE_FALLADO = fallarAtaque();
                if (!ATAQUE_FALLADO) {
                    panelInicial.getTxtArea().append("¡Oak usó PUÑETAZO!\n");
                    panelInicial.getTxtArea().append("¡Bulbasaur ha recibido " + (oak.getAtaque() - bulbasaur.getDefensa()) + "⚔️ de daño!\n");
                    bulbasaur.setVida(bulbasaur.getVida() + bulbasaur.getDefensa() - oak.getAtaque());
                } else {
                    panelInicial.getTxtArea().append("¡Oak falló el ataque!\n");
                }

                // ATAQUE PROBABILIDAD 20% (PUÑO CERTERO)
            } else if (oak.getProbabilidad() >= 4 && oak.getProbabilidad() <= 5) {
                panelInicial.getTxtArea().append("¡Oak usó PUÑETAZO VERDADERO!\n");
                panelInicial.getTxtArea().append("¡Oak no puede fallar el ataque!\n");
                panelInicial.getTxtArea().append("¡Bulbasaur ha recibido " + (oak.getAtaque() - bulbasaur.getDefensa()) + "⚔️ de daño!\n");
                bulbasaur.setVida(bulbasaur.getVida() + bulbasaur.getDefensa() - oak.getAtaque());

                // ATAQUE PROBABILIDAD 10% (HIPNOSIS)
            } else if (oak.getProbabilidad() == 6) {
                ATAQUE_FALLADO = fallarAtaque();
                if (!ATAQUE_FALLADO) {
                    panelInicial.getTxtArea().append("¡Oak usó HIPNOSIS! Bulbasaur queda aturdido y perderá su próximo turno\n");
                    BULBASAUR_ATURDIDO = true;
                } else {
                    panelInicial.getTxtArea().append("¡Oak falló el ataque!\n");
                }

                // ATAQUE PROBABILIDAD 20% (MAGIA CURATIVA)
            } else if (oak.getProbabilidad() >= 2 && oak.getProbabilidad() <= 3) {
                // OBTENER NÚMERO ALEATORIO DE PROBABILIDAD
                Random random = new Random();
                Integer aleatorio = random.nextInt(1, 11);

                ATAQUE_FALLADO = fallarAtaque();
                if (!ATAQUE_FALLADO) {
                    panelInicial.getTxtArea().append("¡Oak usó MAGIA CURATIVA!\n");
                    if (aleatorio >= 7 && aleatorio <= 10) {
                        panelInicial.getTxtArea().append("¡Oak se curó un 100% su daño de ataque!\n");
                        oak.setVida(oak.getVida() + oak.getAtaque());
                        ajustarVidaSuperior100(bulbasaur, oak);
                        panelInicial.getTxtVidaOak().setText(oak.getVida() + "/100");
                    } else if (aleatorio >= 4 && aleatorio <= 6) {
                        panelInicial.getTxtArea().append("¡Oak falló el ataque!\n");
                    } else if (aleatorio >= 2 && aleatorio <= 3) {
                        panelInicial.getTxtArea().append("¡Oak se curó un 100% su daño de ataque y además hizo 10⚔️ de daño!\n");
                        oak.setVida(oak.getVida() + oak.getAtaque());
                        ajustarVidaSuperior100(bulbasaur, oak);
                        bulbasaur.setVida(bulbasaur.getVida() + bulbasaur.getDefensa() - 10);
                        panelInicial.getTxtVidaOak().setText(oak.getVida() + "/100");
                    } else if (aleatorio == 1) {
                        panelInicial.getTxtArea().append("¡Oak se curó un 150% del daño de ataque de Bulbasaur!\n");
                        oak.setVida(oak.getVida() + (int) (Math.round(bulbasaur.getAtaque() * 1.5)));
                        ajustarVidaSuperior100(bulbasaur, oak);
                        panelInicial.getTxtVidaOak().setText(oak.getVida() + "/100");
                    }
                } else {
                    panelInicial.getTxtArea().append("¡Oak falló el ataque!\n");
                }

                // ATAQUE PROBABILIDAD 10% (MAGIA DE ATAQUE)
            } else if (oak.getProbabilidad() == 1) {
                ATAQUE_FALLADO = fallarAtaque();
                if (!ATAQUE_FALLADO) {
                    panelInicial.getTxtArea().append("¡Oak usó MAGIA DE ATAQUE!\n");
                    MAGIA_DE_ATAQUE_ACTIVADO = true;
                } else {
                    panelInicial.getTxtArea().append("¡Oak falló el ataque!\n");
                }
            }
        } else {
            panelInicial.getTxtArea().append("¡Oak falló el ataque por bloqueo de Bulbasaur! 🛡️\n");
        }

        // COMPROBAR SI BULBASAUR SIGUE CON VIDA
        sumarAtaqueOak(oak, MAGIA_DE_ATAQUE_ACTIVADO, panelInicial);
        comprobarVidaBulbasaur(bulbasaur, oak, panelInicial);

        // REINICIAR VARIABLES
        BLOQUEO = false;
    }

    public static void ajustarVidaSuperior100(Bulbasaur bulbasaur, Oak oak) {
        if (bulbasaur.getVida() > 100) {
            bulbasaur.setVida(100);
        }
        if (oak.getVida() > 100) {
            oak.setVida(100);
        }
    }

    public static void comprobarVidaBulbasaur(Bulbasaur bulbasaur, Oak oak, frameCombate panelInicial) {

        int vidaActual = Math.max(0, bulbasaur.getVida());
        panelInicial.getTxtVidaPokemon().setText(vidaActual + "/100");

        if (bulbasaur.getVida() <= 0) {
            panelInicial.getTxtArea().append("\n☠️ BULBASAUR FUERA DE COMBATE ☠️\n");
            inavilidadBotones(panelInicial);
            int opcion = JOptionPane.showConfirmDialog(null, "¡Te han raptado! ¿Reiniciar?", "Fin", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                reiniciarJuego(bulbasaur, oak, panelInicial);
            }
        }
    }

    public static void comprobarVidaOak(Oak oak, Bulbasaur bulbasaur, frameCombate panelInicial) {

        int vidaActual = Math.max(0, oak.getVida());
        panelInicial.getTxtVidaOak().setText(vidaActual + "/100");

        if (oak.getVida() <= 0) {
            panelInicial.getTxtArea().append("\n🔥 OAK FUERA DE COMBATE 🔥\n");
            inavilidadBotones(panelInicial);
            int opcion = JOptionPane.showConfirmDialog(null, "¡Oak ha sido derrotado! ¿Reiniciar?", "Fin", JOptionPane.YES_NO_OPTION);
            if (opcion == JOptionPane.YES_OPTION) {
                reiniciarJuego(bulbasaur, oak, panelInicial);
            }
        }
    }

    public static void iniciarTurno(frameCombate panelInicial) {
        panelInicial.getTxtArea().append("\n====================");
        panelInicial.getTxtArea().append(" TURNO " + TURNO.toString() + " ");
        panelInicial.getTxtArea().append("====================\n\n");
        TURNO++;
    }

    public static void inavilidadBotones(frameCombate panelInicial) {
        panelInicial.getTxtVidaPokemon().setText("0/100");
        panelInicial.getBtnHabilidad1().setEnabled(false);
        panelInicial.getBtnHabilidad2().setEnabled(false);
        panelInicial.getBtnHabilidad3().setEnabled(false);
        panelInicial.getBtnHabilidad4().setEnabled(false);
    }

    public static void sumarAtaqueBulbasaur(Bulbasaur bulbasaur, Boolean activo, frameCombate panelInicial) {
        if (DURACION_DANZA_ESPADA < 3 && activo) {
            bulbasaur.setAtaque(bulbasaur.getAtaque() + 3);
            DURACION_DANZA_ESPADA++;
            panelInicial.getBtnHabilidad3().setEnabled(false);
            panelInicial.getTxtArea().append("EFECTO DANZA ESPADA: Ataque aumentado en 3⚔ (" + bulbasaur.getAtaque() + ") 🔺\n");
        } else {
            bulbasaur.setAtaque(DATAQUEBULBASAUR);
            DANZA_ESPADA_ACTIVADO = false;
            DURACION_DANZA_ESPADA = 0;
            panelInicial.getBtnHabilidad3().setEnabled(true);
        }
    }

    public static void sumarAtaqueOak(Oak oak, Boolean activo, frameCombate panelInicial) {
        if (DURACION_MAGIA_DE_ATAQUE <= 2 && activo) {
            oak.setAtaque(oak.getAtaque() + 3);
            DURACION_MAGIA_DE_ATAQUE++;
            panelInicial.getTxtArea().append("EFECTO MAGIA DE ATAQUE: Ataque aumentado en 3⚔ (" + oak.getAtaque() + ") 🔺\n");
        } else {
            oak.setAtaque(DATAQUEOAK);
            MAGIA_DE_ATAQUE_ACTIVADO = false;
            DURACION_MAGIA_DE_ATAQUE = 0;
        }
    }

    public static boolean fallarAtaque() {
        Integer probabilidad = new Random().nextInt(1, 11);
        return probabilidad <= 1;
    }

    public static void reiniciarJuego(Bulbasaur bulbasaur, Oak oak, frameCombate panel) {
        // Restablecer Pokémon
        bulbasaur.setVida(100);
        bulbasaur.setAtaque(DATAQUEBULBASAUR);
        bulbasaur.setDefensa(5);
        bulbasaur.setProbabilidad(0);

        oak.setVida(100);
        oak.setAtaque(DATAQUEOAK);
        oak.setDefensa(5);
        oak.setProbabilidad(0);

        // Restablecer variables estáticas
        TURNO = 1;
        DANZA_ESPADA_ACTIVADO = false;
        MAGIA_DE_ATAQUE_ACTIVADO = false;
        DURACION_DANZA_ESPADA = 0;
        DURACION_MAGIA_DE_ATAQUE = 0;
        BULBASAUR_ATURDIDO = false;

        // Actualizar UI
        panel.getTxtVidaPokemon().setText("100/100");
        panel.getTxtVidaOak().setText("100/100");
        panel.getTxtArea().setText("");
        panel.getBtnHabilidad1().setEnabled(true);
        panel.getBtnHabilidad2().setEnabled(true);
        panel.getBtnHabilidad3().setEnabled(true);
        panel.getBtnHabilidad4().setEnabled(true);
    }

}
