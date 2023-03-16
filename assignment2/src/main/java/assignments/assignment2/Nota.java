package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private Member member;
    private String paket;
    private int berat;
    private String tanggalMasuk;
    private int sisaHari;
    private int idNota;
    private boolean ready;
    
    public Nota(Member member, String paket, int berat, String tanggalMasuk) {
        // TODO: buat constructor untuk class ini
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
    }
    // TODO: tambahkan methods yang diperlukan untuk class ini

    public int idNota(){
        return null;
    }

    public String paket(){
        return paket;
    }

    public int berat(){
        return berat;
    }

    public String tanggalMasuk(){
        return tanggalMasuk;
    }

    public Member member(){
        return member;
    }

    public void setSisaHariPengerjaan(int sisaHari){
        this.sisaHari = sisaHari;
    }

    public int getSisaHari(){
        return sisaHari;
    }

    public void setIsReady(boolean ready){
        this.ready = ready;
    }

    public boolean getIsReady(){
        return ready;
    }

    public void setNota(int idNota){
        this.idNota = idNota;
    }

    public int getNota(){
        return idNota;
    }
     
}
