package batalha_final;

import java.util.Random;
import java.util.Scanner;

public class Batalha_final {

    static Scanner ler = new Scanner(System.in);
    static Random aleatorio = new Random();

    public static void main(String[] args) throws InterruptedException {
        char[][] mar1 = new char[10][10];
        char[][] mar2 = new char[10][10];
        carregarInicio(mar1, mar2);
        int opcao = 0;
        System.out.println("BATALHA NAVAL!");
        do {
            System.out.println("1- Player x Player");
            System.out.println("2- Player x Máquina");
            System.out.print("Modo de jogo: ");
            opcao = ler.nextInt();
            System.out.println(" ");
            if (opcao != 1 && opcao != 2) {
                System.out.println("Digite um valor válido");
            } else if (opcao == 1) {
                System.out.println("Modo selecionado: ");
                System.out.println("Player x Player");
            } else {
                System.out.println("Modo selecionado: ");
                System.out.println("Player x Máquina");
            }
        } while (opcao != 1 && opcao != 2);
        int player = 1;
        boolean bot = false;
        boolean novamente;
        switch (opcao) {
            case 1 -> {
                System.out.println("\n\n\n\n\n\n\n\n\n\n\n Player 1 \n");
                carregarMapa(mar1);
                colocarBarcosPlayers(mar1);
                System.out.println("\n\n\n\n\n\n\n\n Player 2\n");
                carregarMapa(mar2);
                colocarBarcosPlayers(mar2);

                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
                boolean venceu = false;
                for (; venceu != true; ) {
                    novamente = true;
                    for (; novamente && venceu != true; ) {
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n Player 1 Atacando:\n");
                        carregarMapa(mar2);
                        novamente = ataque(mar2, bot);
                        player = 1;
                        venceu = testeVenceu(mar2);
                    }
                    if (venceu != true) {
                        novamente = true;
                        for (; novamente && venceu != true; ) {
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n Player 2 Atacando:\n");
                            carregarMapa(mar1);
                            novamente = ataque(mar1, bot);
                            player = 2;
                            venceu = testeVenceu(mar1);
                        }
                    }
                }
                venceu(player, bot);
            }
            case 2 -> {
                System.out.println("\n\n\n\n\n Player 1\n");
                carregarMapa(mar1);
                colocarBarcosPlayers(mar1);
                System.out.println("\n\n\n\n\n Máquina\n");
                bot = true;
                carregarMapa(mar2);
                colocarBarcos(mar2, bot);
                System.out.println("\n\t - Máquina colocando os barcos");

                System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n");
                boolean venceu = false;
                for (; venceu != true; ) {
                    bot = false;
                    novamente = true;
                    for (; novamente && venceu != true; ) {
                        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n Player 1 - Atacando:\n");
                        carregarMapa(mar2);
                        novamente = ataque(mar2, bot);
                        player = 1;
                        venceu = testeVenceu(mar2);
                    }
                    if (venceu != true) {
                        novamente = true;
                        bot = true;
                        for (; novamente && venceu != true; ) {
                            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\nMÁQUINA - Atacando:\n");
                            carregarMapa(mar1);
                            System.out.println("A máquina está jogando...");
                            novamente = ataque(mar1, bot);
                            player = 2;
                            venceu = testeVenceu(mar1);
                        }
                    }
                }
                venceu(player, bot);
            }
        }
    }


    public static void venceu(int player, boolean bot) {
        System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
        if (bot != true) {
            System.out.println("PLAYER " + player + " ganhou!");
        } else {
            System.out.println("MÁQUINA ganhou!");
        }
    }

    public static boolean testeVenceu(char[][] mar) {
        int cont = 20;
        for(int i=0; i<mar.length; i++){
            for(int j=0; j<mar.length; j++){
                if(mar[i][j] == 'B')
                    cont--;
            }
        }
        if(cont==0){
            return true;
        }
        else{
            return false;
        }
    }

    public static boolean ataque(char[][] mar, boolean bot){
        int x, y;
        boolean novamente = false;
        x=-1;
        y=-1;
        if(bot==false){
            for(;x<0 || x>9;){
                System.out.print(" - x: ");
                x = ler.nextInt();   
            }
            for(;y<0 || y>9;){
                System.out.print(" - y: ");
                y = ler.nextInt();     
            }
            switch (mar[y][x]) {
                case 'B' -> {
                    mar[y][x] = 'b';
                    novamente = true;
                    System.out.println("\nBoa você acertou! Jogue novamente!\n");
                }
                case 'A' -> {
                    mar[y][x] = 'a';
                    System.out.println("\nVocê errou! Será a vez do outro jogador!");
                }
                default -> {
                    System.out.println("\nEste lugar já foi atacado! Jogue novamente!\n");
                    novamente = true;
                }
            }
        }
        else{
            x = aleatorio.nextInt(0, 10);
            y = aleatorio.nextInt(0, 10);
            switch (mar[y][x]) {
                case 'B' -> {
                    mar[y][x] = 'b';
                    novamente = true;
                    System.out.println("\nA máquina acertou! A máquina vai jogar novamente!\n");
                }
                case 'A' -> {
                    mar[y][x] = 'a';
                    System.out.println("\nA máquina errou! Será a vez player 1!");
                }
                default -> novamente = true;
            }
        }
        return novamente;
    }


    public static void colocarBarcosPlayers(char[][] mar) {
        boolean hitbox;
        boolean novamente;
        int[] propriedadesBarcos = {4, 3, 2, 1};
        int x = 0, y = 0;
        char sentido = 'e';
        int colocar = 0;
        do{
        System.out.println("Colocar os barcos: ");
        System.out.println("1 - Colocar manual: ");
        System.out.println("2 - Colocar automático: ");
        System.out.print("Digite sua opção: ");
        colocar = ler.nextInt();
        }while(colocar != 1 && colocar != 2);
        if(colocar == 1){
            System.out.print("Colocar manual foi selecionado");
        }
        else{
            System.out.print("Colocar automático foi selecionado");
        }
        for(int i=propriedadesBarcos.length-1; i>=0; i--){
            for(int j=1; j<=propriedadesBarcos[i]; j++){
                novamente = false;
                hitbox = false;
                switch(colocar){
                    case 1 ->{
                
                        for(;hitbox==false;){
                            x=-1;
                            y=-1;
                            
                            if(novamente==true){
                                System.out.println("\nNão foi possível colocar o barco\n");
                            }
                            for(;sentido != 'v' && sentido != 'h' && i>0;){
                                System.out.print("Digite a direção (h - horizontal; v - vertical) do "+j+"º barco de "+(i+1)+" espaços: ");
                                sentido = ler.next().toLowerCase().charAt(0);
                            }
                            if(i==0)
                                sentido = 'v';
                            
                                System.out.println(" - Escolha um lugar para colocar o "+j+"º barco de "+(i+1)+" espaços: ");

                                for(;x<0 || x>9;){
                                    System.out.print("   - x: ");
                                    x = ler.nextInt();   
                                }
                                for(;y<0 || y>9;){
                                    System.out.print("   - y: ");
                                    y = ler.nextInt();     
                                }    
                            }
                        }
                    
                    case 2 ->{
                        x = aleatorio.nextInt(0, 10);
                        y = aleatorio.nextInt(0, 10);
                        int s = aleatorio.nextInt(0, 2);
                        if(s==0){
                            sentido = 'v';
                        } 
                        else{
                            sentido = 'h';
                        }
                            switch(sentido){
                                case 'v' ->{
                                    if(y+i <= 9){
                                        hitbox = true;
                                        for(int t=y; t<=(y+i); t++){
                                            if(mar[t][x]!='A' && mar[t][x] != 'a'){
                                                hitbox = false;
                                            }
                                        }
                                        if(hitbox==true){
                                            for(int l=y; l<=(y+i); l++)
                                                mar[l][x] = 'B';
                                        }
                                    }
                                }
                                case 'h' ->{
                                    if(x+i <= 9){
                                        hitbox = true;
                                        for(int t=x; t<=(x+i); t++){
                                            if(mar[y][t]!='A' && mar[y][t] != 'a'){
                                                hitbox = false;
                                            }
                                        }
                                        if(hitbox==true){
                                            for(int c=x; c<=(x+i); c++)
                                                mar[y][c] = 'B';
                                        }
                                    }
                                }
                            }
                        }
                }
            }
        }
    }
    
    public static void colocarBarcos(char[][] mar, boolean bot){
        boolean hitbox;
        int[] propriedadesBarcos = {4, 3, 2, 1};
        int x, y;
        char direcao;
        for(int i=propriedadesBarcos.length-1; i>=0; i--){
            for(int j=1; j<=propriedadesBarcos[i]; j++){
                hitbox = false;
                for(;hitbox==false;){
                    x=-1;
                    y=-1;
                    direcao = 'e';
                    if(bot==true){
                        x = aleatorio.nextInt(0, 10);
                        y = aleatorio.nextInt(0, 10);
                        int s = aleatorio.nextInt(0, 2);
                        if(s==0)
                            direcao = 'v';
                        else
                            direcao = 'h';
                    }
                    switch(direcao){
                        case 'v' ->{
                            if(y+i <= 9){
                                hitbox = true;
                                for(int t=y; t<=(y+i); t++){
                                    if(mar[t][x]!='A' && mar[t][x] != 'a'){
                                        hitbox = false;
                                    }
                                }
                                if(hitbox==true){
                                    for(int l=y; l<=(y+i); l++)
                                        mar[l][x] = 'B';
                                }
                            }
                        }
                        case 'h' ->{
                            if(x+i <= 9){
                                hitbox = true;
                                for(int t=x; t<=(x+i); t++){
                                    if(mar[y][t]!='A' && mar[y][t] != 'a'){
                                        hitbox = false;
                                    }
                                }
                                if(hitbox==true){
                                    for(int c=x; c<=(x+i); c++)
                                        mar[y][c] = 'B';
                                }
                            }
                        }
                    }
                }
            }
        }
    }

        
        
    
    public static void carregarMapa(char[][] mar) {
       System.out.println("   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |");
       System.out.println("--------------------------------------------");
       String linha;
       for(int i=0; i<mar.length; i++){
           linha = "";
           linha += String.format(" %d | ", i);
           for(int j=0; j<mar.length; j++){
               switch (mar[i][j]) {
                   case 'A', 'B' -> linha += String.format("  | ");
                   case 'a' -> linha += String.format("O | ");
                   default -> linha += String.format("x | ");
               }
           }
           System.out.println(linha);
           System.out.println("--------------------------------------------");
       }
    }
    

    public static void carregarInicio(char[][] mar1, char[][] mar2) {
        for (char[] mar11 : mar1) {
            for (int j = 0; j < mar1.length; j++) {
                mar11[j] = 'A';
            }
        }
        for (char[] mar21 : mar2) {
            for (int j = 0; j < mar2.length; j++) {
                mar21[j] = 'A';
            }
        }
    }
}
