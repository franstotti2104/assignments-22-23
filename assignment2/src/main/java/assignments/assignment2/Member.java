package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter;

    public Member(String nama, String noHp) {
        // TODO: buat constructor untuk class ini
        this.nama = nama;
        this.noHp = noHp;
    }
    
    public String getNama(){
        return nama;
    }

    public String getNomor(){
        return noHp;
    }

    public String getId(){
        id = NotaGenerator.generateId(nama, noHp);
        return id;
    }

    public void setBonusCounter(int bonusCounter){
        this.bonusCounter = bonusCounter;
    }
    
    public int getBonusCounter(){
        return bonusCounter;
    }
    
}
