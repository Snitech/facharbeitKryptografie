import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;
import java.util.Scanner;
import java.util.Random;

        public class Main{
            static Scanner StringScanner = new Scanner(System.in);
            static Scanner intScanner = new Scanner(System.in);
            static Scanner longScanner =new Scanner(System.in);
            public static char[] alphabet = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
            public static char[] morseCode = {};

            public static void main(String[] args) {
                //startet Programm
                start();
            }
            static void start(){
                //Abfrage nach gewuenscher Aktion(caesar Verschluesselung)
                System.out.println("was würden sie gerne tun?\n c=cäsar\n v=Vigenere \n b=bit-Verschlüsselung\n d=diffe-hellmann\n m=morse code\n\n x=Programm beenden\n");
                String verschluesselungWahl = StringScanner.nextLine(); //input fuer Aktion
                switch(verschluesselungWahl) {
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
                    case "m":
                        break;
                    case "x":
                        StringScanner.close();
                        intScanner.close();
                        System.out.print("Programm erfolgreich beendet");
                        System.exit(0);
                    default://wenn die Eingabe nicht mit einer Option uebereinstimmt
                        start();
                        System.out.println("bitte geben sie eine gültige Eingabe ein");
                        break;
                }
                System.out.print("\n"); //fuegt neue Zeile zur besseren Uebersicht ein
            }
            static char[] StringZuArrayInput() {		//fragt nach String input und wandelt in char array um
                String klarText = StringScanner.nextLine().toLowerCase();
                if(klarText.contains(" ")) {
                    System.out.println("alle Leerzeichen wurden entfernt");
                }
                return klarText.toCharArray();
            }
            static int indexInAlphabet(char c){
                for(int i = 0; i<alphabet.length; i++){
                    if (c == alphabet[i]) {
                        return i;
                    }
                }
                return alphabet.length + 1;
            }
            static void caesarStart() {
                //Abfrage nach gewuenscher Aktion(caesar Verschluesselung)
                System.out.println("was würden sie gerne tun?\n v=verschlüsseln\n e=entschlüsseln \n b=brute-force Angriff\n\n x=Programm beenden\n");
                String aktion = StringScanner.nextLine();
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
            static void caeserchiffrieren(){
                //legt variablen fest
                char[] klarTextArray;
                int caeserNum;

                System.out.println("geben sie den zu verschlüsselnden Text ein");
                klarTextArray = StringZuArrayInput();
                char[] chiffreArr = new char[klarTextArray.length];
                //fragt nach der Caeser Zahl und speichert sie als integer
                System.out.println("geben sie die gewünschte Caesar Zahl ein: ");
                caeserNum = Math.abs(intScanner.nextInt());

                //verschluesselt den text
                for(int i =0; i< klarTextArray.length;i++) {
                    int klartextIndex = indexInAlphabet(klarTextArray[i]);
                    if (klartextIndex != alphabet.length+1){
                        int chiffreIndex = (klartextIndex + caeserNum) % alphabet.length;
                        chiffreArr[i] = alphabet[chiffreIndex];
                    }
                    else {
                        chiffreArr[i] = klarTextArray[i];
                    }
                }
                System.out.println(chiffreArr);
            }
            static void caeserdechiffrieren() {
                //legt variablen fest
                char[] chiffreArr;
                int caeserNum;

                System.out.println("geben sie den zu entschlüsselnden Text ein");
                chiffreArr = StringZuArrayInput();
                char[] klarTextArr = new char[chiffreArr.length];
                //fragt nach der Caeser Zahl und speichert sie als integer
                System.out.println("geben sie die gewünschte Caesar Zahl ein: ");
                caeserNum = Math.abs(intScanner.nextInt());

                //verschluesselt den text
                for(int i =0; i< chiffreArr.length;i++) {
                    int chiffreIndex = indexInAlphabet(chiffreArr[i]);
                    if (chiffreIndex != alphabet.length+1){
                        int klartextIndex = (chiffreIndex - caeserNum) % alphabet.length;
                        while (klartextIndex<0){
                            klartextIndex += alphabet.length;
                        }
                        klarTextArr[i] = alphabet[klartextIndex];
                    }
                    else{
                        klarTextArr[i] = chiffreArr[i];
                    }

                }
                System.out.println(klarTextArr);

            }
            static void bruteForce() {
                //legt variablen fest
                char[] chiffreArr;
                System.out.println("geben sie den zu entschluesselnden Text ein");
                chiffreArr = StringZuArrayInput();
                char[] klarTextArr = new char[chiffreArr.length];
                System.out.println();
                for(int i=0; i<alphabet.length; i++ ) {
                    for(int j =0; j< chiffreArr.length;j++) {
                        int chiffreIndex = indexInAlphabet(chiffreArr[j]);
                        if (chiffreIndex != alphabet.length+1){
                            int klartextIndex = (chiffreIndex - i) % alphabet.length;
                            while (klartextIndex<0){
                                klartextIndex += alphabet.length;
                            }
                            klarTextArr[j] = alphabet[klartextIndex];
                        }
                        else{
                            klarTextArr[j] = chiffreArr[j];
                        }

                    }
                    System.out.println(i+". " + new String(klarTextArr));
                    }


            }

            static void vigenereStart(){
                //Abfrage nach gewuenscher Aktion(vigenere Verschluesselung)
                System.out.println("vigenere-Verschlüsselung");
                System.out.println("was würden sie gerne tun?\n v=verschlüsseln\n e=entschlüsseln \n\n x=Programm beenden\n");
                String aktion = StringScanner.nextLine();
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
            static void vigenereChiffrieren(){
                char[] klarTextArray;
                char[] tempPasswortArr;
                char[] passwortArray;
                String oneTimePadAuswahl;

                System.out.println("mit eigenem Passwort verschlüsseln, drück x");
                System.out.println("mit pseudozufällig generierten one-time-pad verschlüsseln, drück o");
                oneTimePadAuswahl = StringScanner.nextLine();
                if(!oneTimePadAuswahl.equals("o")) {
                    System.out.println("geben sie den zu verschlüsselnden Text ein");
                    klarTextArray = StringZuArrayInput();
                    char[] chiffreArray = new char[klarTextArray.length];
                    System.out.println("mit welchem Passwort wollen sie den Text verschlüsseln?");
                    tempPasswortArr = StringZuArrayInput();
                    if(tempPasswortArr.length >= klarTextArray.length){
                        passwortArray = tempPasswortArr;
                    }
                    else{
                        passwortArray = new char[klarTextArray.length];
                        for (int i = 0; i < klarTextArray.length; i++) {
                            passwortArray[i] = tempPasswortArr[i % tempPasswortArr.length];
                        }
                    }
                    for (int i = 0; i < klarTextArray.length; i++) {
                        if (indexInAlphabet(klarTextArray[i]) != alphabet.length+1){
                            int klarTextIndex = indexInAlphabet(klarTextArray[i]);
                            int passwortIndex = indexInAlphabet(passwortArray[i]);
                            chiffreArray[i] = alphabet[((klarTextIndex + passwortIndex) % alphabet.length)];
                        }
                        else {
                            chiffreArray[i] = klarTextArray[i];
                        }

                    }
                    System.out.println(chiffreArray);
                }
                else {
                    Random random = new Random();
                    System.out.println("geben sie den zu verschlüsselnden Text ein");
                    klarTextArray = StringZuArrayInput();
                    char[] chiffreArray = new char[klarTextArray.length];
                    char[] oneTimePadArr = new char[klarTextArray.length];
                    for(int i =0; i < klarTextArray.length; i++) {
                        int j = random.nextInt(26);
                        char c = alphabet[j];
                        oneTimePadArr[i] = c;
                    }
                    System.out.print("one-time-pad: ");
                    System.out.println(oneTimePadArr);

                    for (int i = 0; i < klarTextArray.length; i++) {
                        if (indexInAlphabet(klarTextArray[i]) != alphabet.length+1){
                            int klarTextIndex = indexInAlphabet(klarTextArray[i]);
                            int oneTimePadIndex = indexInAlphabet(oneTimePadArr[i]);
                            chiffreArray[i] = alphabet[((klarTextIndex + oneTimePadIndex) % alphabet.length)];
                        }
                        else {
                            chiffreArray[i] = klarTextArray[i];
                        }
                    }
                    System.out.print("chiffre: ");
                    System.out.println(chiffreArray);
                }


            }
            static void vigenereDechiffrieren(){
                char[] chiffreArray;
                char[] passwort;
                char[] passwortArray;

                System.out.println("geben sie den zu entschlüsselnden Text ein");
                chiffreArray = StringZuArrayInput();
                char[] klarTextArray = new char[chiffreArray.length];
                System.out.println("mit welchem Passwort wollen sie den Text entschlüsseln?:");
                passwort = StringZuArrayInput();
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
                    if (indexInAlphabet(chiffreArray[i]) != alphabet.length+1){
                        int chiffreIndex = indexInAlphabet(chiffreArray[i]);
                        int passwortIndex = indexInAlphabet(passwortArray[i]);
                        klarTextArray[i] = alphabet[(((chiffreIndex - passwortIndex)+alphabet.length) % alphabet.length)];
                    }
                    else {
                        klarTextArray[i] = chiffreArray[i];
                    }
                }
                System.out.println(klarTextArray);
            }

            static void bitStart(){
                System.out.println("bit-Verschlüsselung");
                System.out.println("was würden sie gerne tun?\n v=verschlüssel/entschlüsseln \n\n x=Programm beenden\n");
                String aktion = StringScanner.nextLine();
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
                System.out.println("welchen Zeichensatz würden sie gerne verwenden?\n u=UTF-8 \n a=ASCII\n i=ISO 8859-1\n\n x=Programm beenden\n");
                String aktion = StringScanner.nextLine();
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

                char[] klarTextArray;
                char[] passwortArray;
                System.out.println("geben sie den zu verschlüsselnden/entschlüsselnden Text ein: ");
                klarTextArray = StringZuArrayInput();
                System.out.println("geben sie das gewünschte Passwort ein: ");
                passwortArray = StringZuArrayInput();
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
                boolean zeichenGueltigkeit  = true;
                for (int i = 0; i<klarTextByteArr.length; i++) {
                    klarTextIntArr[i] = klarTextByteArr[i];
                    passwortIntArr[i] = passwortByteArr[i];
                    chiffreIntArr[i] = klarTextIntArr[i]^passwortIntArr[i];
                    chiffreByteArr[i] = (byte)chiffreIntArr[i];
                    if(chiffreByteArr[i] == 0){
                        chiffreByteArr[i] = 33;
                        zeichenGueltigkeit = false;
                    }
                }
                if (!zeichenGueltigkeit){
                    System.out.println("ein oder mehrere Zeichen können nicht korrekt verschlüsselt werden und wurden durch '!' ersetzt");
                }
                System.out.println(new String(chiffreByteArr, charset));
                System.out.println("bis hier kopieren");
            }
            static void diffieHellmann() {
                System.out.println("Diffie-Hellmann Schlüsselaustausch mit Y^x(mod P)");
                System.out.print("geben sie den Wert für Y ein, Y= " );
                int Y = intScanner.nextInt();
                System.out.print("geben sie den Wert für x ein, x= " );
                int x = intScanner.nextInt();
                System.out.print("geben sie den Wert für P ein, P= " );
                long P = longScanner.nextLong();
                BigInteger potenzwert = new BigInteger(String.valueOf(Math.pow(2147483647, 2)));
                System.out.println(potenzwert);
                System.out.println(potenzwert.mod(new BigInteger(String.valueOf(P))));
                start();
            }
            static void morseCode(){

            }

        }

