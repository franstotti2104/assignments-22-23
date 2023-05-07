package assignments.assignment3.user.menu;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

import assignments.assignment1.*;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;

public class MemberSystem extends SystemCLI {
    private Scanner input = new Scanner(System.in);
    private int berat;
    private String paket;
    private String pilihanSetrika;
    private String pilihanAntar;
    public Nota nota;
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        if(choice == 3){
            logout = true;
        }else if(choice == 1){
            logout = false;
            choice1();
        }else if(choice == 2){
            logout = false;
            
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        //TODO
        memberList.add(member);
    }

    public void choice1(){
        System.out.println("Masukkan paket laundry:");
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
        paket = input.next();
        input.nextLine();
        System.out.println("Masukkan berat cucian anda [Kg]:");
        berat = input.nextInt();
        if(berat < 2){
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat = 2;
        }
        System.out.println("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?");
        System.out.println("Hanya tambah 1000 / kg :0");
        System.out.print("[Ketik x untuk tidak mau]: ");
        pilihanSetrika = input.next();
        input.nextLine();
        System.out.println("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!");
        System.out.println("Cuma 2000 / 4kg, kemudian 500 / kg");
        System.out.print("[Ketik x untuk tidak mau]: ");
        pilihanAntar = input.next();
        input.nextLine();
        System.out.println("Nota berhasil dibuat!");
        // nota = new Nota(loginMember, berat, paket, Calendar.getInstance());
    }
}