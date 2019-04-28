/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tittactoe;

import java.util.Scanner;

/**
 *
 * @author Nurul
 */
public class tictactoe {

    private char papanGame [][];
  
    private final int JUMLAH_BARIS = 3;
    private final int JUMLAH_KOLOM = 3;
  
    private char giliranPemain;
  
    private enum statusGame{
     WINNER,
     DRAW,
     PROCESS
    }
     
    private static Scanner input = new Scanner (System.in);
  
 public tictactoe(){
   
  papanGame = new char [JUMLAH_BARIS] [JUMLAH_KOLOM];
  System.out.println("Welcome To Tic-Tac-Toe Game");
  System.out.println("===========================");
   
  do{
  System.out.print("Please choose your character :\n"
    + "Press 1 for X \n"
    + "Press 2 for O \n"
    + "Type here = ");
  int pilihKarakter = input.nextInt();
  if(pilihKarakter == 1){
   giliranPemain = 'x';
   System.out.println("Papan telah diinisilisasi\n"
     + "**************************");
   }else if(pilihKarakter == 2){
   giliranPemain = 'o';
   System.out.println("Board has been initialized\n"
     + "**************************");
    }else{System.out.println("Wrong option!\n"
      + "*************************");
     }
  } while (cekPilihKarakter(giliranPemain) == false);
  inisialisasiPapan();
  tampilkanPapan();
   
 }
 //**********************************INISIALISASI*************************
 //Method untuk inisialisasi
  private void inisialisasiPapan(){
   for (int i = 0; i < JUMLAH_BARIS; i ++){
    for (int j = 0; j < JUMLAH_KOLOM; j++){
     papanGame [i][j] = '?';
    }
   }
   char playerSatu;
   char playerDua;
   if(giliranPemain == 'x'){
    playerSatu = 'x';
    playerDua = 'o';
    System.out.println("PLAYER 1: " + playerSatu);
    System.out.println("PLAYER 2: " + playerDua);
   }else if(giliranPemain == 'o'){
    playerSatu = 'o';
    playerDua = 'x';
    System.out.println("PLAYER 1: " + playerSatu);
    System.out.println("PLAYER 2: " + playerDua);
   }
      
  }//Akhir method inisilaisasiPapan()
   
  //*************************CEK PILIH KARAKTER*****************************//
  //Method untuk mengecek pemain memilih karakter
  //untuk dimainkan dengan valid
  private boolean cekPilihKarakter (char giliranPemain){
   boolean pilihKarakter = false;
   if(giliranPemain == 'x' || giliranPemain =='o'){
    pilihKarakter = true;
    }else{
     pilihKarakter = false;
     }
   return pilihKarakter;
  }//Akhir method cekPilihKarakter
   
  //************************************TAMPILKAN PAPAN***********************//
  //Method untuk menampilkan papan tic tac toe
  private void tampilkanPapan(){
   System.out.println("=============");
   for (int i = 0; i < JUMLAH_BARIS; i ++){
    System.out.print("| ");
    for (int j = 0; j < JUMLAH_KOLOM; j++){
     System.out.print(papanGame[i][j] + " | ");
    }
    System.out.println();
    System.out.println("=============");
   }
  }//Akhir method tampilkan papan
   
  //*************************GANTI PEMAIN*************************************//
  //Method untuk mengecek giliran pemain
  private void gantiPemain(){
   if(giliranPemain == 'x'){
    giliranPemain = 'o';
   }else{
    giliranPemain = 'x';
   }
    
  }//Akhir method gantiPemain
   
  //**************************CEK PEMENANG*************************************//
  //Method untuk mengecek jika ada pemenang
  private boolean cekPemenang(){
   return(cekBarisPemenang() || cekKolomPemenang() 
     || cekDiagonalPemenang());
    
  }//Akhir method cekPemenang
   
  //Method untuk mengecek baris pemenang
  private boolean cekBarisPemenang(){
   for(int i = 0; i < JUMLAH_BARIS; i++){
    if(cekBarisKolom(papanGame[i][0], papanGame[i][1], 
      papanGame[i][2]) == true){
     return true;
    }
   }
   return false;
  }//Akhir method cekBarisPemenang
   
  //Method untuk mengecek kolom pemenang
  private boolean cekKolomPemenang(){
   for(int i = 0; i < JUMLAH_KOLOM; i++){
    if(cekBarisKolom(papanGame[0][i], papanGame[1][i], 
      papanGame[2][i]) == true){
     return true;
    }
   }
   return false;
  }//Akhir method cekKolomPemenang
   
   
  //Method untuk mengecek diagonal pemenang
  private boolean cekDiagonalPemenang(){
   return((cekBarisKolom(papanGame[0][0], papanGame[1][1], 
     papanGame[2][2]) == true)
     || (cekBarisKolom(papanGame[0][2], papanGame[1][1], 
       papanGame[2][0]) == true) );
  }//Akhir method cekDiagonalPemenang
   
   
  //Method untuk mengecek tiga nilai adalah sama
  //dan tidak kosong pada baris atau kolom
  private boolean cekBarisKolom(char a1, char a2, char a3){
   return ((a1 != '?') && (a1 == a2) && (a2 == a3));
  }//Akhir method cekBarisKolom
   
  //************************AKHIR CEK PEMENANG***********************************//
   
   
  //******************************CEK DRAW***************************************//
  //Method untuk mengecek kondisi papan game
  //sudah penuh atau belum
  private boolean cekDraw(){
   boolean draw = true;
   for(int i = 0; i < JUMLAH_BARIS; i++){
    for (int j = 0; j < JUMLAH_KOLOM; j++){
     if(papanGame[i][j] == '?'){
      draw = false;
     }
    }
   }
   return draw;
  }//Akhir method cekPapanPenuh
  //******************************AKHIR CEK DRAW**********************************//
   
  //************************CEk STATUS GAME************************************//
  private statusGame statusSekarang(){
   if(cekPemenang() == true)
    return statusGame.WINNER;
   else if(cekDraw() == true)
    return statusGame.DRAW;
   else
    return statusGame.PROCESS;
  }
   
  private void tampilkanStatus(){
         statusGame status = statusSekarang();
         if (status == statusGame.WINNER)
             System.out.println("PLAYER " + giliranPemain +" WON!!");
         else if (status == statusGame.DRAW)
             System.out.println("DRAW! GAME ENDED!!!");
  }
  //******************************AKHIR CEK STATUS GAME*****************************//
   
  //***********************************INPUT KARAKTER******************************//
  //Method untuk menginput karakter
  private void inputKarakter(){
    
     cekPemenang();
        cekDraw();
        statusSekarang();
    
    System.out.print("Pemain " + giliranPemain +
      " silakan pilih baris (0-2): ");
    int row = input.nextInt();
    System.out.print("Pemain " + giliranPemain +
      " silakan pilih kolom (0-2): ");
    int col = input.nextInt();
    
             if ((row < 0) || (row > 2))
                 System.out.println("Invalid line type, Try again!");
             else if ((col < 0) || (col > 2))
                 System.out.println("Invalid column type, Try again!");
 
              
             else if (papanGame[row][col] != '?')
                 System.out.println("This area is filled, Try again!");
              
             else {
                 tandaiPapan(row, col, giliranPemain);
                 tampilkanPapan();
                 if(statusSekarang() == statusGame.PROCESS){
                  gantiPemain();
                 }
             
             }
  }//Akhir method input karakter
   
     //***********************************MENANDAI PAPAN********************************//
     // Pemain menandai papan
     private void tandaiPapan(int baris, int kolom, char c) {
         papanGame[baris][kolom] = c;
     }//Akhir method tandaiPapan
   
     //************************************START GAME***********************************//
     public void start(){
      do{
       inputKarakter();
      }while(statusSekarang() == statusGame.PROCESS);
      tampilkanStatus();
     }//Akhir method start()
}
