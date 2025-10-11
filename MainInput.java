import java.util.Scanner;

public class MainInput {

    static Scanner sc = new Scanner(System.in);
    static int pilih = 0;
    static Petugas p = new Petugas();

    void generateSampleData() {
        // sample data buku
        Buku b1 = new Buku();
        b1.setId("B001");
        b1.setJudul("Pemrograman Java");
        b1.setHalaman(350);
        p.simpanBuku(b1);

        Buku b2 = new Buku();
        b2.setId("B002");
        b2.setJudul("Biologi");
        b2.setHalaman(200);
        p.simpanBuku(b2);

        Buku b3 = new Buku();
        b3.setId("B003");
        b3.setJudul("Sejarah");
        b3.setHalaman(150);
        p.simpanBuku(b3);

        Buku b4 = new Buku();
        b4.setId("B010");
        b4.setJudul("Ekonomi");
        b4.setHalaman(180);
        p.simpanBuku(b4);

        // sample data anggota
        Anggota a1 = new Anggota();
        a1.setNpm("A001");
        a1.setNama("Sugeng");
        a1.setKelas("IF-1");
        p.simpanAnggota(a1);

        Anggota a2 = new Anggota();
        a2.setNpm("A004");
        a2.setNama("Mukti");
        a2.setKelas("IF-2");
        p.simpanAnggota(a2);

        // sample data peminjaman sesuai gambar
        p.pinjamBuku("P001", a1, b3); // Sugeng meminjam Sejarah
        p.pinjamBuku("P002", a2, b2); // Mukti meminjam Biologi
        p.pinjamBuku("P002", a2, b4); // Mukti meminjam Ekonomi
    }

    public static void main(String[] args) {
        new MainInput().generateSampleData();

        do {
            boolean valid = false;
            mainMenu();

            // memastikan user harus input integer
            while (!valid) {
                System.out.print("[ Ketik 1/2/3/4/5/6/7/8/9/0 ]: ");

                if (sc.hasNextInt()) {
                    pilih = sc.nextInt();

                    if (pilih > 9 || pilih < 0) {
                        System.out.println("[ Menu salah!, Coba lagi. ]");
                        valid = false;
                    } else {
                        valid = true;
                    }
                } else {
                    System.out.println("[ Coba lagi! ]");
                    sc.next();
                    valid = false;
                }
            }

            if (pilih == 1) {
                mainTambahAnggota();
            } else if (pilih == 2) {
                p.tampilAnggota();
                System.out.print("[ Tekan apapun untuk kembali ]");
                sc.nextLine();
                sc.nextLine();
            } else if (pilih == 3) {
                mainHapusAnggota();
            } else if (pilih == 4) {
                mainTambahBuku();
            } else if (pilih == 5) {
                p.tampilBuku();
                System.out.print("[ Tekan apapun untuk kembali ]");
                sc.nextLine();
                sc.nextLine();
            } else if (pilih == 6) {
                mainHapusBuku();
            } else if (pilih == 7) {
                mainPinjamBuku();
            } else if (pilih == 8) {
                mainLihatPinjamBuku();
            } else if (pilih == 9) {
                mainHapusPinjamBuku();
            } else if (pilih == 0) {
                System.out.println("[ Keluar dari program ]");
                break;
            }

        } while (true);
    }

    private static void mainHapusPinjamBuku() {
        boolean valid = false;
        int baris = 0;

        p.tampilPinjamBuku();

        if (p.jumlahPeminjaman() == 0) {
            System.out.println("[ Tidak ada data peminjaman ]");
            sc.nextLine();
            sc.nextLine();
            return;
        }

        // memastikan user harus input integer
        while (!valid) {
            System.out.print("[ Pilih baris yang akan dihapus ]: ");

            if (sc.hasNextInt()) {
                baris = sc.nextInt();
                valid = true;
            } else {
                System.out.println("[ Coba lagi! ]");
                sc.next();
            }
        }

        // jika salah baris
        if (baris > p.jumlahPeminjaman() || baris < 1) {
            System.out.println("[ Gagal hapus, baris salah ]");
            sc.nextLine();
            sc.nextLine();
        } else {
            p.hapusPinjamBuku(baris - 1);
            System.out.println("[ Berhasil dihapus ]");
        }
    }

    private static void mainLihatPinjamBuku() {
        p.tampilPinjamBuku();
        System.out.print("[ Tekan apapun untuk kembali ]");
        sc.nextLine();
        sc.nextLine();
    }

    private static void mainPinjamBuku() {
        String idPeminjaman, npmAnggota, idBuku;

        sc.nextLine();
        System.out.print("ID Peminjaman: ");
        idPeminjaman = sc.nextLine();
        System.out.print("NPM Anggota: ");
        npmAnggota = sc.nextLine();
        System.out.print("ID Buku: ");
        idBuku = sc.nextLine();

        // Cari anggota dan buku
        Anggota anggota = p.cariAnggotaByNpm(npmAnggota);
        Buku buku = p.cariBukuById(idBuku);

        if (anggota == null) {
            System.out.println("[ Anggota dengan NPM " + npmAnggota + " tidak ditemukan ]");
        } else if (buku == null) {
            System.out.println("[ Buku dengan ID " + idBuku + " tidak ditemukan ]");
        } else {
            p.pinjamBuku(idPeminjaman, anggota, buku);
            System.out.println("[ Peminjaman berhasil disimpan ]");
        }

        sc.nextLine();
    }

    private static void mainHapusBuku() {
        boolean valid = false;
        int baris = 0;

        p.tampilBuku();

        // memastikan user harus input integer
        while (!valid) {
            System.out.print("[ Pilih baris yang akan dihapus ]: ");

            if (sc.hasNextInt()) {
                baris = sc.nextInt();
                valid = true;
            } else {
                System.out.println("[ Coba lagi! ]");
                sc.next();
            }
        }

        // jika salah baris
        if (baris > p.jumlahBuku()) {
            System.out.println("[ Gagal hapus, baris salah ]");
            sc.nextLine();
            sc.nextLine();
        } else {
            p.hapusBuku(baris - 1);
            System.out.println("[ Berhasil dihapus ]");
        }
    }

    private static void mainTambahBuku() {
       String id, judul;
       int halaman;

        sc.nextLine();
        System.out.print("ID Buku: ");
        id = sc.nextLine();
        System.out.print("Judul: ");
        judul = sc.nextLine();
        System.out.print("Jumlah Halaman: ");
        halaman = sc.nextInt();

        Buku b = new Buku();
        b.setId(id);
        b.setJudul(judul);
        b.setHalaman(halaman);
        p.simpanBuku(b);

        System.out.println("[ Data buku tersimpan. ]");
    }

    static void mainMenu() {
        System.out.println("Anggota: " + p.jumlahAnggota() + " | Buku: " + p.jumlahBuku() + " | Peminjaman: " + p.jumlahPeminjaman());
        System.out.println("-----------------------");
        System.out.println("1. Tambah Anggota");
        System.out.println("2. Tampilkan Anggota");
        System.out.println("3. Hapus Anggota");
        System.out.println("4. Tambah Buku");
        System.out.println("5. Tampilkan Buku");
        System.out.println("6. Hapus Buku");
        System.out.println("7. Pinjam Buku");
        System.out.println("8. Lihat Peminjaman");
        System.out.println("9. Hapus Peminjaman");
        System.out.println("0. Keluar");
        System.out.println("-----------------------");
    }

    static void mainTambahAnggota() {
        String npm, nama, kelas;

        sc.nextLine();
        System.out.print("NPM: ");
        npm = sc.nextLine();
        System.out.print("Nama: ");
        nama = sc.nextLine();
        System.out.print("Kelas: ");
        kelas = sc.nextLine();

        Anggota a = new Anggota();
        a.setNpm(npm);
        a.setNama(nama);
        a.setKelas(kelas);

        p.simpanAnggota(a);
        System.out.println("[ Data anggota tersimpan. ]");
    }

    static void mainHapusAnggota() {
        boolean valid = false;
        int baris = 0;

        p.tampilAnggota();

        // memastikan user harus input integer
        while (!valid) {
            System.out.print("[ Pilih baris yang akan dihapus ]: ");

            if (sc.hasNextInt()) {
                baris = sc.nextInt();
                valid = true;
            } else {
                System.out.println("[ Coba lagi! ]");
                sc.next();
            }
        }

        // jika salah baris
        if (baris > p.jumlahAnggota()) {
            System.out.println("[ Gagal hapus, baris salah ]");
            sc.nextLine();
            sc.nextLine();
        } else {
            p.hapusAnggota(baris - 1);
            System.out.println("[ Berhasil dihapus ]");
        }
    }
}