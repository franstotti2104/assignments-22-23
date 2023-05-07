package assignments.assignment3.user;

public class Employee extends Member {
    public static int employeeCount;
    public Employee(String nama, String password) {
        super(nama, generateId(nama), password);
    }

    /**
     * Membuat ID employee dari nama employee dengan format
     * NAMA_DEPAN-[jumlah employee, mulai dari 0]
     * Contoh: Dek Depe adalah employee pertama yang dibuat, sehingga IDnya adalah DEK-0
     *
     * @param nama -> Nama lengkap dari employee
     */
    private static String generateId(String nama) {
        String id = "";
        if(nama.toLowerCase().equals("dek depe")){
            id = "DEK-0";
        }else if(nama.toLowerCase().equals("depram")){
            id = "DEPRAM-1";
        }else if(nama.toLowerCase().equals("lita duo")){
            id = "LITA-2";
        }else if(nama.toLowerCase().equals("ivan hoshimachi")){
            id = "IVAN-3";
        }else{
            id = null;
        }
        return id;
    }
}
