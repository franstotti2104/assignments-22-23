package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import assignments.assignment1.NotaGenerator;

import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    private static int banyakPengguna = 0;
    private static Member member;
    private static int idNota = 0;
    private static String tanggalTerima;
    private static ArrayList<Member> memberList = new ArrayList<Member>();
    private static int sisaHari;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        // TODO: handle generate user
        banyakPengguna += 1;
        System.out.println("Masukkan nama Anda: ");
        String nama = input.nextLine();

        System.out.println("Masukkan nomor handphone Anda: ");
        String nomorHP = input.nextLine();
        while(!isNumeric(nomorHP)){
            System.out.println("Field nomor hp hanya menerima digit.");
            nomorHP = input.nextLine();
        }
        for (Member element: memberList){
            if(element.getId().equals(generateId(nama, nomorHP))){
                System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!",nama,nomorHP);
                return;
            }
        }
        Member member = new Member(nama, nomorHP);
        member.setBonusCounter(0);        
        for (int i = banyakPengguna-1; i<banyakPengguna; i++){
            memberList.add(member);
        }
        

        System.out.printf("Berhasil membuat member dengan ID %s", NotaGenerator.generateId(nama, nomorHP));
        System.out.println();
    }

    private static void handleGenerateNota() {
        boolean diskon = false;

        String tanggalMasuk = fmt.format(cal.getTime());
        System.out.println("Masukan ID member:");
        String id = input.nextLine();
        for(Member element:memberList){
            if(id.equals(element.getId())){
                element.setBonusCounter(element.getBonusCounter()+1);
                if(element.getBonusCounter() == 3){
                    diskon = true;
                    element.setBonusCounter(0);
                }
                String paket = "";
                while (true) {
                    System.out.println("Masukkan paket laundry:");
                    paket = input.nextLine();
        
                    if (paket.equals("?")) {
                        showPaket();
                        continue;
                    }
        
                    if (getHargaPaket(paket) < 0) {
                        System.out.printf("Paket %s tidak diketahui\n", paket);
                        System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                    } else {
                        break;
                    }
                }
                System.out.println("Masukkan berat cucian Anda [Kg]: ");
                String beratInput = input.nextLine();
                while (!isNumeric(beratInput) || Integer.parseInt(beratInput) < 1) {
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    beratInput = input.nextLine();
                }
                int berat = Integer.parseInt(beratInput);

                if (berat < 2) {
                    System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                    berat = 2;
                }
                Nota nota = new Nota(member, paket, berat, tanggalMasuk);
                nota.setNota(idNota);
                System.out.println("Berhasil menambahkan nota!");
                System.out.printf("[ID Nota = %s]\n",nota.getNota());
                System.out.println(generateNota(id, paket, berat, tanggalMasuk,diskon));
                System.out.println("Status      	: Belum bisa diambil :(");
                idNota++;
                nota.setSisaHariPengerjaan(getHariPaket(paket));
                notaList.add(nota);

                
                return;   
            }
        }
        System.out.printf("Member dengan %s tidak ditemukan!", id);
        

    }

    private static void handleListNota() {
        System.out.printf("Terdaftar %s nota dalam sistem\n", notaList.size());
        for(Nota element:notaList){
            String kata;
            if (!element.getIsReady()){
                kata = "Belum bisa diambil :(";
            }else{
                kata = "Sudah dapat diambil!";
            }
            System.out.printf("- [%s] Status      	: %s\n",element.getNota(),kata);
        }
    }

    private static void handleListUser() {
        System.out.printf("Terdaftar %s member dalam sistem.\n",banyakPengguna);
        for (int i = 0; i < banyakPengguna; i++){
            System.out.println("- " + memberList.get(i).getId() + " : " + memberList.get(i).getNama());
        }

    }

    private static void handleAmbilCucian() {
        System.out.println("Masukkan ID nota yang akan diambil: ");
        String idNota = input.nextLine();
        while (!isNumeric(idNota)) {
            System.out.println("ID nota berbentuk angka!");
            idNota = input.nextLine();
        }
        for (int i = 0; i < notaList.size(); i++){
            if (Integer.parseInt(idNota) == notaList.get(i).getNota()){
                if(notaList.get(i).getSisaHari() > 0){
                    System.out.printf("Nota dengan ID %s gagal diambil!",idNota);
                }else{
                    System.out.printf("Nota dengan ID %s berhasil diambil!",idNota);
                    notaList.remove(i);
                }
                return;
            }
        }
        System.out.printf("Nota dengan ID %s tidak ditemukan!\n",idNota);

    }

    private static void handleNextDay() {
        System.out.println("Dek Depe tidur hari ini... zzz...");
        for(Nota element:notaList){
            sisaHari = element.getSisaHari() - 1;
            element.setSisaHariPengerjaan(sisaHari);
            if(sisaHari <= 0){
                element.setIsReady(true);
                System.out.printf("Laundry dengan nota ID %s sudah dapat diambil!\n",element.getNota());
            }
        }
        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");
        String[] pemisahTanggal = fmt.format(cal.getTime()).split("/");
        //Mengubah String tanggal menjadi int
        int tanggal = Integer.parseInt(pemisahTanggal[0]);
        int bulan = Integer.parseInt(pemisahTanggal[1]);
        int tahun = Integer.parseInt(pemisahTanggal[2]);
        cal.set(tahun,bulan-1,tanggal+1);
        tanggalTerima = fmt.format(cal.getTime());
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

}
