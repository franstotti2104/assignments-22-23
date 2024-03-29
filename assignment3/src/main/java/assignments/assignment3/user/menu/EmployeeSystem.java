package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

import javax.swing.text.DefaultStyledDocument.ElementSpec;

public class EmployeeSystem extends SystemCLI {

    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {

        memberList.add(new Employee("Dek Depe", "akuDDP"));
        memberList.add(new Employee("Depram", "musiktualembut"));
        memberList.add(new Employee("Lita Duo", "gitCommitPush"));
        memberList.add(new Employee("Ivan Hoshimachi", "SuamiSahSuisei"));
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        if(choice == 3){
            logout = true;
        }else{
            logout = false;
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }
}