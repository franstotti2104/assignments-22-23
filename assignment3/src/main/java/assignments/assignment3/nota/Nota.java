package assignments.assignment3.nota;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
import java.util.Calendar;
import java.text.SimpleDateFormat;
import assignments.assignment2.*;
import java.util.ArrayList;

public class Nota {
    private Member member;
    private String paket;
    private ArrayList<LaundryService> services = new ArrayList<LaundryService>();
    private long baseHarga;
    private int sisaHariPengerjaan;
    private  int berat;
    private int idNota;
    private String tanggalMasuk;
    private boolean isReady = false;;
    static public int totalNota;
    private static int hargaSetrika;
    private static int hargaAntar;
    private static int totalHarga;

    public Nota(Member member, int berat, String paket, String tanggal) {
        //TODO
        this.member = member;
        this.berat = berat;
        this.paket = paket;
        this.tanggal = tanggal;
        this.id = totalNota;
        totalNota++;
    }

    public int getId(){
        return idNota;
    }

    public void addService(LaundryService service){
        services.add(service);
        
    }

    public String kerjakan(){
        // TODO
        return "";
    }

    public boolean isReady(){
        return isReady;
    }

    public void toNextDay() {
        // TODO
        sisaHariPengerjaan--;
        if(sisaHariPengerjaan <= 0){
            isReady = true;
        }
    }

    public long calculateHarga(){
        // TODO
        
        return -1;
    }

    public String getNotaStatus(){
        // TODO
        return "";
    }

    @Override
    public String toString(){
        // TODO
        return String.format("[ID Nota = %d]%n", idNota) + generateNota(member.getId(), paket, berat, tanggalTerima) + "\n"
        + getStatus();
    }

    // Dibawah ini adalah getter

    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }

    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }

    public LaundryService[] getServices(){
        return services;
    }

    public static String generateNota(String id, String paket, int berat, String tanggalTerima, String pilihanSetrika, String pilihanAntar) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Calendar cal = Calendar.getInstance();
        int year = Integer.parseInt(tanggalTerima.substring(6));
        int month = Integer.parseInt(tanggalTerima.substring(3, 5)) - 1;
        int date = Integer.parseInt(tanggalTerima.substring(0, 2));
        cal.set(year, month, date);

        String nota = "";
        nota += "ID    : " + id + "\n";
        nota += "Paket : " + paket + "\n";
        nota += "Harga :\n";
        nota += String.format("%d kg x %d = %d", berat, toHargaPaket(paket), (berat * toHargaPaket(paket)));
        nota += "\nTanggal Terima  : " + tanggalTerima + "\n";
        cal.add(Calendar.DATE, toHariPaket(paket));
        nota += "Tanggal Selesai : " + formatter.format(cal.getTime());
        nota += ("\n--- SERVICE LIST ---");
        nota += ("\n-Cuci @ Rp.0");
        if(pilihanSetrika.equals("x")){
            hargaSetrika = 0;
        }else{
            hargaSetrika = berat*1000;
            nota += "\n-Setrika @ " + hargaSetrika;
        }
        if(pilihanAntar.equals("x")){
            hargaAntar = 0;
        }else{
            if(berat <= 4){
                hargaAntar = 2000;
                nota += "\n-Antar @ Rp.2000";
            }else{
                hargaAntar = 2000 + (500*(berat-4));
                nota += "\n-Antar @ " + hargaAntar;
            }
        }
        totalHarga = berat*toHargaPaket(paket) + hargaAntar + hargaSetrika;
        nota += String.format("\nHarga Akhir: %d",totalHarga); 
        return nota;
    }

    public static long toHargaPaket(String paket) {
        paket = paket.toLowerCase();
        if (paket.equals("express"))
            return 12000;
        if (paket.equals("fast"))
            return 10000;
        if (paket.equals("reguler"))
            return 7000;
        return -1;
    }
}
