package assignments.assignment1;

import java.util.Scanner;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        while (true){

            //Print Menu yang tersedia
            printMenu();
            System.out.print("Pilihan: ");
            String pilihan = input.next();
            System.out.println("================================");

            //Memberi program jika pilihan adalah 1 atau 2 atau 0 atau bukan ketiganya
            if (pilihan.equals("1")){

                //Menjalankan program jika pilihan = 1
                System.out.println("Masukkan nama Anda: ");
                String nama = input.next();
                input.nextLine();
                System.out.println("Masukkan nomor handphone Anda: ");
                String nomorHP = input.nextLine();

                //Mengecek jika nomorHP terdapat huruf atau unsur lain yang bukan angka
                for(int i = 0; i < nomorHP.length(); i++){
                    char charAngka = nomorHP.charAt(i);
                    String angka = String.valueOf(charAngka);
                    if(cekNumber(angka)){
                        continue;
                    }else{
                        System.out.println("Nomor HP hanya menerima digit");
                        nomorHP = input.nextLine();
                        break;
                    }
                }

                //Print method generateId
                System.out.println("ID Anda : " + generateId(nama, nomorHP));

            //Jika pilihan = 2
            }else if (pilihan.equals("2")){

                System.out.println("Masukkan nama Anda: ");
                String nama = input.next();
                input.nextLine();
                System.out.println("Masukkan nomor handphone Anda: ");
                String nomorHp = input.nextLine();
                System.out.println("Masukkan tanggal terima: ");
                String tanggal = input.next();
                input.nextLine();
                String paket;

                
                while(true){
                    System.out.println("Masukkan paket laundry: ");
                    paket = input.next();

                    //Jika paket diketik ?, untuk menampilkan menu paket
                    if(paket.equals("?")){
                        showPaket();
                    
                    //Jika paket bukan express, fast, maupun reguler
                    }else if(!paket.equals("express") && !paket.equals("fast") && !paket.equals("reguler")){
                        System.out.println("Paket " + paket + " tidak diketahui");
                        System.out.println("[ketik ? untuk mencari tahu jenis paket]");

                    //Jika paket merupakan express, fast, atau reguler
                    }else if(paket.equals("express")||paket.equals("fast")||paket.equals("reguler")){
                        break;
                    }
                }

                //Meminta input berat
                System.out.println("Masukkan berat cucian Anda: ");
                String berat;
                int intBerat;
                while(true){
                    berat = input.next();
                    //Mencek jika inputan terdapat huruf
                    for (int i = 0; i<berat.length(); i++){
                        char charBerat = berat.charAt(i);
                        String strBerat = String.valueOf(charBerat);
                        if (cekNumber(strBerat)){
                            continue;
                        }else{
                            System.out.println("Harap masukkan berat cucian anda dalam bentuk bilangan positif");
                            berat = input.next();
                            break;
                        }
                    }
                    intBerat = Integer.parseInt(berat);
                    if(intBerat<0){
                        System.out.println("Harap masukkan berat cucian anda dalam bentuk bilangan positif");
                    }else if(intBerat<2){
                        System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg.");
                        intBerat = 2;
                        break;
                    }else {
                        break;
                    }
                }

                //print hasil akhir
                String id = generateId(nama, nomorHp);
                System.out.println("Nota Laundry");
                System.out.println(generateNota(id, paket, intBerat, tanggal));
            
            //Jika pilihan = 0
            }else if(pilihan.equals("0")){
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                break;
            }else{
                System.out.println("Perintah tidak diketahui, silakan periksa kembali");
            }
        }
        input.close();
    }    
    


    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        //Memisahkan nama jika nama lebih dari 1 kata
        String[] pemisahNama = nama.split(" ");
        String namaID = pemisahNama[0].toUpperCase();
        int finalNama = 0;
        int finalNomor = 0;
        int checkSum;

        //Mengubah setiap huruf dalam nama menjadi kode Ascii kemudian diubah menjadi value setiap alfabet
        for (int i = 0; i < namaID.length(); i++){
            int ascii = (int) namaID.charAt(i) - 64;
            finalNama += ascii;
        }

        //Mengubah tiap nomor dalam string menjadi kode Ascii kemudian diubah menjadi value setiap nomor
        for (int i = 0; i < nomorHP.length(); i++){
            int intNomor = (int) nomorHP.charAt(i) - 48;
            finalNomor += intNomor;
        }

        //Menjumlahkan tiap kode agar menjadi checkSum
        checkSum = finalNama+finalNomor+7;
        String finalCheckSum = Integer.toString(checkSum);

        //Membuat pemisalan jika CheckSum lebih dari 2 digit, dan jika checkSum hanya 1 digit
        if (finalCheckSum.length()>2){
            checkSum  = checkSum%100;
            finalCheckSum = Integer.toString(checkSum);
            if (finalCheckSum.length() == 1){
                finalCheckSum = "0"+finalCheckSum;
            }          
        }else if(finalCheckSum.length() == 1){
            finalCheckSum = "0"+finalCheckSum;
        }

        //Menggabungkan sekaligus return dalam bentuk String
        String finalID = namaID+"-"+nomorHP+"-"+finalCheckSum;
        return finalID;
        // return null;
    }

    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        id = "ID    : " + id;
        String teksPaket = "Paket : " + paket;
        int biaya;
        int tanggalKembali;

        //Membuat pemisalan di setiap paket
        if (paket.equals("express")){
            biaya = 12000;
            tanggalKembali = 1;
        }else if(paket.equals("fast")){ 
            biaya = 10000;
            tanggalKembali = 2;
        }else{
            biaya = 7000;
            tanggalKembali = 3;
        }
        
        //Membuat perhitungan harga total
        int hargaTotal = berat*biaya;

        //Mengatur tanggal
        String[] pemisahTanggal = tanggalTerima.split("/");
        //Mengubah String tanggal menjadi int
        int tanggal = Integer.parseInt(pemisahTanggal[0]);
        int bulan = Integer.parseInt(pemisahTanggal[1]);
        int tahun = Integer.parseInt(pemisahTanggal[2]);

        //Mendapatkan calendar nya
        Calendar calendar = Calendar.getInstance();

        //Set untuk menghasilkan tanggal laundry selesai
        calendar.set(tahun, bulan-1, tanggal+tanggalKembali);
        Date date = calendar.getTime();

        //Membuat formatnya
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        String tanggalSelesai = dateFormat.format(date);

        //Membuat hasil akhir
        String finalNota = id + "\n" + teksPaket + "\n" + "Harga :" + "\n" + berat + " kg" + " x " + biaya + " = " + hargaTotal + "\n" + "Tanggal Terima  : " + tanggalTerima + "\n" + "Tanggal Selesai : " + tanggalSelesai;
        return finalNota;
    }

    //Membuat method lain untuk mengecek apakah di sebuah string terdapat angka
    public static boolean cekNumber(String number){
        boolean isNumber = true;
        try{
            int angka = Integer.parseInt(number);
        }catch(Exception e){
            isNumber = false;
        }
        return isNumber;
    }
}

//acknowledment
// Narendra Dzulqarnain - 2206081881