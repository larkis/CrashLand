/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package crashland;

import java.util.Scanner;
import java.util.Random;

public class CrashLand
{
    private static final Random rnd = new Random();
    
  	public static void main(String[] args) { // Lande das Flugzeug auf er landebahn
		(new CrashLand()).spielstart();
	}

	public void spielstart() {
		// Startvariablen die vor der Schleife deklariert werden müssen
		int strecke = (20 * (int) ((Math.random() * 7))) + 400;
		boolean ersteRunde = true;
		int fuel = 500;
		int maxwind = 20;
		Scanner usercontrol = new Scanner(System.in);
		do {
			if (ersteRunde == true) {
			    System.out.println("Du bist im Landeanflug bei stürmischen Wetter. Vor dir liegen noch " + strecke + " Meter. Achte auf den Wind! Der Throttle gibt die Geschwindigkeit in m/s an. Du hast noch Sprit für 500m.Du kannst nich schneller als 100m/s oder langsamer als 10m/s fliegen. Und der Sturm wird stärker...!!!");
			    ersteRunde = false;
			} else {
			    System.out.println("Du bist noch "+strecke+"m von dem Aufsetzpunkt entfernt. Du hast noch Sprit für "+fuel+" Meter!");
			}
			System.out.println("Stell den Throttle zwischen 0 und 100");
			int throttle = usercontrol.nextInt();
			if (throttle > 100) {
			    throttle = 100;
			}
			if (throttle < 10 ) {
			    throttle = 10;
			}
			System.out.println("Du fliegst "+throttle+" Meter.");
			strecke = strecke - throttle;
			fuel = fuel - throttle;
			strecke = windeinfluss(maxwind, strecke);
			
			// Test ob Gewinnbedinung oder Sonderfall erfüllt ist
			if (strecke < 0) {
			    System.out.println("Du bist zu weit geflogen und musst noch einmal anfangen.");
			    strecke = (20 * (int) ((Math.random() * 7))) + 300; //Neustart wenn man nicht genau landet.
			}
			if (strecke == 0) {
				System.out.println("Du bist sicher gelandet!");
				usercontrol.close();
			}
			maxwind = maxwind +10; //Erhöhen der maximalen Windstärke
		} while (fuel > 0);
		//Ende der Schleife ohne das die Gewinnbedinung erfüllt wurde
		System.out.println("Du bist abgestürzt!");
		usercontrol.close();
	}
	// Komplexe Operatoren werden in eine eigene Funktion gekapselt
	public int windeinfluss(int maxwind, int strecke) {
	    int wind = (int)(Math.random() * (maxwind - 2) + 2); //Berechnen der Windstärke. Maxwind wird mit jeder Runde höher.
			boolean windrichtung =  rnd.nextBoolean(); // true = Wind von vorne, false = Wind von hinten
			if (windrichtung == true) {
			    System.out.println("Der stürmische Wind drückt dein Flugzeug " + wind + "m zurück!");
			    strecke = strecke + wind;
			    
			} else {
			    System.out.println("Der stürmische Wind drückt dein Flugzeug " + wind + "m vorwärts!");
			    strecke = strecke - wind;
			}
			return strecke;
	}
}