import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;

public class main{
	//erstellt Alphabet variable als Array(bereits gefuellt) und als Arraylist
	public static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
	public static ArrayList <Character> alphabetList = new ArrayList<>();
	//erstellt scanner fuer die Auswahl der aktionen
	public static Scanner aktionScanner = new Scanner(System.in);
	
	public static void main(String[] args) {
	//fuellt Alphabet Arraylist mit allen Buchstaben, zum einfacheren durchsuchen
		for(char c : alphabet) {
        	alphabetList.add(c);
        }
	//startet Programm
		start();
	}
	static void start(){
		//Abfrage nach gewuenscher Aktion(caesar Verschluesselung)
		System.out.println("was würden sie gerne tun?\n c=cäsar\n v=Vigenere \n b=bit-Verschlüsselung\n d=diffe-hellmann\n\n x=Programm beenden\n");
		String verschlüsselungWahl = aktionScanner.nextLine(); //input fuer Aktion
		switch(verschlüsselungWahl) {
		case "c":
			caesarStart();
			break;
		case "v":
			vigenereStart();
			break;
		case "b":
			bitStart();
			break;
		case "d":
			diffieHellmann();
			break;
		case "x":
			aktionScanner.close();
			System.out.print("Programm erfolgreich beendet");
			System.exit(0);
		default://wenn die Eingabe nicht mit einer Option uebereinstimmt
			start();
			System.out.println("bitte geben sie eine gültige Eingabe ein");
			break;
		}
		System.out.print("\n"); //fuegt neue Zeile zur besseren Uebersicht ein
	}
	static void caesarStart() {
		//Abfrage nach gewuenscher Aktion(caesar Verschluesselung)
		System.out.println("was würden sie gerne tun?\n v=verschlüsseln\n e=entschlüsseln \n b=brute-force Angriff\n\n x=Programm beenden\n");
		String aktion = aktionScanner.nextLine();
		switch(aktion) {
		case "v":
			caeserchiffrieren();
			break;
		case "e":
			caeserdechiffrieren();
			break;
		case "b":
			bruteForce();
			break;
		case "x":
			start();
		default:
			System.out.println("gibt's nicht");
			break;
		}
		//startet Abfrage neu
		System.out.print("\n");
		caesarStart();
    }
    static void vigenereStart(){
		//Abfrage nach gewuenscher Aktion(vigenere Verschluesselung)
			System.out.println("vigenere-Verschlüsselung");
			System.out.println("was würden sie gerne tun?\n v=verschlüsseln\n e=entschlüsseln \n\n x=Programm beenden\n");
			String aktion = aktionScanner.nextLine();
			switch(aktion) {
			case "v":
				vigenereChiffrieren();
				break;
			case "e":
				vigenereDechiffrieren();
				break;
			case "x":
				start();
			default:
				System.out.println("bitte geben sie eine gültige Eingabe ein");
				break;
			}
			//startet Abfrage neu
			System.out.print("\n");
			vigenereStart();
		}

	static void caeserchiffrieren(){
    	//erstellt scanner
    	Scanner scanner = new Scanner(System.in);
    	
    	//legt variablen fest
        char[] klarTextArray;
        int caeserNum;
        ArrayList <Character> chiffreList = new ArrayList<>();
        
        System.out.println("geben sie den zu verschlüsselnden Text ein");
        klarTextArray = textInput(scanner);
        //fragt nach der Caeser Zahl und speichert sie als integer
        System.out.println("geben sie die gewünschte Caesar Zahl ein: ");
        caeserNum = Math.abs(scanner.nextInt());
        
        //verschluesselt den text
        for(char c : klarTextArray) {
        	//überprüft ob zeichen c gültig ist
        	if (alphabetList.contains(c)) {
        	int index = (alphabetList.indexOf(c)+caeserNum) % alphabet.length; //findet den Index des verschluesselten zeichens in alphabet[]
        	//System.out.println(index);
        	char e = alphabetList.get(index % alphabet.length); //verschluesseltes Zeichen
        	chiffreList.add(e);//fuegt verschluesseltes Zeichen hinzu
        	}
        	//teilt nutzer mit, dass zeichen c nicht gültig ist und beendet Programm 
        	else {
        		System.out.printf("'%c' kann nicht verschluesselt werden \n", c);
        		chiffreList.add(c);
        	}
        }
        //wandelt arraylist in array um
        char[] chiffreArray = new char[klarTextArray.length];
        for(int i = 0; i < klarTextArray.length; i++) {
        	chiffreArray[i] = chiffreList.get(i);
        }
        //wandelt array in String um
        String chiffre = new String(chiffreArray);
        System.out.println("\n"+chiffre);
    	

    }
    static char[] textInput(Scanner scanner) {		//fragt nach String input und wandelt in char array um
        String klarText = scanner.nextLine().toLowerCase();
        if(klarText.contains(" ")) {
        	System.out.println("alle Leerzeichen wurden entfernt");
        }
        klarText = klarText.replaceAll(" ", "");
        char[] klarTextArray = klarText.toCharArray();
    	return klarTextArray;
    }
    static void caeserdechiffrieren() {
    	//erstellt scanner
    	Scanner scanner = new Scanner(System.in);
    	//legt variablen fest
        char[] chiffreArray;
        int caeserNum;
        ArrayList <Character> chiffreList = new ArrayList<>();
        
        
        chiffreArray = chiffre(scanner);
        //fragt nach der Caeser Zahl und speichert sie als integer
        System.out.println("geben sie die gewünschte Caesar Zahl ein: ");
        caeserNum = Math.abs(scanner.nextInt());
        
        //verschluesselt den text
        for(char c : chiffreArray) {
        	//überprüft ob zeichen c gültig ist
        	if (alphabetList.contains(c)) {
        	int index = (alphabetList.indexOf(c)-caeserNum) % alphabet.length; //berechnet den Index des verschluesselten zeichens in alphabet[]
        	while (index<0) {
        		index +=alphabet.length;
        	}
        	//System.out.println(index);
        	char e = alphabetList.get(index % alphabet.length); //verschluesseltes Zeichen
        	chiffreList.add(e);//fuegt verschluesseltes Zeichen hinzu
        	}
        	//teilt nutzer mit, dass zeichen c nicht gültig ist und beendet Programm 
        	else {
        		System.out.printf("'%c' kann nicht entschluesselt werden \n", c);
        		chiffreList.add(c);
        	}
        }
        //wandelt arraylist in array um
        char[] klarTextArray = new char[chiffreArray.length];
        for(int i = 0; i < chiffreArray.length; i++) {
        	klarTextArray[i] = chiffreList.get(i);
        }
        //wandelt array in String um
        String klarText = new String(klarTextArray);
        System.out.println(klarText);
    	
    }
    static char[] chiffre(Scanner scanner) {
    	//fragt nach dem text, welcher verschluesselt werden soll und speichert es zuerst als String und anschliessend als array von zeichen
        System.out.println("geben sie den zu entschlüsselnden Text ein: ");
        String chiffre = scanner.nextLine().toLowerCase();
        if(chiffre.contains(" ")) {
        	System.out.println("alle Leerzeichen wurden entfernt");
        }
        chiffre = chiffre.replaceAll(" ", "");
        char[] chiffreArray = chiffre.toCharArray();
    	return chiffreArray;
    }
    static void bruteForce() {
    //erstellt scanner
	Scanner scanner = new Scanner(System.in);
	
	//legt variablen fest
    char[] chiffreArray;
    ArrayList <Character> klarTextList = new ArrayList<>();
    
    System.out.println("den zu entschluesselnden Text ein");
    String chiffre = scanner.nextLine();
	System.out.println("---------------------------------");
    chiffreArray = chiffre.toCharArray();
    for(int i=0; i<alphabet.length; i++ ) {
    	klarTextList.clear();
    	for(char c : chiffreArray) {
    		if (alphabetList.contains(c)) {
    		int index = (alphabetList.indexOf(c)-i) % alphabet.length; //berechnet den Index des verschluesselten zeichens in alphabet[]
    		while (index<0) {
    			index+=alphabet.length;
    		}
        	char e = alphabetList.get(index % alphabet.length);
        	klarTextList.add(e);
    		}
    		else {
    			klarTextList.add(c);
    		}
    	}
		//wandelt arraylist in array um
        char[] klarTextArray = new char[klarTextList.size()];
        for(int k = 0; k < klarTextList.size(); k++) {
        	klarTextArray[k] = klarTextList.get(k);
        }
        //wandelt array in String umx
        String klarText = new String(klarTextArray);
        System.out.println(i + ". "+ klarText);
    }
    
    //chiffreArray = klarText(scanner);
    }
	
	static void vigenereChiffrieren(){
		Scanner scanner = new Scanner(System.in);
		char[] klarTextArray;
		char[] passwort;
		char[] passwortArray;
		String oneTimePadAuswahl;
		
		System.out.println("mit eigenem Passwort verschlüsseln, drück x");
		System.out.println("mit pseudozufällige generierten one-time-pad verschlüsseln, drück o");
		oneTimePadAuswahl = scanner.nextLine();
		if(!oneTimePadAuswahl.equals("o")) {
			System.out.println("geben sie den zu verschlüsselnden Text ein");
			klarTextArray = textInput(scanner);
			char[] chiffreArray = new char[klarTextArray.length];
			System.out.println("mit welchem Passwort wollen sie den Text verschlüsseln?");
	        passwort = textInput(scanner);
			if(passwort.length >= klarTextArray.length){
				passwortArray = passwort;
			}
			else{
				passwortArray = new char[klarTextArray.length];
				for (int i = 0; i < klarTextArray.length; i++) {
					passwortArray[i] = passwort[i % passwort.length];
				}
			}
			System.out.println(passwortArray);
			for (int i = 0; i < klarTextArray.length; i++) {
				int klarTextBuchstabeIndexInAlphabet = alphabetList.indexOf(klarTextArray[i]);
				int passwortBuchstabeIndexInAlphabet = alphabetList.indexOf(passwortArray[i]);
				chiffreArray[i] = alphabetList.get((klarTextBuchstabeIndexInAlphabet + passwortBuchstabeIndexInAlphabet) % alphabetList.size());
			}
			System.out.println(chiffreArray);
		}
		else {
			Random random = new Random();
			System.out.println("geben sie den zu verschlüsselnden Text ein");
			klarTextArray = textInput(scanner);
			char[] chiffreArray = new char[klarTextArray.length];
			char[] oneTimePadArr = new char[klarTextArray.length];
			for(int i =0; i < klarTextArray.length; i++) {
				int j = random.nextInt(26);
				char c = alphabet[j];
				oneTimePadArr[i] = c;
			}
			System.out.println("one-time-pad:");
			System.out.println(oneTimePadArr);

			for (int i = 0; i < klarTextArray.length; i++) {
				int klarTextBuchstabeIndexInAlphabet = alphabetList.indexOf(klarTextArray[i]);
				int passwortBuchstabeIndexInAlphabet = alphabetList.indexOf(oneTimePadArr[i]);
				chiffreArray[i] = alphabetList.get((klarTextBuchstabeIndexInAlphabet + passwortBuchstabeIndexInAlphabet) % alphabetList.size());
			}
			System.out.println("chiffre:");
			System.out.println(chiffreArray);
		}
		
		
	}
	static void vigenereDechiffrieren(){
		Scanner scanner = new Scanner(System.in);
		char[] chiffreArray;
		char[] passwort;
		char[] passwortArray;

		System.out.println("geben sie den zu entschlüsselnden Text ein");
		chiffreArray = textInput(scanner);
		char[] klarTextArray = new char[chiffreArray.length];
		System.out.println("mit welchem Passwort wollen sie den Text entschlüsseln?:");
        passwort = textInput(scanner);
		if(passwort.length >= chiffreArray.length){
			passwortArray = passwort;
		}
		else{
			passwortArray = new char[chiffreArray.length];
			for (int i = 0; i < chiffreArray.length; i++) {
				passwortArray[i] = passwort[i % passwort.length];
			}
		}
		System.out.println(passwortArray);
		for (int i = 0; i < klarTextArray.length; i++) {
			int chiffreBuchstabeIndexInAlphabet = alphabetList.indexOf(chiffreArray[i]);
			int passwortBuchstabeIndexInAlphabet = alphabetList.indexOf(passwortArray[i]);
			klarTextArray[i] = alphabetList.get(((chiffreBuchstabeIndexInAlphabet - passwortBuchstabeIndexInAlphabet)+alphabetList.size()) % alphabetList.size());
		}
		System.out.println(klarTextArray);
	}
	
	static void bitStart(){
		System.out.println("bit-Verschlüsselung");
			System.out.println("was würden sie gerne tun?\n v=verschlüssel/entschlüsseln \n\n x=Programm beenden\n");
			String aktion = aktionScanner.nextLine();
			switch(aktion) {
			case "v":
				bitChiffrieren();
				break;
			case "x":
				start();
			default:
				System.out.println("gibt's nicht");
				break;
			}
			//startet Programm neu
			System.out.print("\n");
			bitStart();
	}
	static void bitChiffrieren(){
		Charset charset = null;
		Scanner aktionScanner = new Scanner(System.in);
		System.out.println("welchen Zeichensatz würden sie gerne verwenden?\n u=UTF-8 \n a=ASCII\n i=ISO 8859-1\n\n x=Programm beenden\n");
		String aktion = aktionScanner.nextLine();
		switch(aktion) {
		case "u":
			charset = StandardCharsets.UTF_8;
			break;
		case "a":
			charset = StandardCharsets.US_ASCII;
			break;
		case "i":
			charset = StandardCharsets.ISO_8859_1;
			break;
		default:
			System.out.println("gibt's nicht");
			break;
		}
		System.out.print("\n");
		Scanner scanner = new Scanner(System.in);

		char[] klarTextArray;
		char[] passwortArray;
		System.out.println("geben sie den zu verschlüsselnden/entschlüsselnden Text ein: ");
		klarTextArray = textInput(scanner);
		System.out.println("geben sie das gewünschte Passwort ein: ");
		passwortArray = textInput(scanner);
		String klarTextString = new String(klarTextArray);
		String passwortString = null;
		if(passwortArray.length >= klarTextArray.length){ //prüft, ob Passwort gleich lang oder länger als klarText ist
			passwortString = new String(passwortArray);
		}
		else{
			char[] tempPasswortArr = new char[klarTextArray.length]; //wenn Passwort kürzer ist als klarText wird das Passwort der Länge angepasst
			for(int i = 0; i < klarTextArray.length; i++) {
				tempPasswortArr[i] = passwortArray[i % passwortArray.length];
				passwortString = new String(tempPasswortArr);
			}
		}
		
		System.out.println("ab hier kopieren");
		
		byte[] klarTextByteArr = klarTextString.getBytes(charset);
		byte[] passwortByteArr = passwortString.getBytes(charset);
		
		int[] klarTextIntArr = new int[klarTextByteArr.length];
		int[] passwortIntArr = new int[passwortByteArr.length];
		int[] chiffreIntArr = new int[klarTextByteArr.length];
		byte[] chiffreByteArr = new byte[klarTextByteArr.length];
		
		for (int i = 0; i<klarTextByteArr.length; i++) {
			klarTextIntArr[i] = klarTextByteArr[i];
			passwortIntArr[i] = passwortByteArr[i];
			chiffreIntArr[i] = klarTextIntArr[i]^passwortIntArr[i];
			if(chiffreIntArr[i] != 0) {
				chiffreByteArr[i] = (byte)chiffreIntArr[i];
			}
			else {
				chiffreByteArr[i] = 45;
			}
		}
		System.out.println(new String(chiffreByteArr, charset));
		System.out.println("bis hier kopieren");
	}
	static void diffieHellmann() {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Diffie-Hellmann Schlüsselaustausch mit Y^x(mod P)");
		System.out.print("geben sie den Wert für Y ein, Y= " );
		int Y = scanner.nextInt();
		System.out.print("geben sie den Wert für x ein, x= " );
		int x = scanner.nextInt();
		System.out.print("geben sie den Wert für P ein, P= " );
		int P = scanner.nextInt();
		double produkt = Math.pow(Y, x);
		System.out.println(produkt%P);
		start();
	}
}