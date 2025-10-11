import java.util.ArrayList;

public class Petugas {
    ArrayList<Buku> bukuList = new ArrayList<>();
    ArrayList<Anggota> anggotaList = new ArrayList<>();
    ArrayList<Peminjaman> peminjamanList = new ArrayList<>();

    void simpanBuku(Buku b) {
        bukuList.add(b);
    }

    void simpanAnggota(Anggota a) {
        anggotaList.add(a);
    }

    void pinjamBuku(String idPeminjaman, Anggota a, Buku b) {
        Peminjaman p = new Peminjaman();
        p.setIdPeminjaman(idPeminjaman);
        p.setAnggota(a);
        p.setBuku(b);
        peminjamanList.add(p);
    }

    void hapusPinjamBuku(int index) {
        if (index >= 0 && index < peminjamanList.size()) {
            peminjamanList.remove(index);
        }
    }

    void tampilPinjamBuku() {
        int no = 0;
        System.out.println("DATA PEMINJAMAN");
        System.out.println("------------------------------------------------------------");
        System.out.printf("| %-2s | %-6s | %-8s | %-13s | %-4s | %-15s |\n", 
                         "No", "ID Pinj", "ID Angg", "Nama", "ID Bk", "Judul");
        System.out.println("------------------------------------------------------------");
        
        for (Peminjaman p : peminjamanList) {
            System.out.printf(
                    "| %-2s | %-6s | %-8s | %-13s | %-4s | %-15s |",
                    no + 1,
                    p.getIdPeminjaman(),
                    p.getAnggota().getNpm(),
                    (p.getAnggota().getNama().length() > 13) ? 
                    p.getAnggota().getNama().substring(0, 13) : p.getAnggota().getNama(),
                    p.getBuku().getId(),
                    (p.getBuku().getJudul().length() > 15) ? 
                    p.getBuku().getJudul().substring(0, 15) : p.getBuku().getJudul());
            System.out.println();
            no++;
        }
        System.out.println("------------------------------------------------------------");
    }

    int jumlahPeminjaman(){
        return peminjamanList.size();
    }

    void tampilAnggota() {
        int no = 0;
        System.out.println("DATA ANGGOTA");           
        System.out.println("------------------------------------");            
        for (Anggota a : anggotaList) {
            System.out.printf(
                    "| %-2s | %-5s | %-13s | %-3s |",
                    no + 1,
                    a.getNpm(),
                    (a.getNama().length() > 13) ? 
                    a.getNama().substring(0, 13): 
                    a.getNama(),
                    a.getKelas());
            System.out.println();
            no++;
        }
        System.out.println("------------------------------------");            
    }
    
    int jumlahAnggota(){
        return anggotaList.size();
    }
    
    void hapusAnggota(int index){
        anggotaList.remove(index);
    }

    void tampilBuku() {
        int no = 0;
        System.out.println("DATA BUKU");            
        System.out.println("-------------------------------------");            
        for (Buku b : bukuList) {
            System.out.printf(
                    "| %-2s | %-4s | %-15s | %-3s |",
                    no + 1,
                    b.getId(),
                    (b.getJudul().length() > 15 ) ? 
                    b.getJudul().substring(0, 15) : b.getJudul(),
                    b.getHalaman());
            System.out.println();
            no++;
        }
        System.out.println("-------------------------------------");            
        System.out.println();
    }
    
    int jumlahBuku(){
        return bukuList.size();
    }

    void hapusBuku(int index){
        bukuList.remove(index);
    }

    // Method untuk mencari anggota berdasarkan NPM
    Anggota cariAnggotaByNpm(String npm) {
        for (Anggota a : anggotaList) {
            if (a.getNpm().equals(npm)) {
                return a;
            }
        }
        return null;
    }

    // Method untuk mencari buku berdasarkan ID
    Buku cariBukuById(String id) {
        for (Buku b : bukuList) {
            if (b.getId().equals(id)) {
                return b;
            }
        }
        return null;
    }
}

